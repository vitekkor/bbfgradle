package com.stepanov.bbf.bugfinder.executor.checkers

import com.stepanov.bbf.bugfinder.executor.COMPILE_STATUS
import com.stepanov.bbf.bugfinder.executor.CommonCompiler
import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.executor.compilers.JVMCompiler
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.gitinfocollector.*
import com.stepanov.bbf.bugfinder.util.*
import coverage.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.system.exitProcess

@ExperimentalSerializationApi
object CoverageGuider {
    //lateinit var desiredCoverage: List<CoverageEntry>
    lateinit var desiredCoverage: List<BranchCoverageEntry>
    var initCoef = 0
    const val pathToCommits = "inlineClassesCommits.txt"

    //1.4.0 commit
    val lastCommit = "0ddb7937eea9ca84442cc358871b2b663d01e298"
    private val commits
        get() =
            File(pathToCommits)
                .let {
                    if (it.exists())
                        it.readText().split("\n")
                    else listOf()
                }

    //private val commit = "d1322280ddba07a581cc18f9e97d040c9c1a95da"
    val patches: List<FilePatch>
        get() {
            val res = mutableListOf<FilePatch>()
            for (commit in commits) {
                val pathToSerializedCommits = CompilerArgs.pathToSerializedCommits
                val serFile = File(pathToSerializedCommits).listFiles()?.toList()?.find { it.name == commit.take(7) }
                if (serFile != null) {
                    res.addAll(Json.decodeFromString<List<FilePatch>>(serFile.readText()))
                    continue
                }
                println("GETTING COMMIT $commit")
                val patches = GitRepo.getLocalPatches(commit, CompilerArgs.pathToKotlin)
                res.addAll(patches)
                File("$pathToSerializedCommits/${commit.take(7)}").writeText(Json.encodeToString(patches))
            }
            return res
        }

    private val patchesWithCommitInfo: List<Pair<FilePatch, String>>
        get() {
            val res = mutableListOf<Pair<FilePatch, String>>()
            for (commit in commits) {
                val pathToSerializedCommits = CompilerArgs.pathToSerializedCommits
                val serFile = File(pathToSerializedCommits).listFiles()?.toList()?.find { it.name == commit.take(7) }
                if (serFile != null) {
                    Json.decodeFromString<List<FilePatch>>(serFile.readText()).forEach { res.add(it to commit) }
                    continue
                }
                val repo = GitRepo("JetBrains", "kotlin")
                val patches = GitRepo.getLocalPatches(commit, CompilerArgs.pathToKotlin)
                patches.forEach { res.add(it to commit) }
                File("$pathToSerializedCommits/${commit.take(7)}").writeText(Json.encodeToString(patches))
            }
            return res
        }

//    fun init(commit: String, project: Project, filterFunc: (CoverageEntry) -> Boolean = { true }) {
//        //println("PATCHES = ${patches.size}")
//        this.desiredCoverage = getFilteredSignatures(filterFunc)
//        val initCoverage = getCoverage(project, CompilerArgs.getCompilersList())
//        initCoef = calcKoefOfCoverageUsage(initCoverage)
//        println("INIT K = $initCoef\n")
//    }

    fun filterPatchesForUnsignedNumbersTest(fileName: String) =
        with(fileName) {
            notContainsAny(
                "testData",
                "/js/",
                "dev/null",
                "tests",
                "/test/",
                "native",
                "/fir",
                "metadata",
                "samples/",
                "/idea/",
                "plugin"
            ) && (endsWith(".java") || endsWith(".kt"))
        }

    fun filterPatchesForUnsignedNumbersTest(patch: Pair<FilePatch, String>) =
        filterPatchesForUnsignedNumbersTest(patch.first.fileName)

    fun filterPatchesForUnsignedNumbersTest(patch: FilePatch) = filterPatchesForUnsignedNumbersTest(patch.fileName)

    fun getModifiedLines(pathToSerialized: String): List<Pair<String, List<Int>>> =
        try {
            val serializedAffectedLines = File(pathToSerialized).readText()
            Json.decodeFromString(serializedAffectedLines)
        } catch (e: Exception) {
            calcModifiedLines().also { File(pathToSerialized).writeText(Json.encodeToString(it)) }
        }


    private fun calcModifiedLines(): List<Pair<String, List<Int>>> {
        val modifiedLines = mutableListOf<Pair<String, List<Int>>>()
        val sourceFiles =
            Files.walk(Paths.get(CompilerArgs.pathToKotlin))
                .map { it.toFile() }
                .filter { filterPatchesForUnsignedNumbersTest(it.path) }
                .toArray().toList().map { it as File }
        val commitMessages = mutableSetOf<String>()
        for ((i, sourceFile) in sourceFiles.withIndex()) {
            println("f = ${sourceFile.path} $i from ${sourceFiles.size}")
            val gitBlameResult =
                GitRepo.executeProcess("git blame -- ${sourceFile.absolutePath}", directory = CompilerArgs.pathToKotlin)
                    .lines()
            if (gitBlameResult.all { !it.contains("shadrina", true) }) continue
            println("LOL")
            val commits =
                gitBlameResult
                    .map { it.trim().substringBefore(' ') }
                    .filter { it.isNotEmpty() }
                    .toSet()
                    .associateWith {
                        GitRepo.executeProcess("git log -n 1 $it", directory = CompilerArgs.pathToKotlin)
                            .trim()
//                        GitRepo.executeProcess("git log --format=%B -n 1 $it", directory = CompilerArgs.pathToKotlin)
//                            .trim()
                    }
                    .filter { it.value.contains("shadrina", true) }
                    //.filter { !it.value.contains("JS", false) }
            if (commits.isEmpty()) continue
            commits.values.forEach {
                commitMessages.add(it)
            }
            val fileModifiedLines = gitBlameResult.mapIndexedNotNull { ind, line ->
                val commit = line.trim().substringBefore(' ')
                if (commit in commits) ind else null
            }
            if (fileModifiedLines.isNotEmpty()) modifiedLines.add(sourceFile.path to fileModifiedLines)
        }
        //commitMessages.forEach(::println)
        return modifiedLines
    }

    fun initDesireCoverage(pathToSerializedCoverage: String, filterFunc: (CoverageEntry) -> Boolean = { true }) {
        val helloWorldProject = Project.createFromCode(
            """
                fun main() {
                    println("Kotlin")
                }""".trimIndent()
        )
        val helloWorldCoverage = getCoverage(helloWorldProject, CompilerArgs.getCompilersList())
        //if (File(pathToSerializedCoverage).exists()) {
//        val p = patchesWithCommitInfo.filter(::filterPatchesForUnsignedNumbersTest)
//        val modifiedFunctions = ExtendedFilePatchHandler(p).getListOfAffectedFunctions(true)
//        println()
////        exitProcess(0)
//        this.desiredCoverage =
//            run {
//                val filteredPatches = patches.filter(::filterPatchesForUnsignedNumbersTest)
//                val modifiedLines = FilePatchHandler(filteredPatches).getListOfAffectedLines(true)
//
//                listOf()
//            }
//            try {
//                val coverageText = File(pathToSerializedCoverage).readText()
//                Json.decodeFromString<List<CoverageEntry>>(coverageText)
//                    .filter { filterFunc.invoke(it) && it !in helloWorldCoverage }
//            } catch (e: Exception) {
//                val filteredPatches = patches.filter(::filterPatchesForUnsignedNumbersTest)
//            if (CompilerArgs.instrumentationLevel == "BRANCH") {
//                val modifiedLines = FilePatchHandler(filteredPatches).getListOfAffectedLines(true)
//                println("LOL")
//                exitProcess(0)
//            } else {
//
//            }
//                val modifiedFunctions =
//                    FilePatchHandler(filteredPatches).getListOfAffectedFunctions(true).filterDuplicatesBy { it.text }
//                val signatures = SignatureCollector.collectSignatures(modifiedFunctions)
//                val desiredCoverage =
//                    signatures.filter { filterFunc.invoke(it) && it !in helloWorldCoverage }.toSet().toList()
//                File(pathToSerializedCoverage).writeText(Json.encodeToString(desiredCoverage))
//                desiredCoverage
//            }
    }

    fun init(
        pathToSerializedCoverage: String,
        project: Project,
        filterFunc: (CoverageEntry) -> Boolean = { true }
    ) {
        initDesireCoverage(pathToSerializedCoverage, filterFunc)
        val initCoverage = getCoverage(project, CompilerArgs.getCompilersList())
        println("init Coverage size = ${initCoverage.size}")
        initCoef = calcKoefOfCoverageUsage(initCoverage)
        println("INIT K = $initCoef\n")
    }

    fun getBranchCoverage(project: Project) = getBranchCoverage(project, CompilerArgs.getCompilersList())

    fun getBranchCoverage(project: Project, compilers: List<CommonCompiler>): Map<BranchCoverageEntry, Int> {
        CompilerArgs.isInstrumentationMode = true
        MyBranchBasedCoverage.methodProbes.clear()
        val sumCoverage = mutableMapOf<BranchCoverageEntry, Int>()
        for (compiler in compilers) {
            compiler.compile(project)
            sumCoverage.putAll(MyBranchBasedCoverage.methodProbes)
        }
        CompilerArgs.isInstrumentationMode = false
        return sumCoverage
    }

    fun getCoverage(project: Project) = CoverageGuider.getCoverage(project, CompilerArgs.getCompilersList())

    fun getCoverage(project: Project, compilers: List<CommonCompiler>): Map<CoverageEntry, Int> {
        CompilerArgs.isInstrumentationMode = true
        MyMethodBasedCoverage.methodProbes.clear()
        val sumCoverage = mutableMapOf<CoverageEntry, Int>()
        val execCoverageEntries = mutableMapOf<CoverageEntry, Int>()
        for (compiler in compilers) {
            val compiled = compiler.compile(project)
            sumCoverage.putAll(MyMethodBasedCoverage.methodProbes)
            if (CompilerArgs.isMiscompilationMode && compiled.status == COMPILE_STATUS.OK) {
                val file = File("tmp/jarCoverage.txt")
                with(file) {
                    if (exists()) delete()
                }
                compiler.exec(compiled.pathToCompiled)
                execCoverageEntries.putAll(
                    with(file) {
                        if (exists()) {
                            readText()
                                .split("\n")
                                .filter { it.trim().isNotEmpty() }
                                .associate {
                                    with(it.split("=")) {
                                        CoverageEntry.parseFromKtCoverage(first()) to last().toInt()
                                    }
                                }
                        } else {
                            mapOf()
                        }
                    }
                )
            }
            execCoverageEntries.forEach {
                sumCoverage[it.key]?.let { c -> sumCoverage[it.key] = c + it.value } ?: sumCoverage.put(
                    it.key,
                    it.value
                )
            }
        }
        CompilerArgs.isInstrumentationMode = false
        return sumCoverage
    }

//    fun calcKoefOfCoverageUsage(compilationCoverage: Map<CoverageEntry, Int>) =
//        desiredCoverage.sumBy { compilationCoverage[it] ?: 0 }

    fun calcKoefOfCoverageUsage(compilationCoverage: Map<CoverageEntry, Int>): Int = TODO()
        //desiredCoverage.sumBy { compilationCoverage[it]?.let { 1 } ?: 0 }

}