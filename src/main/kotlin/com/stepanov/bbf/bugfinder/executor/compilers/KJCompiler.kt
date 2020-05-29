package com.stepanov.bbf.bugfinder.executor.compilers

import com.stepanov.bbf.bugfinder.util.decompiler.copyContentTo
import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.executor.CompilingResult
import com.stepanov.bbf.bugfinder.manager.Bug
import com.stepanov.bbf.bugfinder.manager.BugManager
import com.stepanov.bbf.bugfinder.manager.BugType
import com.stepanov.bbf.bugfinder.util.Stream
import java.io.File
import java.nio.charset.Charset
import java.util.zip.ZipFile
import javax.tools.DiagnosticCollector
import javax.tools.JavaFileObject
import javax.tools.ToolProvider


class KJCompiler(override val arguments: String = "") : JVMCompiler(arguments) {
    override val compilerInfo: String
        get() = "KJVM $arguments"

    override val pathToCompiled: String
        get() = "tmp/tmp.jar"

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

    fun compileJava(path: String, pathToLib: String, pathToDir: String): Boolean {
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
        return commonExec("java -cp $path MainKt", streamType)
    }

    val pathToTmpDir = CompilerArgs.pathToTmpFile.substringBeforeLast('/') + "/tmp/tmp"
}