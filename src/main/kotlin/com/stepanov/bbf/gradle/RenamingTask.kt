package com.stepanov.bbf.gradle

import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.net.URI
import java.nio.file.FileSystems
import java.nio.file.Paths
import java.util.*
import java.util.jar.JarFile
import kotlin.streams.toList

/* =(((((( */
data class Patch(val entry: String, val replace: String, val replacement: String)

fun getIntellijCorePath(): String {
    val itellijCoreVer =
        File("build.gradle").readText().lines().firstOrNull { it.trim().contains("intellij_core_version") }
            ?: throw Exception("Dont see kotlinVersion parameter in build.gradle file")
    val ver = itellijCoreVer.split("=").last().trim().filter { it != '\'' }
    val gradleDir =
        "${System.getProperty("user.home")}/.gradle/caches/modules-2/files-2.1/com.android.tools.external.com-intellij/intellij-core/"
    return File("$gradleDir/$ver").walkTopDown().find { it.name == "intellij-core-$ver.jar" }?.absolutePath ?: ""
}

fun main() {
    for (patch in patches) {
        val corePath = getIntellijCorePath()
        val coreJar = JarFile(corePath)
        val neededEntry = coreJar.getJarEntry(patch.entry)
        val inputStream = coreJar.getInputStream(neededEntry)
        val text = BufferedReader(InputStreamReader(inputStream)).lines().toList()
        val res = text.joinToString("\n") {
            if (it.trim() == patch.replace) patch.replacement else it
        }
        //
        val env = HashMap<String, String>()
        env.put("create", "true")
        println(corePath)
        val zipfs = FileSystems.newFileSystem(URI.create(corePath), env)
        println(zipfs)
    }
}

val patches = mutableListOf(
    Patch(
        "META-INF/services/org.jetbrains.kotlin.diagnostics.rendering.DefaultErrorMessages\$Extension",
        "org.jetbrains.kotlin.fir.resolve.diagnostics.DefaultErrorMessagesFir",
        "org.jetbrains.kotlin.fir.analysis.diagnostics.FirDefaultErrorMessages"
    )
)