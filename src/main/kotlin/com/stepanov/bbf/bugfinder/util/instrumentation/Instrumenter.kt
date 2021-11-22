package com.stepanov.bbf.bugfinder.util.instrumentation

import com.stepanov.bbf.bugfinder.executor.checkers.CoverageGuider
import com.stepanov.bbf.bugfinder.executor.project.Project

object Instrumenter {
    fun instrumentJars() {
        val pathToGradleDir = "/home/zver/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin"
        val commonJar =
            "$pathToGradleDir/kotlin-stdlib-common/1.4.0/1c752cce0ead8d504ccc88a4fed6471fd83ab0dd/kotlin-stdlib-common-1.4.0.jar" to "$pathToGradleDir/kotlin-stdlib-common/1.4.0/a8b4f1baa61d83112d68bb4a3230d58b5972e3c3/kotlin-stdlib-common-1.4.0-sources.jar"
        val stdlibJar = "$pathToGradleDir/kotlin-stdlib/1.4.0/63e75298e93d4ae0b299bb869cf0c627196f8843/kotlin-stdlib-1.4.0.jar" to "$pathToGradleDir/kotlin-stdlib/1.4.0/4a5e4f984e6c3e380f100857f04aba4f3e5fbda8/kotlin-stdlib-1.4.0-sources.jar"
        //val jarsToSerialize = listOf(commonJar, stdlibJar)
        val jarsToSerialize = listOf(stdlibJar)
        val pathToCoverage = "tmp/coverage.txt"
        val proj = Project.createFromCode("""
        fun main() {
            println("Hello")
        }
    """.trimIndent())
        CoverageGuider.init(pathToCoverage, proj)
        val desiredCoverage = CoverageGuider.desiredCoverage
        for (jar in jarsToSerialize) {
            val instrumenter = JarSourceCodeInstrumenter(jar.first, listOf(jar.second, commonJar.second), jar.first.substringBefore(".jar") + "-inst.jar")
            instrumenter.instrument(desiredCoverage)
        }
    }
}