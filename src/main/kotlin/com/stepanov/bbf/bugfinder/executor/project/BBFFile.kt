package com.stepanov.bbf.bugfinder.executor.project

import com.intellij.psi.PsiErrorElement
import com.intellij.psi.PsiFile
import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.util.filterLines
import com.stepanov.bbf.bugfinder.util.filterNotLines
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.parser.PSICreator
import org.jetbrains.kotlin.resolve.BindingContext
import java.io.File
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

data class BBFFile(val name: String, var psiFile: PsiFile, var ctx: BindingContext? = null) {

    fun getLanguage(): LANGUAGE {
        return if (name.endsWith(".java")) LANGUAGE.JAVA else LANGUAGE.KOTLIN
    }

    fun changePsiFile(newPsiFile: PsiFile) = changePsiFile(newPsiFile.text)

    fun changePsiFile(newPsiFileText: String) {
        val (psiFile, ctx) = createPSI(newPsiFileText)
        this.psiFile = psiFile
        this.ctx = ctx
    }

    fun isPsiWrong(): Boolean =
        createPSI(psiFile.text, false).first.getAllPSIChildrenOfType<PsiErrorElement>().isNotEmpty()


    private fun createPSI(text: String, withCtx: Boolean = true): Pair<PsiFile, BindingContext?> {
        val creator = PSICreator("")
        val newPsi = when (getLanguage()) {
            LANGUAGE.JAVA -> creator.getPsiForJava(text)
            else -> creator.getPSIForText(text)
        }
        return newPsi to creator.ctx
    }

    val text: String
        get() = psiFile.text
}

internal class BBFFileFactory(
    private val text: String,
    private val configuration: Header
) {

    fun createBBFFiles(name: String = "tmp"): List<BBFFile>? {
        try {
            val splitCode = splitCodeByFiles(text)
            val names = splitCode.map { it.lines().find { it.startsWith(Directives.file) } ?: "" }
            val codeWithoutComments = splitCode.map { it.filterNotLines { it.startsWith("// ") }.trim() }
            val pathToTmp = CompilerArgs.pathToTmpDir
            return if (names.any { it.isEmpty() }) codeWithoutComments.mapIndexed { i, code ->
                val fileName = "$pathToTmp/$name$i.kt"
                val fileToCtx = createKtFileWithCtx(code)
                //val fileToCtx = createKtFileWithCtx("${Directives.file}$fileName\n$code")
                BBFFile(fileName, fileToCtx.first, fileToCtx.second)
            }
            else names.zip(codeWithoutComments).map {
                val fileName = "$pathToTmp/${it.first.substringAfter(Directives.file)}"
                if (fileName.contains(".java"))
                    BBFFile(fileName, PSICreator("").getPsiForJava(it.second, Factory.file.project), null)
                else {
                    val fileToCtx = createKtFileWithCtx(it.second)
                    BBFFile(fileName, fileToCtx.first, fileToCtx.second)
                }
            }
        } catch (e: Throwable) {
            return null
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
        } else fragments.add(text.lines().filterNot { it.startsWith("// ") }.joinToString("\n"))
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

    private fun createKtFileWithCtx(text: String): Pair<PsiFile, BindingContext?> {
        val creator = PSICreator("")
        val file = creator.getPSIForText(text)
        return file to creator.ctx
    }

}