package com.stepanov.bbf.bugfinder

import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.executor.checkers.TracesChecker
import com.stepanov.bbf.bugfinder.executor.compilers.JSCompiler
import com.stepanov.bbf.bugfinder.executor.compilers.JVMCompiler
import com.stepanov.bbf.bugfinder.executor.compilers.KJCompiler
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.util.addAtTheEnd
import com.stepanov.bbf.bugfinder.util.debugPrint
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.log4j.PropertyConfigurator
import org.jetbrains.kotlin.psi.KtDotQualifiedExpression
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtSimpleNameExpression
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
    for (f in File("/home/zver/IdeaProjects/kotlinBugs/diffBehavior/tmp/").listFiles()) {
        println("NAME = ${f.name}")
        val project = Project.createFromCode(f.readText())
        val backends = f.readText().split("\n")
            .first { it.startsWith("// Bug happens on ") }
            .substringAfter("// Bug happens on ").split(", ").map { it.trim() }
            .map {
                var (backend, args) = it.substringBefore(" ") to it.substringAfter(" ")
                if (args.trim() == backend.trim()) args = ""
                when (backend) {
                    "JVM" -> JVMCompiler(args)
                    "KJVM" -> KJCompiler(args)
                    else -> JSCompiler(args)
                }
            }
        val tracer = TracesChecker(backends)
        val res = tracer.checkBehavior(project, false)
        //if (res) f.delete()
    }
    exitProcess(0)

//    val files =
//        Files.walk(Paths.get("/home/zver/IdeaProjects/kotlin/compiler/testData/codegen/box/syntheticAccessors/"))
//            .map { it.toFile() }.toArray().toList().map { it as File }
//    for (f in files.filter { it.path.endsWith("kt") }) {
//        val p = Project.createFromCode(f.readText())
//        CoverageGuider.init("", p)
//    }
//    exitProcess(0)
//    ProjectInstrumenter.instrument()
//    exitProcess(0)
//    val file = File(CompilerArgs.baseDir).listFiles()?.filter { it.path.endsWith(".kt") }?.random() ?: exitProcess(0)
//    SingleFileBugFinder(files.random().absolutePath).findBugsInFile()
//    exitProcess(0)
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

//fun checkDiffBehTests() {
//
//    for (f in File("/home/zver/IdeaProjects/kotlinBugs/diffBehavior/tmp/").listFiles()) {
//        println("NAME = ${f.name}")
//        val project = Project.createFromCode(f.readText())
//        val backends = f.readText().split("\n")
//            .first { it.startsWith("// Bug happens on ") }
//            .substringAfter("// Bug happens on ").split(", ").map { it.trim() }
//            .map {
//                var (backend, args) = it.substringBefore(" ") to it.substringAfter(" ")
//                if (args.trim() == backend.trim()) args = ""
//                when (backend) {
//                    "JVM" -> JVMCompiler(args)
//                    "KJVM" -> KJCompiler(args)
//                    else -> JSCompiler(args)
//                }
//            }
//        val tracer = TracesChecker(backends)
//        tracer.checkBehavior(project, false)
//    }
//    exitProcess(0)
//}