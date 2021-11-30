package com.stepanov.bbf.bugfinder.util

fun String.splitWithoutRemoving(regex: Regex): List<String> {
    val ranges = regex.findAll(this).map { it.range }.toList().flatMap { listOf(it.first, it.last + 1) }.toMutableList()
    if (ranges.isEmpty()) return listOf(this)
    if (ranges.first() != 0) ranges.add(0, 0)
    if (ranges.last() != this.length) ranges.add(this.length)
    return ranges.zipWithNext().map { this.substring(it.first, it.second) }
}

fun String.erase(str: String) =
    this.split(str).joinToString("")

fun String.containsAny(vararg words: String): Boolean =
    words.any { this.contains(it) }

fun String.notContainsAny(vararg words: String): Boolean = !this.containsAny(*words)

fun List<String>.filterContains(vararg words: String): List<String> =
    this.filter { str -> words.any { word -> str.contains(word) } }

fun List<String>.filterNotContains(vararg words: String): List<String>  =
    this.filterNot { str -> words.any { word -> str.contains(word) } }