package com.stepanov.bbf.bugfinder.executor.compilers

import com.stepanov.bbf.bugfinder.decompiler.copyContentTo
import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.executor.CompilingResult
import com.stepanov.bbf.bugfinder.executor.Project
import com.stepanov.bbf.bugfinder.manager.Bug
import com.stepanov.bbf.bugfinder.manager.BugManager
import com.stepanov.bbf.bugfinder.manager.BugType
import com.stepanov.bbf.bugfinder.util.Stream
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.zip.ZipFile
import javax.tools.DiagnosticCollector
import javax.tools.JavaFileObject
import javax.tools.ToolProvider


class KJCompiler(override val arguments: String = "") : JVMCompiler(arguments) {
    override val compilerInfo: String
        get() = "JVM $arguments"

    override val pathToCompiled: String
        get() = "tmp/tmp.jar"

    override fun compile(path: String, includeRuntime: Boolean): CompilingResult {
        val kotlinCompiled = super.compile(path, includeRuntime)
        if (isCompilerBug(path)) BugManager.saveBug(
            Bug(
                this,
                "",
                Project(path.split(" ").map { File(it).readText() }),
                BugType.BACKEND
            )
        )
        println("Kt compile status = $kotlinCompiled\nmsg = ${getErrorMessage(path)}")
        if (kotlinCompiled.status == -1) return CompilingResult(-1, "")
        val pathToTmpDir = CompilerArgs.pathToTmpFile.substringBeforeLast('/') + "/tmp"
        File(pathToTmpDir).deleteRecursively()
        val kotlinJar = ZipFile(kotlinCompiled.pathToCompiled)
        kotlinJar.copyContentTo(pathToTmpDir)
        compileJava(path, kotlinCompiled.pathToCompiled, pathToTmpDir)
        return CompilingResult(0, pathToTmpDir)
    }

    fun compileJava(path: String, pathToLib: String, pathToDir: String) {
        val javaFiles = path.split(" ").filter { it.endsWith(".java") }.map { File(it) }
        if (javaFiles.size == 0) return
        val compiler = ToolProvider.getSystemJavaCompiler()
        val diagnostics = DiagnosticCollector<JavaFileObject>()
        val manager = compiler.getStandardFileManager(diagnostics, null, null)
        val sources = manager.getJavaFileObjectsFromFiles(javaFiles)
        val classPath = (CompilerArgs.jvmStdLibPaths + CompilerArgs.getAnnoPath("13.0") + pathToDir).joinToString(":")
        val options = mutableListOf("-classpath", classPath, "-d", pathToDir)
        val task = compiler.getTask(null, manager, diagnostics, options, null, sources)
        task.call()
        if (diagnostics.diagnostics.size == 0) {
            println("OK")
        } else {
            diagnostics.diagnostics.forEach {
                println(it.getMessage(null))
            }
        }
    }

    override fun exec(path: String, streamType: Stream): String {
        return commonExec("java -cp $path MainKt", streamType)
    }
}