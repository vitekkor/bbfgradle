package com.stepanov.bbf.bugfinder.executor.checkers

import com.stepanov.bbf.bugfinder.executor.COMPILE_STATUS
import com.stepanov.bbf.bugfinder.executor.CommonCompiler
import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.executor.compilers.JVMCompiler
import com.stepanov.bbf.bugfinder.manager.Bug
import com.stepanov.bbf.bugfinder.manager.BugType
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.tracer.Tracer
import com.stepanov.bbf.coverage.CoverageEntry
import com.stepanov.bbf.coverage.ProgramCoverage
import org.jetbrains.kotlin.abicmp.MySummaryReport
import org.jetbrains.kotlin.abicmp.tasks.JarTask
import org.jetbrains.kotlin.abicmp.tasks.checkerConfiguration
import java.io.File
import java.util.jar.JarFile

open class CompilationChecker(val compilers: List<CommonCompiler>) /*: Checker()*/ {

    constructor(compiler: CommonCompiler) : this(listOf(compiler))

    fun isCompilationSuccessful(project: Project): Boolean = compilers.all { it.checkCompiling(project) }

    fun compileAndGetMessage(project: Project): String = compilers.first().getErrorMessage(project)

    fun compileAndGetStatuses(project: Project): List<COMPILE_STATUS> =
        compilers.map { it.tryToCompileWithStatus(project) }

    fun checkTraces(project: Project): Boolean {
        val compilers = CompilerArgs.getCompilersList()
        val copyOfProject = project.copy()
        Tracer(compilers.first(), copyOfProject).trace()
        return TracesChecker(compilers).checkBehavior(copyOfProject)
    }

    fun checkABI(project: Project): Pair<Int, File>? {
        val compilers = CompilerArgs.getCompilersList()
        if (compilers.size != 2) return null
        val compiled = compilers.mapIndexed { index, comp ->
            comp.pathToCompiled =
                comp.pathToCompiled.replace(".jar", "$index.jar")
            comp.compile(project).pathToCompiled
        }
        if (compiled.any { it == "" }) return null
        val jars = compiled.map { JarFile(it) }
        val task =
            JarTask(
                "",
                jars.first(),
                jars.last(),
                compilers.first().compilerInfo,
                compilers.last().compilerInfo,
                File("tmp/report.html"),
                checkerConfiguration {}
            )
        val execRes = task.execute()
        MySummaryReport.summary.add(task.defectReport)
        return execRes
    }

    //
    fun isCoverageDecreases(project: Project): Boolean {
        val sumCoverage = CoverageGuider.getCoverage(project, compilers)
        val k = CoverageGuider.calcKoefOfCoverageUsage(sumCoverage)
        println("k = $k")
        if (k > CoverageGuider.initCoef) CoverageGuider.initCoef = k
        return k < CoverageGuider.initCoef
    }

    fun checkAndGetCompilerBugs(project: Project): List<Bug> {
        val res = mutableListOf<Bug>()
        compilers.forEach { compiler ->
            if (compiler.isCompilerBug(project)) {
                val msg = compiler.getErrorMessage(project)
                val type =
                    if (msg.contains("Exception while analyzing expression")) BugType.FRONTEND else BugType.BACKEND
                res.add(Bug(compiler, msg, project.copy(), type))
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
                    Bug(diffCompilers, "", project.copy(), BugType.DIFFCOMPILE)
                )
            }
        }
        return res
    }


}