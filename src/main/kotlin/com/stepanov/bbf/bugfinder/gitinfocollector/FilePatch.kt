package com.stepanov.bbf.bugfinder.gitinfocollector

import com.stepanov.bbf.bugfinder.util.splitWithoutRemoving
import com.stepanov.bbf.reduktor.util.getAllWithout
import kotlinx.serialization.Serializable
import kotlin.system.exitProcess

@Serializable
data class FilePatch(
    val fileName: String,
    val text: String,
    val patches: List<Patch>
)

@Serializable
data class Patch(
    val startOldLine: Int,
    val numOfOldLines: Int,
    val startNewLine: Int,
    val numOfNewLines: Int,
    val removedLines: List<String>,
    val addedLines: List<String>
)

object PatchParser {

    fun parsePatchesFromString(str: String): List<Patch> {
        return str.splitWithoutRemoving(Regex("""@@ -\d+,\d+ \+\d+,\d+ @@"""))
            .drop(1)
            .chunked(2)
            .map { listOf(it.first().filter { it != '@' }.trim(), it.last()) }
            .map { line ->
                val numbers =
                    line.first().split(Regex("[+\\-, ]"))
                        .map { it.trim() }
                        .filter { it.isNotEmpty() }
                        .map { it.toInt() }
                if (numbers.size < 4) return listOf()
                val modifications =
                    line.last().split("\n")
                        .filter { it.isNotEmpty() }
                        .groupBy { it[0] }
                val removedLines = modifications['-']?.filter { it.substring(1).trim().isNotEmpty() } ?: listOf()
                val addedLines = modifications['+']?.filter { it.substring(1).trim().isNotEmpty() } ?: listOf()
                Patch(numbers[0], numbers[1], numbers[2], numbers[3], removedLines, addedLines)
            }
    }
}


