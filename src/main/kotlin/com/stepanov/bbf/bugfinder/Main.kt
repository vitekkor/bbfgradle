package com.stepanov.bbf.bugfinder

import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.executor.checkers.LineCoverageGuider
import com.stepanov.bbf.bugfinder.gitinfocollector.GitCommitsParser
import com.stepanov.bbf.bugfinder.util.instrumentation.Instrumenter
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import java.io.File
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (!CompilerArgs.getPropAsBoolean("LOG")) {
        LogManager.shutdown()
    }
//    println("HELLO")
//    exitProcess(0)
//    println(LineCoverageStatisticsCollectorForEachSeed("lol.kt").coveredMethods)
//    exitProcess(0)
//    Instrumenter.instrumentJars()
//    exitProcess(0)
//    val p = Project.createFromCode(File("tmp/myTest.kt").readText())
//    println(CompilerArgs.getCompilersList().first().getErrorMessage(p))
//    exitProcess(0)
//    val cov = LineCoverageGuider.getLineCoverage(p)
//    println(cov.count { it == 1 })
//    exitProcess(0)
//    val instrumentedCode = Instrumenter.instrumentJars()
    //GitCommitsParser.parse("/home/zver/IdeaProjects/kotlin/lol.txt")
//    exitProcess(0)
//    val coverage = LineCoverageGuider.getLineCoverage(p)
//    println(coverage.count { it == 1 })
//    exitProcess(0)
//    println()
//    exitProcess(0)
//    val initialSeed = LineCoverageGuider.calcInitialSeed("tmp/arrays", 20)
//    initialSeed.toList().sortedByDescending { it.second.second }.forEach { println("${it.first} -> ${it.second}") }
//    exitProcess(0)
//    val savedCoverage = mutableMapOf<String, IntArray>()
//    for (f in File("tmp/testsForExperiments/7_3_inlineClasses/").listFiles()
//        .filter { it.absolutePath.endsWith(".kt") }) {
////        if (f.name != "kt32793.kt") continue
//        //println("f = ${f.name}")
//        val p = Project.createFromCode(f.readText())
//        println("CAN COMPILE? = ${CompilerArgs.getCompilersList().all { it.checkCompiling(p) }}")
//        val cov1 = LineCoverageGuider.getLineCoverage(p, false)
//        savedCoverage[f.name] = cov1
//        println("Coverage of ${f.name} = ${cov1.count { it == 1 }}")
//        CoverageStatisticWriter.fileName = f.absolutePath
//        CoverageStatisticWriter.instance.addCoveredMethods(cov1)
//        CoverageStatisticWriter.instance.saveCoverageStatistic()
//        continue
//        for (c in CompilerArgs.getCompilersList()) {
//            val compiled = c.compile(p)
//            println("compiled = $compiled")
//            if (compiled.status != COMPILE_STATUS.OK) {
//                println(c.getErrorMessage(p))
//                continue
//            }
//            val execRes = c.exec(compiled.pathToCompiled, Stream.BOTH)
//            println("exec res of $c is:\n$execRes")
//            println("--------")
//        }
//        println("-----------------------------------------")
//    }
//    println("LOL")
//    exitProcess(0)
//        for (f in File("/home/zver/IdeaProjects/bbfgradle/tmp/testsForExperiments/7_2_inlineClasses/").listFiles()) {
//            //if (f.name != "stepNonConst9.kt") continue
//            println("f = ${f.name}")
//            val p = Project.createFromCode(f.readText())
//            val allKtFiles = p.files.mapNotNull { it.psiFile as? KtFile }
//            val allKlasses = allKtFiles.flatMap { it.getAllPSIChildrenOfType<KtClass>() }
//            val allInlineKlasses = allKlasses.filter { it.modifierList?.hasModifier(KtTokens.INLINE_KEYWORD) ?: false }
//            println("allInline = ${allInlineKlasses.size}")
//        }
//    exitProcess(0)
//    for (f in File("tmp/arrays").listFiles()) {
//        if (f.name != "myTest.kt") continue
//        println("f = ${f.name}")
//        val p = Project.createFromCode(f.readText())
////        if (p.files.size > 1) continue
////        val ktFile = p.files.first().psiFile as KtFile
////        val ifExtensionReceiver = ktFile.getAllPSIChildrenOfType<KtNamedFunction>().any { it.isExtensionDeclaration() }
////        if (ifExtensionReceiver) {
////            println("EXTENSION RECEIVER!!!")
////        }
////        continue
//        for (c in CompilerArgs.getCompilersList()) {
//            val newC = if (p.language == LANGUAGE.KJAVA) KJCompiler(c.arguments) else c
//            val compiled = newC.compile(p)
//            if (compiled.pathToCompiled.isEmpty()) {
//                println(c.getErrorMessage(p))
//                break
//            }
//            val execRes = newC.exec(compiled.pathToCompiled, Stream.BOTH)
//            println("exec res of $newC is:\n$execRes")
//            println("--------")
//        }
////        BugManager.saveBug(
////            Bug(
////                CompilerArgs.getCompilersList().first(),
////                "",
////                p,
////                BugType.BACKEND
////            )
////        )
//        println("-----------------------------------------")
//    }
//    exitProcess(0)
//    val f = File("/home/zver/IdeaProjects/bbfgradle/tmp/myTest.kt")
//    println("f = ${f.name}")
//    val p = Project.createFromCode(f.readText())
//    for (c in CompilerArgs.getCompilersList()) {
//        val compiled = c.compile(p)
//        val execRes = c.exec(compiled.pathToCompiled, Stream.BOTH)
//        println("exec res of $c is:\n$execRes")
//        println("--------")
//    }
//    println("-----------------------------------------")
//
//    exitProcess(0)
//    val pathToCoverage = "tmp/coverage.txt"
//    CoverageGuider.initDesireCoverage(pathToCoverage) { !"$it".contains("konan") && !"$it".contains("fir") }
//    Instrumenter.instrumentJars()
//    exitProcess(0)
//    val helloWorldProject = Project.createFromCode(
//        """
//    fun main() {
//        println("HELLO WORLD!")
//    }
//    """.trimIndent()
//    )
//    val helloWorldCoverage = CoverageGuider.getLineCoverage(helloWorldProject)
//    println("full Coverage = ${helloWorldCoverage.count { it == 1 }}")
//    for (f in File("/home/zver/IdeaProjects/bbfgradle/tmp/testsForExperiments/7_1_inlineClasses/").listFiles()) {
//        println(f.name)
//        val p1 = Project.createFromCode(f.readText())
//        val cov1 = LineCoverageGuider.getLineCoverage(p1)
//        LineCoverageStatisticsCollector.addCoveredMethods(cov1)
//        LineCoverageStatisticsCollector.saveCoverageStatistic()
//        println("Full coverage = ${cov1.count { it == 1 }}")
////        val uniqueCoverage = cov1.mapIndexed { index, i ->
////            if (helloWorldCoverage[index] == 1) 0 else i
////        }.count { it == 1 }
////        println("Unique coverage = $uniqueCoverage")
////        println()
//    }
//    exitProcess(0)
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
//    JarSourceCodeInstrumenter().instrument()
    val filePath =
        if (args.isNotEmpty()) args[0]
        else
            File("tmp/arrays/")
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