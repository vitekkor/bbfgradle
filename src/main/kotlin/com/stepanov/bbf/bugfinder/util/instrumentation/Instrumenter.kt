package com.stepanov.bbf.bugfinder.util.instrumentation

import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.executor.checkers.CoverageGuider
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.gitinfocollector.FilePatchHandler
import kotlin.system.exitProcess

object Instrumenter {
    fun instrumentJars() {
        val pathToGradleDir = "/home/zver/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin"
        //val commonJar =
        //    "$pathToGradleDir/kotlin-stdlib-common/1.4.0/1c752cce0ead8d504ccc88a4fed6471fd83ab0dd/kotlin-stdlib-common-1.4.0.jar" to "$pathToGradleDir/kotlin-stdlib-common/1.4.0/a8b4f1baa61d83112d68bb4a3230d58b5972e3c3/kotlin-stdlib-common-1.4.0-sources.jar"
        //val stdlibJar = "$pathToGradleDir/kotlin-stdlib/1.4.0/63e75298e93d4ae0b299bb869cf0c627196f8843/kotlin-stdlib-1.4.0.jar" to "$pathToGradleDir/kotlin-stdlib/1.4.0/4a5e4f984e6c3e380f100857f04aba4f3e5fbda8/kotlin-stdlib-1.4.0-sources.jar"
        //val jarsToSerialize = listOf(commonJar, stdlibJar)
        val compilerJar =
            "$pathToGradleDir/kotlin-compiler/1.4.0/962125a9b1bd61cceaf79740919b153c12a6ecd9/kotlin-compiler-1.4.0_backup.jar" to "$pathToGradleDir/kotlin-compiler/1.4.0/456958114e183d1bf468f80d23b5ca9023b596af/kotlin-compiler-1.4.0-sources.jar"
        val jarsToSerialize = listOf(compilerJar)
        val pathToCoverage = "tmp/coverage.txt"
        val proj = Project.createFromCode(
            """
        fun main() {
            println("Hello")
        }
    """.trimIndent()
        )
        val filteredPatches = CoverageGuider.patches.filter(CoverageGuider::filterPatchesForUnsignedNumbersTest)
        val modifiedLines = FilePatchHandler(filteredPatches).getListOfAffectedLines(true, "tmp/serializedAffectedLines.txt")
        for (jar in jarsToSerialize) {
            if (CompilerArgs.instrumentationLevel == "BRANCH") {
                JarSourceCodeInstrumenterOnBranchLevel(
                    jar.first,
                    listOf(jar.second),
                    jar.first.substringBefore(".jar") + "-inst.jar"
                ).instrument(modifiedLines)
            } else {
//                JarSourceCodeInstrumenterOnMethodLevel(
//                    jar.first,
//                    listOf(jar.second),
//                    jar.first.substringBefore(".jar") + "-inst.jar"
//                ).instrument(desiredCoverage)
            }
        }
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
        exitProcess(0)
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