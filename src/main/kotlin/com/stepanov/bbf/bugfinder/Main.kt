package com.stepanov.bbf.bugfinder

import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.executor.checkers.MutationChecker
import com.stepanov.bbf.bugfinder.executor.checkers.TracesChecker
import com.stepanov.bbf.bugfinder.executor.compilers.JSCompiler
import com.stepanov.bbf.bugfinder.executor.compilers.JVMCompiler
import com.stepanov.bbf.bugfinder.executor.compilers.KJCompiler
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.manager.Bug
import com.stepanov.bbf.bugfinder.manager.BugManager
import com.stepanov.bbf.bugfinder.manager.BugType
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.mutator.transformations.Transformation
import com.stepanov.bbf.bugfinder.util.FilterDuplcatesCompilerErrors
import com.stepanov.bbf.bugfinder.util.addAtTheEnd
import com.stepanov.bbf.bugfinder.util.debugPrint
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.parser.PSICreator
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.log4j.PropertyConfigurator
import org.jetbrains.kotlin.psi.KtDotQualifiedExpression
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtProperty
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

    val file = File(CompilerArgs.baseDir).listFiles()?.filter { it.path.endsWith(".kt") }?.random() ?: exitProcess(0)
    SingleFileBugFinder(file.absolutePath).findBugsInFile()
    exitProcess(0)
//    val log = Logger.getLogger("bugFinderLogger")
//    val fileToErrorMsg = mutableMapOf<String, String>()
//    for (f in File("/home/zver/IdeaProjects/kotlinBugs/JVM/").listFiles()){
//        println("f = $f")
//        if (f.name.contains("ORIGINAL")) continue
//        val p = Project.createFromCode(f.readText())
//        val errorMsg = JVMCompiler().getErrorMessage(p)
//        fileToErrorMsg[f.name] = errorMsg
//    }
//    val asList = fileToErrorMsg.toList()
//    for (i in asList.indices) {
//        println("I = $i")
//        val msg1 = asList[i].second
//        for (j in i + 1 until asList.size) {
//            println("j = $j")
//            val msg2 = asList[j].second
//            val k = FilterDuplcatesCompilerErrors.newCheckErrsMatching(
//                msg1,
//                msg2
//            )
//            println("k = $k")
//            if (k > 0.49) {
//                fileToErrorMsg.remove(asList[i].first)
//                break
//            }
//            val kStacktraces = FilterDuplcatesCompilerErrors.newCheckErrsMatching(
//                FilterDuplcatesCompilerErrors.getStacktrace2(msg1),
//                FilterDuplcatesCompilerErrors.getStacktrace2(msg2)
//            )
//            println("kStacks = $kStacktraces")
//            if (kStacktraces == 0.5) {
//                fileToErrorMsg.remove(asList[i].first)
//                break
//            }
//        }
//    }
//    println()
//
//    for (f in File("/home/zver/IdeaProjects/kotlinBugs/JVM/").listFiles()){
//        if (f.name !in fileToErrorMsg.keys) {
//            if (File("${f.nameWithoutExtension}_ORIGINAL.kt").exists()) {
//                f.delete()
//            }
//            f.delete()
//        }
//    }
//
//
//    exitProcess(0)
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
//        val res = tracer.checkBehavior(project, false)
//        //if (res) f.delete()
//    }
//    exitProcess(0)

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