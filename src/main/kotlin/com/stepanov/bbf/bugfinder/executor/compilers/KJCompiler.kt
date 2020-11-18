package com.stepanov.bbf.bugfinder.executor.compilers

import com.stepanov.bbf.bugfinder.util.decompiler.copyContentTo
import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.executor.CompilingResult
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.manager.Bug
import com.stepanov.bbf.bugfinder.manager.BugManager
import com.stepanov.bbf.bugfinder.manager.BugType
import com.stepanov.bbf.bugfinder.util.Stream
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths
import java.util.jar.JarOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import javax.tools.DiagnosticCollector
import javax.tools.JavaFileObject
import javax.tools.ToolProvider
import kotlin.streams.toList


class KJCompiler(override val arguments: String = "") : JVMCompiler(arguments) {
    override val compilerInfo: String
        get() = "KJVM $arguments"

    override var pathToCompiled: String = "tmp/tmp.jar"

    override fun compile(project: Project, includeRuntime: Boolean): CompilingResult {
        val projectWithMain = project.addMain()
        val kotlinCompiled = super.compile(projectWithMain, includeRuntime)
        if (kotlinCompiled.status == -1) return CompilingResult(-1, "")
        val path = projectWithMain.saveOrRemoveToTmp(true)
        val kotlinJar = ZipFile(kotlinCompiled.pathToCompiled, Charset.forName("CP866"))
        kotlinJar.copyContentTo(pathToTmpDir)
        val javaRes = compileJava(path, kotlinCompiled.pathToCompiled, pathToTmpDir)
        File(kotlinCompiled.pathToCompiled).let { if (it.exists()) it.delete() }
        projectWithMain.saveOrRemoveToTmp(false)
        return if (javaRes) {
            CompilingResult(0, pathToTmpDir)
        } else {
            CompilingResult(-1, "")
        }
    }

    fun compileAndSaveInJar(project: Project, includeRuntime: Boolean = true): String {
        val path = compile(project, includeRuntime)
        val jar = JarOutputStream(FileOutputStream(pathToCompiled))//File(pathToCompiled)
        for (f in File(path.pathToCompiled).listFiles().filter { it.isFile }) {
            println(f.name)
            jar.putNextEntry(ZipEntry(f.name))
            val t = f.readText(Charset.forName("CP866")).toByteArray(Charset.forName("CP866"))
            //println("T = $t")
            jar.write(t, 0, t.size)
            jar.closeEntry()
        }
        jar.close()
        return pathToCompiled
    }

//    fun compile(path: String, includeRuntime: Boolean): CompilingResult {
//        val kotlinCompiled = super.compile(path, includeRuntime)
//        if (kotlinCompiled.status == -1) return CompilingResult(-1, "")
//        File(pathToTmpDir).deleteRecursively()
////        File(pathToTmpDir).listFiles()
////            .filter { !it.absolutePath.endsWith(".java") && !it.absolutePath.endsWith(".kt") }
////            .forEach { it.deleteRecursively() }
//        val kotlinJar = ZipFile(kotlinCompiled.pathToCompiled, Charset.forName("CP866"))
//        kotlinJar.copyContentTo(pathToTmpDir)
//        val javaRes = compileJava(path, kotlinCompiled.pathToCompiled, pathToTmpDir)
//        File(kotlinCompiled.pathToCompiled).let { if (it.exists()) it.delete() }
//        return if (javaRes) {
//            CompilingResult(0, pathToTmpDir)
//        } else {
//            CompilingResult(-1, "")
//        }
//    }

    private fun compileJava(path: String, pathToLib: String, pathToDir: String): Boolean {
        val javaFiles = path.split(" ").filter { it.endsWith(".java") }.map { File(it) }
        if (javaFiles.isEmpty()) return true
        val compiler = ToolProvider.getSystemJavaCompiler()
        val diagnostics = DiagnosticCollector<JavaFileObject>()
        val manager = compiler.getStandardFileManager(diagnostics, null, null)
        val sources = manager.getJavaFileObjectsFromFiles(javaFiles)
        val classPath = (CompilerArgs.jvmStdLibPaths + CompilerArgs.getAnnoPath("13.0") + pathToDir).joinToString(":")
        val options = mutableListOf("-classpath", classPath, "-d", pathToDir)
        val task = compiler.getTask(null, manager, diagnostics, options, null, sources)
        task.call()
        return diagnostics.diagnostics.isEmpty()
    }

//    override fun checkCompiling(pathToFile: String): Boolean {
//        val status = compile(pathToFile, false).status
//        File(pathToTmpDir).deleteRecursively()
//        return status == 0
//    }

    override fun exec(path: String, streamType: Stream): String {
        val manifest = Files.walk(Paths.get(path)).toList().map { it.toFile() }.find { it.name == "MANIFEST.MF" }
            ?: return ""
        val mainClass = manifest.readLines().find { it.startsWith("Main-Class:") }?.substringAfter("Main-Class: ")
            ?: return ""
        val res =  commonExec("java -cp $path $mainClass", streamType)
        File(path).deleteRecursively()
        return res
    }

    val pathToTmpDir = CompilerArgs.pathToTmpFile.substringBeforeLast('/') + "/tmp/tmp"
}