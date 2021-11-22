package com.stepanov.bbf.bugfinder.util

import com.stepanov.bbf.bugfinder.executor.checkers.CoverageGuider
import coverage.CoverageEntry
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

@ExperimentalSerializationApi
object CoverageStatisticsCollector {
    private val coveredMethods = initCoveredMethods()

    private const val pathToCoverageStatisticFile = "tmp/coverageStatistics.txt"

    private fun initCoveredMethods() =
        try {
            val serializedCoveredMethods = File(pathToCoverageStatisticFile).readText()
            Json.decodeFromString(serializedCoveredMethods)
        } catch (e: Exception) {
            mutableSetOf<CoverageEntry>()
        }

    fun saveCoverageStatistic() {
        val serializedCoveredMethods = Json.encodeToString(coveredMethods)
        File(pathToCoverageStatisticFile).writeText(serializedCoveredMethods)
    }

    fun addCoveredMethods(cm: List<CoverageEntry>) {
        coveredMethods.addAll(cm)
    }

    fun addCoveredMethods(cm: Set<CoverageEntry>) {
        coveredMethods.addAll(cm)
        println("PERCENTAGE OF DESIRED COVERAGE: ${(getPercentageOfDesiredCoverage() * 100)}")
    }

    fun getPercentageOfDesiredCoverage(): Double {
        val fullDesiredCoverage = CoverageGuider.desiredCoverage
        val coveredMethodsFromDesiredCoverage = coveredMethods.filter { it in fullDesiredCoverage }
        return coveredMethodsFromDesiredCoverage.size.toDouble() / fullDesiredCoverage.size
    }
}