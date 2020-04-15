package com.stepanov.bbf.bugfinder.executor

import com.intellij.psi.PsiFile
import com.stepanov.bbf.bugfinder.manager.Bug
import com.stepanov.bbf.bugfinder.manager.BugType
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.util.saveOrRemoveToTmp
import org.apache.log4j.Logger
import org.jetbrains.kotlin.psi.KtFile
import java.io.File

open class CompilationChecker(private val compilers: List<CommonCompiler>) : Factory() /*: Checker()*/ {

    fun isCompilationSuccessful(project: Project): Boolean {
        val path = project.saveOrRemoveToTmp(true)
        if (path.isEmpty()) return false
        val res = compilers.all { it.checkCompiling(path) }
        project.saveOrRemoveToTmp(false)
        return res
    }

    fun isCompilerBug(project: Project): List<Bug> {
        val path = project.saveOrRemoveToTmp(true)
        if (path.isEmpty()) return listOf()
        val res = mutableListOf<Bug>()
        compilers.forEach { compiler ->
            if (compiler.isCompilerBug(path)) {
                val msg = compiler.getErrorMessage(path)
                val type =
                    if (msg.contains("Exception while analyzing expression")) BugType.FRONTEND else BugType.BACKEND
                res.add(Bug(compiler, msg, project, type))
            }
        }
        if (res.size != 0) return res
        val compilersToStatus = compilers.map { it to it.checkCompiling(path) }
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
        project.saveOrRemoveToTmp(false)
        return res
    }


}