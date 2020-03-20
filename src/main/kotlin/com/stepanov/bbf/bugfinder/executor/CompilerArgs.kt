package com.stepanov.bbf.bugfinder.executor

import org.apache.commons.io.IOUtils
import java.io.File
import java.util.*
import java.util.jar.JarFile

object CompilerArgs {

    private val file: File = File("bbf.conf")

    fun getPropValue(name: String): String? {
        val props = Properties()
        props.load(file.inputStream())
        return props.getProperty(name)
    }

    fun getPropValueWithoutQuotes(name: String): String {
        val props = Properties()
        props.load(file.inputStream())
        val prop = props.getProperty(name) ?: throw IllegalArgumentException("Cannot init $name property")
        val res = prop.drop(1).dropLast(1)
        return res
    }

    fun getPropAsBoolean(name: String): Boolean = getPropValue(name)?.toBoolean()
        ?: throw IllegalArgumentException("Cannot init $name property")

    fun getStdLibPath(libToSearch: String): String {
        val kotlinVersion =
            File("build.gradle").readText().lines().firstOrNull { it.trim().contains("kotlin_version") }
                ?: throw Exception("Dont see kotlinVersion parameter in build.gradle file")
        val ver = kotlinVersion.split("=").last().trim().filter { it != '\'' }
        val gradleDir = "${System.getProperty("user.home")}/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/"
        val dir =
            File("$gradleDir/$libToSearch").listFiles()?.find { it.isDirectory && it.name.trim() == ver }?.path ?: ""
        val pathToLib = File(dir).walkTopDown().find { it.name == "$libToSearch-$ver.jar" }?.absolutePath ?: ""
        require(pathToLib.isNotEmpty())
        return pathToLib
    }

    fun getAnnoPath(ver: String): String {
        val gradleDir = "${System.getProperty("user.home")}/.gradle/caches/modules-2/files-2.1/org.jetbrains/"
        val dir =
            File("$gradleDir/annotations").listFiles()?.find { it.isDirectory && it.name.trim() == ver }?.path ?: ""
        val pathToLib = File(dir).walkTopDown().find { it.name == "annotations-$ver.jar" }?.absolutePath ?: ""
        require(pathToLib.isNotEmpty())
        return pathToLib
    }

    private fun findAndSaveLib(name: String, jarFile: JarFile) {
        val lib = jarFile.entries().asSequence().first { it.name == name }
        val input = jarFile.getInputStream(lib)
        val content = IOUtils.toString(input, "UTF-8")
        File("tmp/lib/").mkdirs()
        File("tmp/lib/$name").writeText(content)
    }

    fun createJSLib(): String {
        val pathToJar = getStdLibPath("kotlin-stdlib-js")
        val jarFile = JarFile(File(pathToJar))
        findAndSaveLib("kotlin.js", jarFile)
        findAndSaveLib("kotlin.meta.js", jarFile)
        return "${System.getProperty("user.dir")}/tmp/lib/"
    }

    val baseDir = getPropValueWithoutQuotes("MUTATING_DIR")
    val javaBaseDir = getPropValueWithoutQuotes("JAVA_FILES_DIR")
    val dirForNewTests = "$baseDir/newTests"

    //PATHS TO COMPILERS
    val pathToJsKotlinLib = createJSLib()
    val pathToTmpFile = getPropValueWithoutQuotes("TMPFILE")

    //RESULT
    val resultsDir = getPropValueWithoutQuotes("RESULTS")

    //ORACLE
    val useJavaAsOracle = getPropAsBoolean("USE_JAVA_AS_ORACLE")

    //MUTATED BUGS
    val shouldSaveCompilerBugs =
        getPropAsBoolean("SAVE_BACKEND_EXCEPTIONS")
    val shouldReduceCompilerBugs =
        getPropAsBoolean("REDUCE_BACKEND_EXCEPTIONS")
    val shouldSaveMutatedFiles = getPropAsBoolean("SAVE_MUTATED_FILES")
    val shouldSaveCompileDiff = getPropAsBoolean("SAVE_COMPILER_DIFFS")
    val shouldReduceDiffBehavior =
        getPropAsBoolean("REDUCE_DIFF_BEHAVIOR")
    val shouldReduceDiffCompile =
        getPropAsBoolean("REDUCE_DIFF_COMPILE")

    //REDUKTOR
    val shouldFilterDuplicateCompilerBugs =
        getPropAsBoolean("FILTER_DUPLICATES")

    //JAVA
    val jdkHome = System.getenv("JAVA_HOME")
    val jvmTarget = "1.8"
    val classpath = ""

    //STDLIB
    val jvmStdLibPaths = listOf(
        getStdLibPath("kotlin-stdlib"), getStdLibPath("kotlin-stdlib-common"),
        getStdLibPath("kotlin-test"), getStdLibPath("kotlin-test-common"), getStdLibPath("kotlin-reflect")
    )

    val jsStdLibPaths = listOf(
        getStdLibPath("kotlin-stdlib-js"), getStdLibPath("kotlin-stdlib-common")
        //,getStdLibPath("kotlin-test-js"), getStdLibPath("kotlin-test-common"), getStdLibPath("kotlin-reflect")
    )
}