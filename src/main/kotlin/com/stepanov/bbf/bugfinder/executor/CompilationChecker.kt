package com.stepanov.bbf.bugfinder.executor

import com.intellij.psi.PsiFile
import com.stepanov.bbf.bugfinder.manager.Bug
import com.stepanov.bbf.bugfinder.manager.BugType
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import org.apache.log4j.Logger
import org.jetbrains.kotlin.psi.KtFile
import java.io.File
import com.stepanov.bbf.bugfinder.executor.project.Project

open class CompilationChecker(private val compilers: List<CommonCompiler>) /*: Checker()*/ {

    constructor(compiler: CommonCompiler) : this(listOf(compiler))

    fun isCompilationSuccessful(project: Project): Boolean = compilers.all { it.checkCompiling(project) }

    fun compileAndGetMessage(project: Project): String = compilers.first().getErrorMessage(project)

    fun compileAndGetStatuses(project: Project): List<COMPILE_STATUS> = compilers.map { it.tryToCompileWithStatus(project) }

    fun checkAndGetCompilerBugs(project: Project): List<Bug> {
        val res = mutableListOf<Bug>()
        compilers.forEach { compiler ->
            if (compiler.isCompilerBug(project)) {
                val msg = compiler.getErrorMessage(project)
                val type =
                    if (msg.contains("Exception while analyzing expression")) BugType.FRONTEND else BugType.BACKEND
                res.add(Bug(compiler, msg, project, type))
            }
        }
        if (res.size != 0) return res
        val compilersToStatus = compilers.map { it to it.checkCompiling(project) }
        val grouped = compilersToStatus.groupBy { it.first.compilerInfo.split(" ").first() }
        for (g in grouped) {
            if (g.value.map { it.second }.toSet().size != 1) {
                val diffCompilers =
                    g.value.groupBy { it.second }.mapValues { it.value.first().first }.values.toList()
                res.add(
                    Bug(diffCompilers, "", project, BugType.DIFFCOMPILE)
                )
            }
        }
        return res
    }


}