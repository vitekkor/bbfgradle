package com.stepanov.bbf.bugfinder.util.statistic

import com.stepanov.bbf.bugfinder.executor.checkers.LineCoverageGuider
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.time.LocalDateTime

internal class LineCoverageStatisticsCollectorForEachSeed(val fileName: String) :
    CoverageStatisticsCollector<IntArray>() {

    override val coveredMethods: IntArray = initCoveredMethods()

    override fun initCoveredMethods(): IntArray {
        val res = IntArray(LineCoverageGuider.desiredCoverageLines)
        with(File(pathToCoverageStatisticFile)) {
            if (exists() && readText().trim().isNotEmpty()) {
                Json.decodeFromString<Map<String, IntArray>>(readText())[fileName]?.forEachIndexed { index, i ->
                    res[index] = i
                }
            }
        }
        return res
    }

    override fun saveCoverageStatistic() {
        val deserializedStatistic = with(File(pathToCoverageStatisticFile)) {
            if (exists()) {
                Json.decodeFromString<Map<String, IntArray>>(readText())
            } else {
                mapOf()
            }
        }.toMutableMap()
        val oldCoverage = deserializedStatistic[fileName]
        val newCoverage = oldCoverage?.mapIndexed { index, i -> if (i == 1) i else coveredMethods[index] }?.toIntArray()
            ?: coveredMethods
        deserializedStatistic[fileName] = newCoverage
        File(pathToCoverageStatisticFile).writeText(
            Json.encodeToString(
                deserializedStatistic
            )
        )
    }

//
//
//    {
//        val coverageDif = getPercentageOfDesiredCoverage(newK) - getPercentageOfDesiredCoverage(oldK)
//        println("${LocalDateTime.now()} MUTATION ${CoverageStatisticWriter.currentMutationName} INCREASED COVERAGE ON $coverageDif")
//    }

    override fun addCoveredMethods(cm: IntArray) {
        cm.forEachIndexed { index, i ->
            if (i == 1) coveredMethods[index] = 1
        }
        println("${LocalDateTime.now()} PERCENTAGE OF DESIRED COVERAGE FOR ${fileName}: ${(getPercentageOfDesiredCoverage())}")
    }


    override fun getPercentageOfDesiredCoverage() =
        coveredMethods.count { it == 1 }.toDouble() / LineCoverageGuider.desiredCoverageLines * 100

    override fun addInformationAboutMutationCoverage(coverageDif: Double) {
        println("${LocalDateTime.now()} MUTATION ${CoverageStatisticWriter.currentMutationName} INCREASED COVERAGE FOR $fileName ON $coverageDif")
    }
}