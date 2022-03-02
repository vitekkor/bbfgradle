package com.stepanov.bbf.bugfinder.gitinfocollector

import com.stepanov.bbf.bugfinder.executor.CommonCompiler
import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.util.Stream
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import okhttp3.OkHttpClient
import okhttp3.Request
import org.apache.commons.exec.*
import java.io.ByteArrayOutputStream
import java.io.File
import kotlin.system.exitProcess

class GitRepo(orgName: String, projectName: String) {
    private val client = OkHttpClient()
    private val url = "https://api.github.com/repos/$orgName/$projectName"

    companion object {

        fun executeProcess(
            command: String,
            streamType: Stream = Stream.INPUT,
            timeoutSec: Long = 5L,
            directory: String = ""
        ): String {
            val cmdLine = CommandLine.parse(command)
            val outputStream = ByteArrayOutputStream()
            val errorStream = ByteArrayOutputStream()
            val executor = DefaultExecutor().also {
                it.watchdog = ExecuteWatchdog(timeoutSec * 1000)
                it.streamHandler = PumpStreamHandler(outputStream, errorStream)
                if (directory.isNotEmpty()) {
                    it.workingDirectory = File(directory)
                }
            }
            try {
                executor.execute(cmdLine)
            } catch (e: ExecuteException) {
                executor.watchdog.destroyProcess()
                return when (streamType) {
                    Stream.INPUT -> outputStream.toString()
                    Stream.ERROR -> errorStream.toString()
                    else -> "" + errorStream.toString()
                }
            }
            return when (streamType) {
                Stream.INPUT -> outputStream.toString()
                Stream.ERROR -> errorStream.toString()
                Stream.BOTH -> "OUTPUTSTREAM:\n$outputStream ERRORSTREAM:\n$errorStream"
            }
        }

        fun getFileOnRevision(commit: String, filePath: String) =
            executeProcess("git --git-dir ${CompilerArgs.pathToKotlin}.git show $commit:$filePath")

        fun getFileOnRevision(commit: String, pathToKotlinRepo: String, filePath: String) =
            executeProcess("git --git-dir $pathToKotlinRepo.git show $commit:$filePath")

//        fun getLocalPatches(commit: String, pathToKotlinRepo: String): List<FilePatch> {
//            val patches = executeProcess("git --git-dir $pathToKotlinRepo.git diff-tree -p $commit")
//            return patches.substringAfter(commit)
//                .trim()
//                .split("diff --git ")
//                .filter { it.isNotEmpty() }
//                .mapNotNull {
//                    it.split("\n")
//                        .let { patch ->
//                            patch.find { it.startsWith("+++") }?.substringAfter("/")?.let { fileName ->
//                                val fileQuery = "git --git-dir $pathToKotlinRepo/.git show $commit:$fileName"
//                                val fileText = executeProcess(fileQuery)
//                                FilePatch(
//                                    fileName,
//                                    fileText,
//                                    PatchParser.parsePatchesFromString(patch.joinToString("\n"))
//                                )
//                            }
//                        }
//                }
//        }

        fun getLocalPatches(commit: String, pathToKotlinRepo: String): List<FilePatch> {
            executeProcess("git checkout $commit", directory = pathToKotlinRepo)
            val resPatches = mutableListOf<FilePatch>()
            val patches = executeProcess("git diff-tree -p $commit", directory = pathToKotlinRepo)
            val modifiedFiles =
                patches.substringAfter(commit)
                    .trim()
                    .split("diff --git ")
                    .filter { it.isNotEmpty() }
                    .map { it.substringAfter("a/").substringBefore(' ') }
            for (f in modifiedFiles) {
                println("f = $f")
                val fileText = executeProcess("cat $f", directory = pathToKotlinRepo)
                val filePatches = mutableListOf<Patch>()
                val gitBlameResult = executeProcess("git blame -- $f", directory = pathToKotlinRepo)
                val addedLines = gitBlameResult.lines().filter { it.substringBefore(' ') == commit.take(12) }
                val lineNumbers = addedLines.map { it to it.substringBefore(") ").substringAfterLast(' ').toInt() }
                if (lineNumbers.isEmpty()) continue
                var prevLine = lineNumbers.first().second
                var c = 0
                for ((i, line) in lineNumbers.withIndex()) {
                    if (i == 0) continue
                    if (line.second - prevLine != 1) {
                        val newLine = lineNumbers[c].second
                        val numOfNewLines = i - c
                        val newLines = addedLines.subList(c, i).map { it.substringAfter(") ") }
                        val patch = Patch(newLine, numOfNewLines, newLine, numOfNewLines, listOf(), newLines)
                        filePatches.add(patch)
                        println("Patch from $c to ${i - 1}")
                        c = i
                    }
                    prevLine = line.second
                }
                val newLine = lineNumbers[c].second
                val numOfNewLines = lineNumbers.size - c
                val newLines = addedLines.subList(c, lineNumbers.size).map { it.substringAfter(") ") }
                val patch = Patch(newLine, numOfNewLines, newLine, numOfNewLines, listOf(), newLines)
                filePatches.add(patch)
                println("PATCH FROM $c to ${lineNumbers.size - 1}")
                resPatches.add(FilePatch(f, fileText, filePatches))
            }
//            exitProcess(0)
            return resPatches
        }
//        patches.substringAfter(commit)
//        .trim()
//        .split("diff --git ")
//        .filter { it.isNotEmpty() }
//        .map { it.substringAfter("a/").substringBefore(' ') }
    }

    @Deprecated("60 queries per hour limit, better use local repo")
    fun getPatchesFromGithub(commitSHA: String): List<FilePatch> {
        val request = Request.Builder().url("$url/commits/$commitSHA").build()
        val responseBody = client.newCall(request).execute().body ?: return listOf()
        val json = Json.parseToJsonElement(responseBody.string())
        val files = json.jsonObject["files"]?.jsonArray
        val sources =
            files
                ?.map { it.jsonObject["raw_url"]?.jsonPrimitive?.content ?: "" }
                ?.map {
                    it.substringAfter("$commitSHA/") to Request.Builder().url(it).build().let { req ->
                        client.newCall(req).execute().body?.string() ?: ""
                    }
                } ?: return listOf()
        val patches = files.map { it.jsonObject["patch"]?.jsonPrimitive?.content ?: "" }
        return sources.mapIndexed { index, s ->
            FilePatch(
                s.first,
                s.second,
                PatchParser.parsePatchesFromString(patches[index])
            )
        }
    }

}