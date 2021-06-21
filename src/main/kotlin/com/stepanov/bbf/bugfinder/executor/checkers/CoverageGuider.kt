package com.stepanov.bbf.bugfinder.executor.checkers

import com.stepanov.bbf.bugfinder.executor.CommonCompiler
import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.gitinfocollector.FilePatch
import com.stepanov.bbf.bugfinder.gitinfocollector.FilePatchHandler
import com.stepanov.bbf.bugfinder.gitinfocollector.GitRepo
import com.stepanov.bbf.bugfinder.gitinfocollector.SignatureCollector
import coverage.CoverageEntry
import coverage.MyMethodBasedCoverage
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

object CoverageGuider {
    private lateinit var desiredCoverage: List<CoverageEntry>
    var initCoef = 0
    private val commit = "d1322280ddba07a581cc18f9e97d040c9c1a95da"
    private val patches: List<FilePatch>
        get() {
            val pathToSerializedCommits = CompilerArgs.pathToSerializedCommits
            val serFile = File(pathToSerializedCommits).listFiles()?.toList()?.find { it.name == commit.take(7) }
            serFile?.let {
                val r = Json.decodeFromString<List<FilePatch>>(serFile.readText())
                return r
            }
            val repo = GitRepo("JetBrains", "kotlin")
            val patches = repo.getPatches(commit)
            File("$pathToSerializedCommits/${commit.take(7)}").writeText(Json.encodeToString(patches))
            return patches
        }
    private val modifiedFunctions = FilePatchHandler(patches).getListOfAffectedFunctions(true)
    private val signatures =
        SignatureCollector.collectSignatures(modifiedFunctions)

    fun init(commit: String, project: Project) {
        //println("PATCHES = ${patches.size}")
        this.desiredCoverage = signatures
        val initCoverage = getCoverage(project, CompilerArgs.getCompilersList())
        initCoef = calcKoefOfCoverageUsage(initCoverage)
        println("INIT K = $initCoef\n")
    }

    fun getCoverage(project: Project, compilers: List<CommonCompiler>): Map<CoverageEntry, Int> {
        CompilerArgs.isInstrumentationMode = true
        val sumCoverage = mutableMapOf<CoverageEntry, Int>()
        for (compiler in compilers) {
            compiler.checkCompiling(project)
//            val coverage = ProgramCoverage.createFromMethodProbes()
//            val coverageEntries =
//                coverage.getMethodProbes().entries.map { CoverageEntry.parseFromKtCoverage(it.key) to it.value }.toMap()
            val coverageEntries = MyMethodBasedCoverage.methodProbes
            coverageEntries.entries.forEach {
                sumCoverage[it.key]?.let { c -> sumCoverage[it.key] = c + it.value } ?: sumCoverage.put(
                    it.key,
                    it.value
                )
            }
        }
        CompilerArgs.isInstrumentationMode = false
        return sumCoverage
    }

    fun calcKoefOfCoverageUsage(compilationCoverage: Map<CoverageEntry, Int>) =
        desiredCoverage.sumBy { compilationCoverage[it] ?: 0 }

}