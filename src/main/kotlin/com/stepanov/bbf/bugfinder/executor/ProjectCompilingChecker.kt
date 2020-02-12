package com.stepanov.bbf.bugfinder.executor

import com.intellij.psi.PsiErrorElement
import com.stepanov.bbf.bugfinder.Reducer
import com.stepanov.bbf.bugfinder.executor.compilers.MutationChecker
import com.stepanov.bbf.bugfinder.manager.BugManager
import com.stepanov.bbf.bugfinder.manager.BugType
import com.stepanov.bbf.bugfinder.tracer.Tracer
import com.stepanov.bbf.bugfinder.util.FilterDuplcatesCompilerErrors
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.util.getAllChildrenNodes
import org.jetbrains.kotlin.psi.KtFile
import java.io.File


object ProjectCompilingChecker {

    fun checkCompiling(files: List<KtFile>): Boolean =
        checkTextCompiling(files.map { it.text })

    fun checkTextCompiling(texts: List<String>): Boolean {
        val textToTmpPath = texts.mapIndexed { index, s -> s to generateTmpPath(index) }
        val res = checkTextCompiling1(texts)
        textToTmpPath.forEach { File(it.second).delete() }
        return res
    }

    private fun checkTextCompiling1(texts: List<String>): Boolean {
        val textToTmpPath = texts.mapIndexed { index, s -> s to generateTmpPath(index) }
        val commonTmpName = textToTmpPath.joinToString(" ") { it.second }
        textToTmpPath.forEach { File(it.second).writeText(it.first) }
        val text = textToTmpPath.map { "//File: ${it.second}\n${it.first}" }.joinToString("\n")
        var foundCompilerBug = false
        for (compiler in compilers) {
            if (compiler.isCompilerBug(commonTmpName)) {
                println("FOUND BUG!!!")
                BugManager.saveBug(listOf(compiler), "", Project(listOf(text)), BugType.BACKEND)
                foundCompilerBug = true
            }
        }
        if (foundCompilerBug) return false
        val compilersToStatus = compilers.map { it to it.checkCompiling(commonTmpName) }
        val grouped = compilersToStatus.groupBy { it.first.compilerInfo.split(" ").first() }
//        for (g in grouped) {
//            if (g.value.map { it.second }.toSet().size != 1) {
//                val diffCompilers =
//                    g.value.groupBy { it.second }.mapValues { it.value.first().first.compilerInfo }.values
//                BugManager.saveBug(
//                    diffCompilers.toList(),
//                    "",
//                    text,
//                    BugType.DIFFCOMPILE
//                )
//                return false
//            }
//        }
//        return compilersToStatus.first().second
        return false
    }

    fun compareExecutionTraces(texts: List<String>): Boolean {
        val textToTmpPath = texts.mapIndexed { index, s -> s to generateTmpPath(index) }
        val commonTmpName = textToTmpPath.map { it.second }.joinToString(" ")
        textToTmpPath.forEach { File(it.second).writeText(it.first) }
        val text = textToTmpPath.map { "//File: ${it.second}\n${it.first}" }.joinToString("\n")
        val psi = PSICreator("").getPSIForText(text, false)
        println("LOL")
        val tracedTexts = textToTmpPath.map {
            val psiCreator = PSICreator("")
            val f = Tracer(psiCreator.getPSIForFile(it.second), psiCreator.ctx!!, MutationChecker(compilers)).trace()
            File(it.second).writeText(f.text)
            f.text
        }
        if (!checkTextCompiling1(tracedTexts)) {
            return false
        }
        println("LAL")
        val res = TracesChecker(compilers).checkTestForProject(commonTmpName)
        println("LiL")
        //if (res != null) BugManager.saveBug("JVM and JVM-new-inf", "", text, BugType.DIFFBEHAVIOR)
        textToTmpPath.forEach { File(it.second).delete() }
        return true
    }

    private fun generateTmpPath(idx: Int): String = "${CompilerArgs.pathToTmpFile.substringBefore(".kt")}$idx.kt"

    lateinit var compilers: List<CommonCompiler>
}