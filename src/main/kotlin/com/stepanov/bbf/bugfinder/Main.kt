package com.stepanov.bbf.bugfinder

import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.executor.checkers.CoverageGuider
import com.stepanov.bbf.bugfinder.util.instrumentation.Instrumenter.instrumentJars
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
//    for (f in File("/home/zver/IdeaProjects/bbfgradle/tmp/results/unsignedNumber/notGuided/diffBehavior/").listFiles()) {
//        //if (f.name != "mimocnw_FILE.kt") continue
//        println("f = ${f.name}")
//        val p = Project.createFromCode(f.readText())
//        for (c in CompilerArgs.getCompilersList()) {
//            val compiled = c.compile(p)
//            val execRes = c.exec(compiled.pathToCompiled, Stream.BOTH)
//            println("exec res of $c is:\n$execRes")
//            println("--------")
//        }
//        println("-----------------------------------------")
//    }
//    exitProcess(0)
//    val pathToCoverage = "tmp/coverage.txt"
//    CoverageGuider.initDesireCoverage(pathToCoverage) { !"$it".contains("konan") && !"$it".contains("fir") }
//    val p1 = Project.createFromCode(File("tmp/myTest.kt").readText())
//    val p2 = Project.createFromCode(File("tmp/myTest1.kt").readText())
//    val cov1 = CoverageGuider.getCoverage(p1)
//    val cov2 = CoverageGuider.getCoverage(p2)
//    println()
//    exitProcess(0)
//    val sumCoverage = mutableSetOf<CoverageEntry>()
//    for (f in File("/home/zver/IdeaProjects/bbfgradle/tmp/arrays2/").listFiles()!!.filter { it.path.endsWith(".kt") }) {
//        println("HANDLE ${f.name}")
//        val p = Project.createFromCode(f.readText())
//        val coverage = CoverageGuider.getCoverage(p, CompilerArgs.getCompilersList())
//        CoverageStatisticsCollector.addCoveredMethods(coverage.keys)
//        sumCoverage.addAll(coverage.keys)
//    }
//    println()
//    exitProcess(0)
    //CoverageGuider.initDesireCoverage("tmp/coverage.txt")  { !"$it".contains("konan") && !"$it".contains("fir") }
    //exitProcess(0)
    instrumentJars()
    exitProcess(0)
//    JarSourceCodeInstrumenter().instrument()
    val filePath =
        if (args.isNotEmpty()) args[0]
        else
            File("tmp/arrays3/")
                .listFiles()
                ?.filter { it.path.endsWith(".kt") }
                ?.randomOrNull()?.absolutePath ?: exitProcess(0)
    SingleFileBugFinder(filePath).findBugsInFile()
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