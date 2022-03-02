package com.stepanov.bbf.bugfinder.util.statistic

import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import kotlinx.serialization.ExperimentalSerializationApi
import kotlin.properties.Delegates

@OptIn(ExperimentalSerializationApi::class)
object CoverageStatisticWriter {

    var fileName: String by Delegates.observable("") { _, _, newValue ->
        instance =
            when (CompilerArgs.statisticMode) {
                "LOCAL" -> LineCoverageStatisticsCollectorForEachSeed(newValue)
                "GLOBAL" -> LineCoverageStatisticsCollector
                else -> throw Exception("Unsupported statistic collection mode")
            }

    }
    lateinit var instance: CoverageStatisticsCollector<*>
    var currentMutationName = ""

}