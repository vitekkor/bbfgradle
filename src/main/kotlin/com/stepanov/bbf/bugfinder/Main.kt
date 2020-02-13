package com.stepanov.bbf.bugfinder

import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.util.*
import net.sourceforge.argparse4j.ArgumentParsers
import net.sourceforge.argparse4j.impl.Arguments
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.log4j.PropertyConfigurator
import java.io.File
import kotlin.random.Random
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

    val parser = ArgumentParsers.newFor("bbf").build()
    parser.addArgument("-r", "--reduce")
        .required(false)
        .help("Reduce mode")
    parser.addArgument("-f", "--fuzz")
        .required(false)
        .help("Fuzzing mode")
    parser.addArgument("-c", "--clean")
        .required(false)
        .action(Arguments.storeTrue())
        .help("Clean directories with bugs from bugs that are not reproduced")
    parser.addArgument("-d", "--database")
        .required(false)
        .action(Arguments.storeTrue())
        .help("Database updating")
    val arguments = parser.parseArgs(args)
    arguments.getString("reduce")?.let {
        //Fuck it
//        val type = BBFProperties.getStringGroupWithoutQuotes("BUG_FOR_REDUCE").entries.first().value
//        val backends = BBFProperties.getStringGroupWithoutQuotes("BACKEND_FOR_REDUCE").entries
//        val compilers = backends.map { back ->
//            when {
//                back.key.startsWith("JVM") -> JVMCompiler(back.value)
//                back.key.startsWith("JS") -> JSCompiler(back.value)
//                else -> throw IllegalArgumentException("Illegal backend")
//            }
//        }
//        val tmpPath = CompilerArgs.pathToTmpFile
//        require(!File(it).isDirectory) { "Specify file to reducing" }
//        File(tmpPath).writeText(File(it).readText())
//        val res = when (type) {
//            "DIFF_BEHAVIOR" -> Reducer.reduceDiffBehavior(tmpPath, compilers)
//            "BACKEND_CRASH" -> Reducer.reduce(tmpPath, compilers.first()).first().text
//            else -> throw IllegalArgumentException("Illegal type of bug")
//        }
//        println("Result of reducing:\n$res")
        exitProcess(0)
    }
    arguments.getString("fuzz")?.let {
        require(File(it).isDirectory) { "Specify directory to take files for mutation" }
        val file = File(it).listFiles()?.random() ?: throw IllegalArgumentException("Wrong directory")
        SingleFileBugFinder(file.absolutePath).findBugsInFile()
        exitProcess(0)
    }
    if (arguments.getString("database") == "true") {
        NodeCollector(CompilerArgs.baseDir).collect()
        exitProcess(0)
    }
    if (arguments.getString("clean") == "true") {
        FalsePositivesDeleter().cleanDirs()
        exitProcess(0)
    }
    ProjectBugFinder("tmp/arrays/classTests/").findBugsInProjects()
    //val file = (if (Random.nextInt(0, 10) in 0..2) File("${CompilerArgs.baseDir}/newTests").listFiles()?.random()
    //else File(CompilerArgs.baseDir).listFiles()?.random()) ?: throw IllegalArgumentException("Wrong directory")
    //SingleFileBugFinder(file.absolutePath).findBugsInFile()
    exitProcess(0)
}