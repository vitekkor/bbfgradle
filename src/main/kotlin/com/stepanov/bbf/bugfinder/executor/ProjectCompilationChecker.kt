package com.stepanov.bbf.bugfinder.executor

import com.stepanov.bbf.bugfinder.manager.Bug
import com.stepanov.bbf.bugfinder.manager.BugType
import com.stepanov.bbf.bugfinder.util.saveOrRemoveToTmp
import org.apache.log4j.Logger
import java.io.File

open class ProjectCompilationChecker(private val compilers: List<CommonCompiler>) : Checker() {

    override fun isCompilationSuccessful(project: Project): Boolean {
        val path = project.saveOrRemoveToTmp(true)
        val res = compilers.all { it.checkCompiling(path) }
        project.saveOrRemoveToTmp(false)
        return res
    }

    override fun isCompilerBug(project: Project): List<Bug> {
        val path = project.saveOrRemoveToTmp(true)
        val text = project.getCommonText(path)
        val res = mutableListOf<Bug>()
        compilers.forEach { compiler ->
            if (compiler.isCompilerBug(path)) {
                res.add(Bug(compiler, compiler.getErrorMessage(path), project, BugType.UNKNOWN))
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


    override val additionalConditions: List<() -> Boolean>
        get() = listOf()

    private val log = Logger.getLogger("mutatorLogger")
}