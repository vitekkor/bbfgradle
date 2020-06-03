package com.stepanov.bbf.bugfinder.executor.compilers

import com.stepanov.bbf.bugfinder.executor.CommonCompiler
import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.executor.CompilingResult
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.util.Stream
import com.stepanov.bbf.bugfinder.util.copyFullJarImpl
import com.stepanov.bbf.bugfinder.util.writeRuntimeToJar
import com.stepanov.bbf.reduktor.executor.KotlincInvokeStatus
import com.stepanov.bbf.reduktor.util.MsgCollector
import org.apache.commons.io.FileUtils
import org.apache.log4j.Logger
import org.jetbrains.kotlin.cli.common.arguments.K2JVMCompilerArguments
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageLocation
import org.jetbrains.kotlin.cli.jvm.K2JVMCompiler
import org.jetbrains.kotlin.config.IncrementalCompilation
import org.jetbrains.kotlin.config.Services
import java.io.File
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import java.util.jar.JarInputStream
import java.util.jar.JarOutputStream

open class JVMCompiler(open val arguments: String = "") : CommonCompiler() {
    override val compilerInfo: String
        get() = "JVM $arguments"

    override val pathToCompiled: String
        get() = "tmp/tmp.jar"


    override fun checkCompiling(project: Project): Boolean {
        val status = tryToCompile(project)
        return !MsgCollector.hasCompileError && !status.hasTimeout && !MsgCollector.hasException
    }

    override fun getErrorMessageWithLocation(project: Project): Pair<String, List<CompilerMessageLocation>> {
        val status = tryToCompile(project)
        return status.combinedOutput to status.locations
    }

    override fun isCompilerBug(project: Project): Boolean =
        tryToCompile(project).hasException

    override fun compile(project: Project, includeRuntime: Boolean): CompilingResult {
        val projectWithMainFun = project.addMain()
        val path = projectWithMainFun.saveOrRemoveToTmp(true)
        val tmpJar = "$pathToCompiled.jar"
        val args = prepareArgs(projectWithMainFun, path, tmpJar)
        val status = executeCompiler(projectWithMainFun, args)
        if (status.hasException || status.hasTimeout || !status.isCompileSuccess) return CompilingResult(-1, "")
        val res = File(pathToCompiled)
        val input = JarInputStream(File(tmpJar).inputStream())
        val output = JarOutputStream(res.outputStream(), input.manifest)
        copyFullJarImpl(output, File(tmpJar))
        if (includeRuntime)
            CompilerArgs.jvmStdLibPaths.forEach { writeRuntimeToJar(it, output) }
        output.finish()
        input.close()
        File(tmpJar).delete()
        return CompilingResult(0, pathToCompiled)
    }

    private fun prepareArgs(project: Project, path: String, destination: String): K2JVMCompilerArguments {
        val destFile = File(destination)
        if (destFile.isFile) destFile.delete()
        else if (destFile.isDirectory) FileUtils.cleanDirectory(destFile)
        val projectArgs = project.getProjectSettingsAsCompilerArgs("JVM") as K2JVMCompilerArguments
        val compilerArgs =
            if (arguments.isEmpty())
                "$path -d $destination".split(" ")
            else
                "$path $arguments -d $destination".split(" ")
        projectArgs.apply { K2JVMCompiler().parseArguments(compilerArgs.toTypedArray(), this) }
        projectArgs.classpath =
            "${CompilerArgs.jvmStdLibPaths.joinToString(
                postfix = ":",
                separator = ":"
            )}:${System.getProperty("java.class.path")}"
        return projectArgs
    }

    private fun executeCompiler(project: Project, args: K2JVMCompilerArguments): KotlincInvokeStatus {
        val compiler = K2JVMCompiler()
        val services = Services.EMPTY
        MsgCollector.clear()
        val threadPool = Executors.newCachedThreadPool()
        val futureExitCode = threadPool.submit {
            compiler.exec(MsgCollector, services, args)
        }
        var hasTimeout = false
        try {
            futureExitCode.get(10L, TimeUnit.SECONDS)
        } catch (ex: TimeoutException) {
            hasTimeout = true
            futureExitCode.cancel(true)
        } finally {
            project.saveOrRemoveToTmp(false)
        }
        val status = KotlincInvokeStatus(
            MsgCollector.crashMessages.joinToString("\n") +
                    MsgCollector.compileErrorMessages.joinToString("\n"),
            !MsgCollector.hasCompileError,
            MsgCollector.hasException,
            hasTimeout,
            MsgCollector.locations.toMutableList()
        )
        return status
    }

    override fun tryToCompile(project: Project): KotlincInvokeStatus {
        val path = project.saveOrRemoveToTmp(true)
        val trashDir = "${CompilerArgs.pathToTmpDir}/trash/"
        val args = prepareArgs(project, path, trashDir)
        return executeCompiler(project, args)
    }

    override fun exec(path: String, streamType: Stream): String = commonExec("java -jar $path", streamType)

    private fun analyzeErrorMessage(msg: String): Boolean = !msg.split("\n").any { it.contains(": error:") }

    private val log = Logger.getLogger("compilerErrorsLog")
}



//    override fun compile(path: String, includeRuntime: Boolean): CompilingResult {
//        val threadPool = Executors.newCachedThreadPool()
//        val tmpJar = "$pathToCompiled.jar"
//
//        val args = if (arguments.isEmpty())
//            "$path -d $tmpJar"
//        else
//            "$path $arguments -d $tmpJar"
//        val compiler = K2JVMCompiler()
//        val compilerArgs =
//            K2JVMCompilerArguments().apply { K2JVMCompiler().parseArguments(args.split(" ").toTypedArray(), this) }
//        if (CompilerArgs.classpath.isNotEmpty())
//            compilerArgs.classpath = CompilerArgs.classpath
//        else
//            compilerArgs.classpath =
//                "${CompilerArgs.jvmStdLibPaths.joinToString(
//                    postfix = ":",
//                    separator = ":"
//                )}:${System.getProperty("java.class.path")}"
//
//        compilerArgs.jdkHome = CompilerArgs.jdkHome
//        compilerArgs.jvmTarget = CompilerArgs.jvmTarget
//        compilerArgs.optIn = arrayOf("kotlin.ExperimentalStdlibApi")
//        IncrementalCompilation.setIsEnabledForJvm(true)
//
//        val services = Services.EMPTY
//        MsgCollector.clear()
//        val futureExitCode = threadPool.submit {
//            compiler.exec(MsgCollector, services, compilerArgs)
//        }
//        var hasTimeout = false
//        try {
//            futureExitCode.get(10L, TimeUnit.SECONDS)
//        } catch (ex: TimeoutException) {
//            hasTimeout = true
//            futureExitCode.cancel(true)
//        }
//
//        val status = KotlincInvokeStatus(
//            MsgCollector.crashMessages.joinToString("\n") +
//                    MsgCollector.compileErrorMessages.joinToString("\n"),
//            !MsgCollector.hasCompileError,
//            MsgCollector.hasException,
//            hasTimeout
//        )
//        if (status.hasException || status.hasTimeout || !status.isCompileSuccess) return CompilingResult(-1, "")
//        val res = File(pathToCompiled)
//        val input = JarInputStream(File(tmpJar).inputStream())
//        val output = JarOutputStream(res.outputStream(), input.manifest)
//        copyFullJarImpl(output, File(tmpJar))
//        if (includeRuntime)
//            CompilerArgs.jvmStdLibPaths.forEach { writeRuntimeToJar(it, output) }
//        output.finish()
//        input.close()
//        File(tmpJar).delete()
//        return CompilingResult(0, pathToCompiled)
//    }

//    override fun tryToCompile(string: String): KotlincInvokeStatus {
//        val threadPool = Executors.newCachedThreadPool()
//        val trashDir = "tmp/trash/"
//        //Clean dir
//        if (File(trashDir).exists())
//            FileUtils.cleanDirectory(File(trashDir))
//        return KotlincInvokeStatus("", true, true, true)
//    }


//    override fun tryToCompile(pathToFile: String): KotlincInvokeStatus {
//        val threadPool = Executors.newCachedThreadPool()
//        val trashDir = "tmp/trash/"
//        //Clean dir
//        if (File(trashDir).exists())
//            FileUtils.cleanDirectory(File(trashDir))
//        val args =
//            if (arguments.isEmpty())
//                "$pathToFile -d $trashDir".split(" ")
//            else
//                "$pathToFile $arguments -d $trashDir".split(" ")
//        val compiler = K2JVMCompiler()
//        val compilerArgs = K2JVMCompilerArguments().apply { K2JVMCompiler().parseArguments(args.toTypedArray(), this) }
//        if (CompilerArgs.classpath.isNotEmpty())
//            compilerArgs.classpath = CompilerArgs.classpath
//        else
//            compilerArgs.classpath =
//                "${CompilerArgs.jvmStdLibPaths.joinToString(
//                    postfix = ":",
//                    separator = ":"
//                )}:${System.getProperty("java.class.path")}"
//
//        compilerArgs.jdkHome = CompilerArgs.jdkHome
//        compilerArgs.jvmTarget = CompilerArgs.jvmTarget
//        compilerArgs.optIn = arrayOf("kotlin.ExperimentalStdlibApi")
//
//        IncrementalCompilation.setIsEnabledForJvm(true)
//
//        val services = Services.EMPTY
//        MsgCollector.clear()
//        val futureExitCode = threadPool.submit {
//            compiler.exec(MsgCollector, services, compilerArgs)
//        }
//        var hasTimeout = false
//        try {
//            futureExitCode.get(10L, TimeUnit.SECONDS)
//        } catch (ex: TimeoutException) {
//            hasTimeout = true
//            futureExitCode.cancel(true)
//        }
//        val status = KotlincInvokeStatus(
//            MsgCollector.crashMessages.joinToString("\n") +
//                    MsgCollector.compileErrorMessages.joinToString("\n"),
//            !MsgCollector.hasCompileError,
//            MsgCollector.hasException,
//            hasTimeout,
//            MsgCollector.locations.toMutableList()
//        )
//        return status
//    }
