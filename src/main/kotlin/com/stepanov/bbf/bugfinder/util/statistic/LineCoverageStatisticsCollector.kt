package com.stepanov.bbf.bugfinder.util.statistic

import com.stepanov.bbf.bugfinder.executor.checkers.LineCoverageGuider
import java.io.File
import java.time.LocalDateTime

internal object LineCoverageStatisticsCollector: CoverageStatisticsCollector<IntArray>() {
    override val coveredMethods = initCoveredMethods()

    override fun initCoveredMethods(): IntArray {
        val res = IntArray(LineCoverageGuider.desiredCoverageLines + 1)
        with(File(pathToCoverageStatisticFile)) {
            if (exists()) {
                readText()
                    .split(" ")
                    .mapNotNull { it.toIntOrNull() }
                    .forEach { res[it] = 1 }
            }
        }
        return res
    }

    override fun saveCoverageStatistic() {
        val oldStatistics =
            with(File(pathToCoverageStatisticFile)) {
                if (exists()) {
                    readText()
                        .split(" ")
                        .mapNotNull { it.toIntOrNull() }
                } else {
                    listOf()
                }
            }
        val fullCoverage =
            oldStatistics + coveredMethods
                .mapIndexed { index, i -> if (i == 1) index else -1 }
                .filter { it != -1 }
        fullCoverage
            .toSet()
            .sorted()
            .joinToString(" ")
            .let { File(pathToCoverageStatisticFile).writeText(it) }
    }

    override fun addCoveredMethods(cm: IntArray) {
        cm.forEachIndexed { index, i ->
            if (i == 1) coveredMethods[index] = 1
        }
        println("${LocalDateTime.now()} PERCENTAGE OF DESIRED COVERAGE: ${(getPercentageOfDesiredCoverage())}")
    }

    override fun getPercentageOfDesiredCoverage() =
        coveredMethods.count { it == 1 }.toDouble() / LineCoverageGuider.desiredCoverageLines * 100

    override fun addInformationAboutMutationCoverage(coverageDif: Double) {
        println("${LocalDateTime.now()} MUTATION ${CoverageStatisticWriter.currentMutationName} INCREASED COVERAGE ON $coverageDif")
    }

}