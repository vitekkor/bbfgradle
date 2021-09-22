package com.stepanov.bbf.bugfinder.executor.checkers

import com.stepanov.bbf.bugfinder.executor.CommonCompiler
import com.stepanov.bbf.bugfinder.executor.project.Project
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt


object PerformanceOracle {

    lateinit var project: Project
    lateinit var compilers: List<CommonCompiler>
    lateinit var compilationConfInterval: Pair<Double, Double>
    var compilationSigma: Double = -1.0
    lateinit var executionConfInterval: Pair<Double, Double>
    var executionSigma: Double = -1.0

    fun init(project: Project, compilers: List<CommonCompiler>) {
        this.project = project
        this.compilers = compilers
        if (compilers.size != 2) return
        val firstCompiler = compilers.first()
        val secondCompiler = compilers.last()
        println("COMP INITIALIZING")
        val compilationTimes =
            Array(10) { 0 }.map {
                firstCompiler.tryToCompileWithStatusAndExecutionTime(project).second to
                        secondCompiler.tryToCompileWithStatusAndExecutionTime(project).second
            }.drop(3)
        println(compilationTimes)
        val diffOfCompilationTimes =
            compilationTimes.map { abs(it.first.toDouble() / it.second) }
        println(diffOfCompilationTimes)
        val averageCompilationDeviation = diffOfCompilationTimes.average()
        val compilationVariance = diffOfCompilationTimes.map { (it - averageCompilationDeviation).pow(2) }.average()
        compilationSigma = sqrt(compilationVariance)
        compilationConfInterval =
            averageCompilationDeviation - compilationSigma to averageCompilationDeviation + compilationSigma
        println("COMP INITIALIZING DONE")
        println("EXECUTUION INITIALIZING")
        val executionTimes =
            compilers.map { compiler ->
                val compiled = compiler.compile(project)
                Array(20) { 0 }.map {
                    compiler.getExecutionTime(compiled.pathToCompiled).second
                }
            }
        val diffOfExecutionTimes =
            executionTimes.first()
                .mapIndexed { index, l -> l to executionTimes.last()[index] }
                .map { abs(it.first.toDouble() / it.second) }
        val averageExecutionDeviation = diffOfExecutionTimes.average()
        val executionVariance = diffOfExecutionTimes.map { (it - averageExecutionDeviation).pow(2) }.average()
        executionSigma = sqrt(executionVariance)
        executionConfInterval = averageExecutionDeviation - executionSigma to averageExecutionDeviation + executionSigma
        println("EXECUTUION INITIALIZING DONE")
    }


}