package com.stepanov.bbf.bugfinder.util.statistic

import com.stepanov.bbf.bugfinder.executor.checkers.LineCoverageGuider
import coverage.CoverageEntry
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.time.LocalDateTime

@ExperimentalSerializationApi
abstract class CoverageStatisticsCollector<T> {

    abstract val coveredMethods: T

    abstract fun initCoveredMethods(): T
    abstract fun saveCoverageStatistic()
    abstract fun addCoveredMethods(cm: IntArray)
    abstract fun getPercentageOfDesiredCoverage(): Double
    abstract fun addInformationAboutMutationCoverage(coverageDif: Double)

    internal val pathToCoverageStatisticFile = "tmp/coverageStatistics.txt"
}