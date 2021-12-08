package com.stepanov.bbf.bugfinder.gitinfocollector

import com.stepanov.bbf.bugfinder.util.splitWithoutRemoving
import java.io.File

object GitCommitsParser {
    const val command = "git log --all --until=08.14.2020 -i --grep='inline class' > inlineClassCommits.txt"
    fun parse(pathToFileWithCommits: String): String {
        val text = File(pathToFileWithCommits).readText()
        val commits = text.splitWithoutRemoving(Regex("commit [0-9a-z]{40}\n"))
            .chunked(2) { it.joinToString("\n") }
            .filter { !it.contains("[kotlin compiler][update]") && !it.contains("Coroutine") }
            .joinToString("\n") { it.substringAfter("commit ").substringBefore("\n") }
        //File("/home/zver/IdeaProjects/bbfgradle/inlineClassesCommits.txt").writeText(commits)
        return commits
    }
}