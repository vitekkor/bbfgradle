package com.stepanov.bbf.bugfinder

import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.executor.checkers.CoverageGuider
import com.stepanov.bbf.bugfinder.executor.compilers.JVMCompiler
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.util.CoverageStatisticsCollector
import com.stepanov.bbf.bugfinder.util.instrumentation.CoverageGuidingCoefficients
import com.stepanov.bbf.bugfinder.util.instrumentation.JarSourceCodeInstrumenter
import coverage.MyMethodBasedCoverage
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.log4j.PropertyConfigurator
import java.io.File
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    //Init log4j
    PropertyConfigurator.configure("src/main/resources/bbfLog4j.properties")
    if (!CompilerArgs.getPropAsBoolean("LOG")) {
        Logger.getRootLogger().level = Level.OFF
        Logger.getLogger("bugFinderLogger").level = Level.OFF
        Logger.getLogger("mutatorLogger").level = Level.OFF
        Logger.getLogger("reducerLogger").level = Level.OFF
        Logger.getLogger("transformationManagerLog").level = Level.OFF
    }
//    instrumentJars()
//    exitProcess(0)
//    JarSourceCodeInstrumenter().instrument()
    val filePath =
        if (args.isNotEmpty()) args[0]
        else
            File("/home/zver/IdeaProjects/bbfgradle/tmp/arrays2/")
                .listFiles()
                ?.filter { it.path.endsWith(".kt") }
                ?.randomOrNull()?.absolutePath ?: exitProcess(0)
    SingleFileBugFinder("tmp/myTest.kt").findBugsInFile()
    exitProcess(0)
//    val parser = ArgumentParsers.newFor("bbf").build()
//    parser.addArgument("-r", "--reduce")
//        .required(false)
//        .help("Reduce mode")
//    parser.addArgument("-f", "--fuzz")
//        .required(false)
//        .help("Fuzzing mode")
//    parser.addArgument("-c", "--clean")
//        .required(false)
//        .action(Arguments.storeTrue())
//        .help("Clean directories with bugs from bugs that are not reproduced")
//    parser.addArgument("-d", "--database")
//        .required(false)
//        .action(Arguments.storeTrue())
//        .help("Database updating")
//    val arguments = parser.parseArgs(args)
//    arguments.getString("reduce")?.let {
//        //TODO
//        exitProcess(0)
//    }
//    arguments.getString("fuzz")?.let {
//        require(File(it).isDirectory) { "Specify directory to take files for mutation" }
//        val file = File(it).listFiles()?.random() ?: throw IllegalArgumentException("Wrong directory")
//        SingleFileBugFinder(file.absolutePath).findBugsInFile()
//        exitProcess(0)
//    }
//    if (arguments.getString("database") == "true") {
//        NodeCollector(CompilerArgs.baseDir).collect()
//        exitProcess(0)
//    }
//    if (arguments.getString("clean") == "true") {
//        FalsePositivesDeleter().cleanDirs()
//        exitProcess(0)
//    }
}