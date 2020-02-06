package com.stepanov.bbf.bugfinder.executor

import com.stepanov.bbf.bugfinder.Reducer
import com.stepanov.bbf.bugfinder.manager.BugManager
import com.stepanov.bbf.bugfinder.manager.BugType
import com.stepanov.bbf.bugfinder.util.FilterDuplcatesCompilerErrors
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

    fun checkTextCompiling1(texts: List<String>): Boolean {
        val textToTmpPath = texts.mapIndexed { index, s -> s to generateTmpPath(index) }
        val commonTmpName = textToTmpPath.map { it.second }.joinToString(" ")
        textToTmpPath.forEach { File(it.second).writeText(it.first) }
        val text = textToTmpPath.map { "//File: ${it.second}\n${it.first}" }.joinToString("\n")
        var foundCompilerBug = false
        for (compiler in compilers) {
            if (compiler.isCompilerBug(commonTmpName)) {
                println("FOUND BUG!!!")
                BugManager.saveBug(compiler.compilerInfo, "", text, BugType.BACKEND)
                foundCompilerBug = true
            }
        }
        if (foundCompilerBug) return false
        val compilersToStatus = compilers.map { it to it.checkCompiling(commonTmpName) }
        val grouped = compilersToStatus.groupBy { it.first.compilerInfo.split(" ").first() }
        for (g in grouped) {
            if (g.value.map { it.second }.toSet().size != 1) {
                val diffCompilers =
                    g.value.groupBy { it.second }.mapValues { it.value.first().first.compilerInfo }.values
                BugManager.saveBug(
                    diffCompilers.joinToString(separator = ","),
                    "",
                    text,
                    BugType.DIFFCOMPILE
                )
                return false
            }
        }
        return compilersToStatus.first().second
    }

    private fun generateTmpPath(idx: Int): String = "${CompilerArgs.pathToTmpFile.substringBefore(".kt")}$idx.kt"

    lateinit var compilers: List<CommonCompiler>
}