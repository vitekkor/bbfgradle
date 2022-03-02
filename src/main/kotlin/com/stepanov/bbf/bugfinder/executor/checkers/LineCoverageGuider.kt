package com.stepanov.bbf.bugfinder.executor.checkers

import com.stepanov.bbf.bugfinder.executor.COMPILE_STATUS
import com.stepanov.bbf.bugfinder.executor.CommonCompiler
import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.executor.compilers.JVMCompiler
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.gitinfocollector.*
import com.stepanov.bbf.bugfinder.util.*
import coverage.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import kotlin.math.exp


object LineCoverageGuider {
    //desiredCoverageLines -- number of instrumented lines
    //Unsigned numbers: 1749
    //Callable references: 6129
    //Inline classes: 2595
    //Context receivers: 1807
    const val desiredCoverageLines = 1807
    lateinit var desiredCoverage: IntArray
    var initCoef = 0
    private val helloWorldCoverage = getHelloWorldCoverage()


    fun calcInitialSeed(dir: String, size: Int): Map<String, Pair<IntArray, Double>> {

        fun calcTestScore(testCoverage: List<Int>, desiredCoverage: MutableList<Int>) =
            testCoverage.sumOf { i ->
                if (desiredCoverage[i] == 0) 1E+6
                else 1.0 / exp(desiredCoverage[i].toDouble())
            }

        val savedCoverage = mutableMapOf<String, IntArray>()
        val testDirectory = File(dir).listFiles()
        f1@for ((i, f) in testDirectory.filter { it.absolutePath.endsWith(".kt") }.withIndex()) {
            print("Calc coverage of ${f.name} $i from ${testDirectory.size} ")
            val p = Project.createFromCode(f.readText())
            for (c in CompilerArgs.getCompilersList()) {
                if (!c.checkCompiling(p)) {
                    println()
                    continue@f1
                }
            }
            if (p.configuration.isWithCoroutines() || p.toString().contains("import kotlin.coroutines")) {
                println()
                continue
            }
            val cov1 = getLineCoverage(p)
            println(cov1.count { it == 1 })
            savedCoverage[f.name] = cov1
        }
        val result = mutableMapOf<String, Pair<IntArray, Double>>()
        val desiredCoverage =
            generateSequence { 0 }.take(desiredCoverageLines + 1).toMutableList()//(0..desiredCoverageLines).map { 0 }.toMutableList()
        val sorted = savedCoverage.toList().sortedBy { it.second.count { it == 1 } }
            .map { it.first to it.second.mapIndexed { index, i -> if (i == 1) index else -1 }.filter { it != -1 } }
            .toMutableList()
        for (i in 0 until size) {
            val testToScore = sorted.map { Triple(it.first, it.second, calcTestScore(it.second, desiredCoverage)) }
            val maxScoreTest = testToScore.maxByOrNull { it.third } ?: continue
            println("${maxScoreTest.first} won with ${maxScoreTest.third}")
            maxScoreTest.second.forEach { desiredCoverage[it]++ }
            result[maxScoreTest.first] = maxScoreTest.second.toIntArray() to maxScoreTest.third
            sorted.remove(maxScoreTest.first to maxScoreTest.second)
        }
        return result
    }


    fun init(project: Project, makeUnique: Boolean = CompilerArgs.makeUnique) {
        desiredCoverage = getLineCoverage(project).mapIndexed { index, i ->
            if (CompilerArgs.makeUnique && helloWorldCoverage[index] == 1) 0 else i
        }.toIntArray()
        initCoef = desiredCoverage.count { it == 1 }
        println("INIT K = $initCoef")
    }

    fun getLineCoverage(project: Project, makeUnique: Boolean = CompilerArgs.makeUnique) =
        getLineCoverage(project, CompilerArgs.getCompilersList(), makeUnique)

    fun getLineCoverage(project: Project, compilers: List<CommonCompiler>, makeUnique: Boolean = CompilerArgs.makeUnique): IntArray {
        CompilerArgs.isInstrumentationMode = true
        MyLineBasedCoverage.coveredLines.setAll(0)
        val sumCoverage = IntArray(MyLineBasedCoverage.coveredLines.size)
        for (compiler in compilers) {
            val compiled = compiler.compile(project)
            MyLineBasedCoverage.coveredLines.forEachIndexed { index, i ->
                if (sumCoverage[index] == 0) {
                    sumCoverage[index] = i
                }
            }
            if (compiled.status == COMPILE_STATUS.OK && CompilerArgs.checkExecution) {
                val file = File("tmp/jarCoverage.txt")
                with(file) {
                    if (exists()) delete()
                }
                compiler.exec(compiled.pathToCompiled, Stream.BOTH)
                with(File("tmp/jarCoverage.txt")) {
                    if (exists()) {
                        readText().split(" ").mapNotNull { it.trim().toIntOrNull() }.forEach { sumCoverage[it] = 1 }
                    }
                }
            }
        }
        MyLineBasedCoverage.coveredLines.setAll(0)
        CompilerArgs.isInstrumentationMode = false
        return if (makeUnique) makeCoverageUnique(sumCoverage) else sumCoverage
    }

    private fun makeCoverageUnique(coverage: IntArray): IntArray =
        coverage.mapIndexed { index, i ->
            if (helloWorldCoverage[index] == 1) 0 else i
        }.toIntArray()

//    fun calcKoefOfCoverageUsage(compilationCoverage: Map<CoverageEntry, Int>) =
//        desiredCoverage.sumBy { compilationCoverage[it] ?: 0 }

    fun calcKoefOfCoverageUsage(coverage: IntArray) =
        coverage.count { it == 1 }

    private fun getHelloWorldCoverage(): IntArray {
        val helloWorldProject = Project.createFromCode(
            """
            fun main() {
                println("HELLO WORLD!")
            }
            """.trimIndent()
        )
        return getLineCoverage(helloWorldProject, false)
    }

}