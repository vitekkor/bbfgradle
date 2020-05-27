package com.stepanov.bbf.bugfinder.executor.project

import com.intellij.psi.PsiFile
import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.executor.LANGUAGE
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.reduktor.parser.PSICreator
import java.io.File

data class BBFFile(val name: String, val psiFile: PsiFile)

class BBFFileFactory(
    private val text: String,
    private val configuration: Header
) {

    fun createBBFFiles(name: String = "tmp"): List<BBFFile> {
        val splitCode = splitCodeByFiles(text)
        val names = splitCode.map { it.lines().find { it.startsWith(Directives.file) } ?: "" }
        val pathToTmp = CompilerArgs.pathToTmpDir
        return if (names.any { it.isEmpty() }) splitCode.mapIndexed { i, code ->
            val fileName = "$pathToTmp/$name$i.kt"
            BBFFile(fileName, Factory.psiFactory.createFile("${Directives.file}$fileName\n$code"))
        }
        else names.zip(splitCode).map {
            val fileName = "$pathToTmp/${it.first.substringAfter(Directives.file)}"
            if (fileName.contains(".java"))
                BBFFile(fileName, PSICreator("").getPsiForJava(it.second, Factory.file.project))
            else
                BBFFile(fileName, Factory.psiFactory.createFile(it.second))
        }
    }

    private fun splitByFragments(text: String, splitter: String): List<String> {
        val lines = text.lines()
        val fragments = mutableListOf<String>()
        val firstCommentsSection = lines.takeWhile { it.trim().isEmpty() || it.startsWith("//") }
        if (firstCommentsSection.any { it.startsWith(splitter) }) {
            val curFragment = mutableListOf<String>()
            for (i in firstCommentsSection.size until lines.size) {
                val line = lines[i]
                if (!line.startsWith(splitter)) {
                    curFragment.add(line)
                } else {
                    fragments.add(curFragment.joinToString("\n"))
                    curFragment.clear()
                    curFragment.add(line)
                }
            }
            fragments.add(curFragment.joinToString("\n"))
        } else return listOf(text)
        if (configuration.withDirectives.contains(Directives.coroutinesDirective)) handleCoroutines(fragments)
        val firstFragment = firstCommentsSection.joinToString("\n") + "\n" + fragments[0]
        return listOf(firstFragment) + fragments.subList(1, fragments.size)
    }

    private fun handleCoroutines(fragments: MutableList<String>) {
        val coroutinesPackage = "COROUTINES_PACKAGE"
        val ktCoroutinesPackage = "kotlin.coroutines"
        val helpersImportDirective = "import helpers.*"
        val nameOfHelpersFile = "CoroutineUtil.kt"
        val pathToHelpersFile = "${CompilerArgs.pathToTmpDir}/lib/CoroutineUtil.kt"
        val textOfFile = "${Directives.file}$nameOfHelpersFile\n${File(pathToHelpersFile).readText()}"
        fragments.forEach { it.replace(coroutinesPackage, ktCoroutinesPackage) }
        //Handle helpers
        if (fragments.any { it.contains(helpersImportDirective) }) fragments.add(textOfFile)
    }

    private fun splitCodeByFiles(text: String): List<String> {
        val modules = splitByFragments(text, Directives.module)
        return if (modules.size > 1) modules
        else splitByFragments(text, Directives.file)
    }

}