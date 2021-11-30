package com.stepanov.bbf.bugfinder.gitinfocollector

import com.stepanov.bbf.bugfinder.executor.CommonCompiler
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

    private fun executeProcess(command: String, streamType: Stream = Stream.INPUT, timeoutSec: Long = 5L): String {
        val cmdLine = CommandLine.parse(command)
        val outputStream = ByteArrayOutputStream()
        val errorStream = ByteArrayOutputStream()
        val executor = DefaultExecutor().also {
            it.watchdog = ExecuteWatchdog(timeoutSec * 1000)
            it.streamHandler = PumpStreamHandler(outputStream, errorStream)
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

    fun getLocalPatches(commit: String, pathToKotlinRepo: String): List<FilePatch> {
        val patches = executeProcess("git --git-dir $pathToKotlinRepo/.git diff-tree -p $commit")
        return patches.substringAfter(commit)
            .trim()
            .split("diff --git ")
            .filter { it.isNotEmpty() }
            .map {
                it.split("\n")
                    .let { patch ->
                        val fileName = patch.find { it.startsWith("+++") }!!.substringAfter("/")
                        val fileQuery = "git --git-dir $pathToKotlinRepo/.git show $commit:$fileName"
                        val fileText = executeProcess(fileQuery)
                        FilePatch(fileName, fileText, PatchParser.parsePatchesFromString(patch.joinToString("\n")))
                    }
            }
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