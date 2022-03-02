package com.stepanov.bbf.bugfinder.util.instrumentation

import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.executor.checkers.CoverageGuider
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.gitinfocollector.FilePatchHandler
import kotlin.system.exitProcess

object Instrumenter {
    fun instrumentJars(): List<Pair<Pair<String, Int>, Int>> {
        val pathToGradleDir = "/home/zver/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin"
        val commonJar =
            "$pathToGradleDir/kotlin-stdlib-common/1.4.0/1c752cce0ead8d504ccc88a4fed6471fd83ab0dd/kotlin-stdlib-common-1.4.0.jar" to "$pathToGradleDir/kotlin-stdlib-common/1.4.0/a8b4f1baa61d83112d68bb4a3230d58b5972e3c3/kotlin-stdlib-common-1.4.0-sources.jar"
        val stdlibJar =
            "$pathToGradleDir/kotlin-stdlib/1.4.0/63e75298e93d4ae0b299bb869cf0c627196f8843/kotlin-stdlib-1.4.0.jar" to "$pathToGradleDir/kotlin-stdlib/1.4.0/4a5e4f984e6c3e380f100857f04aba4f3e5fbda8/kotlin-stdlib-1.4.0-sources.jar"
//        val jarsToSerialize = listOf(commonJar, stdlibJar)
        val compilerJar =
            "$pathToGradleDir/kotlin-compiler/1.6.20-RC-169/741c856f7e52ac9eae26c63dc2965d546624fb89/kotlin-compiler-1.6.20-RC-169-backup.jar" to "$pathToGradleDir/kotlin-compiler/1.6.20-RC-169/861ac055b6d8c388a540a8d1f284f3782abc4e8b/kotlin-compiler-1.6.20-RC-169-sources.jar"
        //val jarsToSerialize = listOf(compilerJar to null, stdlibJar to commonJar)
        val jarsToSerialize = listOf<Pair<Pair<String, String>, Pair<String, String>?>>(compilerJar to null)
        val pathToCoverage = "tmp/coverage.txt"
        val proj = Project.createFromCode(
            """
        fun main() {
            println("Hello")
        }
    """.trimIndent()
        )
        val modifiedLines =
            CoverageGuider
                .getModifiedLines("tmp/serAffectedLines.txt")
                .filter { it.second.isNotEmpty() }
//        val filteredPatches = CoverageGuider.patches.filter(CoverageGuider::filterPatchesForUnsignedNumbersTest)
//        val modifiedLines =
//            FilePatchHandler(filteredPatches)
//                .getListOfAffectedLines(true, "tmp/serializedAffectedLines.txt")
//                .filter { it.second.isNotEmpty() }
        val jar = jarsToSerialize.first()
        val res =  JarSourceCodeInstrumenterOnLineLevel(
            jar.first.first,
            listOf(jar.first.second) + (jar.second?.let { listOf(it.second) } ?: listOf()),
            jar.first.first.substringBefore(".jar") + "-inst.jar"
        ).instrument(modifiedLines, true)
        return res
//        for (jar in jarsToSerialize) {
//        when (CompilerArgs.instrumentationLevel) {
//            "BRANCH" ->
//                JarSourceCodeInstrumenterOnBranchLevel(
//                    jar.first.first,
//                    listOf(jar.first.second),
//                    jar.first.first.substringBefore(".jar") + "-inst.jar"
//                ).instrument(modifiedLines)
//            "LINE" ->
//                JarSourceCodeInstrumenterOnLineLevel(
//                    jar.first.first,
//                    listOf(jar.first.second) + (jar.second?.let { listOf(it.second) } ?: listOf()),
//                    jar.first.first.substringBefore(".jar") + "-inst.jar"
//                ).instrument(modifiedLines, true)
//            "METHOD" -> TODO()
////                    JarSourceCodeInstrumenterOnMethodLevel(
////                        jar.first,
////                        listOf(jar.second),
////                        jar.first.substringBefore(".jar") + "-inst.jar"
////                    ).instrument(desiredCoverage)
//        }
//        }
//        for (jar in jarsToSerialize) {
//            JarSourceCodeInstrumenterOnBranchLevel(
//                jar.first,
//                listOf(jar.second),
//                jar.first.substringBefore(".jar") + "-inst.jar"
//            ).instrument(listOf())
//        }
//        exitProcess(0)
//        CoverageGuider.init(pathToCoverage, proj)
//        val desiredCoverage = CoverageGuider.desiredCoverage
//        println(desiredCoverage)
//        exitProcess(0)
//        for (jar in jarsToSerialize) {
//            if (CompilerArgs.instrumentationLevel == "BRANCH") {
//                JarSourceCodeInstrumenterOnBranchLevel(
//                    jar.first,
//                    listOf(jar.second),
//                    jar.first.substringBefore(".jar") + "-inst.jar"
//                ).instrument(desiredCoverage)
//            } else {
//                JarSourceCodeInstrumenterOnMethodLevel(
//                    jar.first,
//                    listOf(jar.second),
//                    jar.first.substringBefore(".jar") + "-inst.jar"
//                ).instrument(desiredCoverage)
//            }
//        }
    }
}