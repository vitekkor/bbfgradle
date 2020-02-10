package com.stepanov.bbf.bugfinder.executor

import com.stepanov.bbf.bugfinder.manager.Bug
import com.stepanov.bbf.bugfinder.manager.BugManager
import com.stepanov.bbf.bugfinder.manager.BugType
import org.apache.log4j.Logger
import java.io.File

open class ProjectCompilationChecker(private val compilers: List<CommonCompiler>) : Checker() {

    override fun isCompilationSuccessful(project: Project): Boolean {
        val path = saveOrRemoveTmpFile(project, true)
        val res = compilers.all { it.checkCompiling(path) }
        saveOrRemoveTmpFile(project, false)
        return res
    }

    override fun isCompilerBug(project: Project): List<Bug> {
        val path = saveOrRemoveTmpFile(project, true)
        val text = project.texts.mapIndexed { index, s -> "//File: ${path.split(" ")[index]}\n$s" }.joinToString("\n")
        val res = mutableListOf<Bug>()
        compilers.forEach { compiler ->
            if (compiler.isCompilerBug(path)) {
                res.add(Bug(compiler.compilerInfo, compiler.getErrorMessage(path), text, BugType.UNKNOWN))
            }
        }
        if (res.size != 0) return res
        val compilersToStatus = compilers.map { it to it.checkCompiling(path) }
        val grouped = compilersToStatus.groupBy { it.first.compilerInfo.split(" ").first() }
        for (g in grouped) {
            if (g.value.map { it.second }.toSet().size != 1) {
                val diffCompilers =
                    g.value.groupBy { it.second }.mapValues { it.value.first().first.compilerInfo }.values
                res.add(
                    Bug(diffCompilers.joinToString(separator = ","), "", text, BugType.DIFFCOMPILE)
                )
            }
        }
        return res
    }

    private fun saveOrRemoveTmpFile(project: Project, save: Boolean): String {
        val texts = project.texts
        val textToTmpPath = texts.mapIndexed { index, s -> s to generateTmpPath(index) }
        val commonTmpName = textToTmpPath.joinToString(" ") { it.second }
        if (save) textToTmpPath.forEach { File(it.second).writeText(it.first) }
        else textToTmpPath.forEach { File(it.second).delete() }
        return commonTmpName
    }

    private fun generateTmpPath(idx: Int): String = "${CompilerArgs.pathToTmpFile.substringBefore(".kt")}$idx.kt"

    override val additionalConditions: List<() -> Boolean>
        get() = listOf()

    private val log = Logger.getLogger("mutatorLogger")
}