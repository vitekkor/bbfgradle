package com.stepanov.bbf.bugfinder

import com.intellij.psi.PsiMethod
import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.executor.compilers.JVMCompiler
import com.stepanov.bbf.bugfinder.executor.project.LANGUAGE
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.gitinfocollector.FilePatchHandler
import com.stepanov.bbf.bugfinder.gitinfocollector.GitRepo
import com.stepanov.bbf.bugfinder.gitinfocollector.SignatureCollector
import com.stepanov.bbf.bugfinder.util.FalsePositivesDeleter
import com.stepanov.bbf.bugfinder.util.NodeCollector
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.coverage.CoverageEntry
import com.stepanov.bbf.coverage.ProgramCoverage
import com.stepanov.bbf.reduktor.parser.PSICreator
import net.sourceforge.argparse4j.ArgumentParsers
import net.sourceforge.argparse4j.impl.Arguments
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.log4j.PropertyConfigurator
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.KtTryExpression
import java.io.File
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    //Init log4j
    PropertyConfigurator.configure("src/main/resources/bbfLog4j.properties")

    val repo = GitRepo("JetBrains", "kotlin")
    val patches = repo.getPatches("c335015c05617e2c332e0cc967c8c1594838640e")
    val modifiedFunctions = FilePatchHandler(patches).getListOfAffectedFunctions(true)
    val signatures = SignatureCollector.collectSignatures(modifiedFunctions)
//    val psi = PSICreator("").getPsiForJava(File("tmp/myTest1.java").readText())
//    println(javaPsi.getAllPSIDFSChildrenOfType<PsiMethod>())
//    val psi = PSICreator("").getPSIForFile("tmp/JvmStringConcatenationLowering.kt", false)
//    val signatures = SignatureCollector.collectSignatures(psi.getAllPSIChildrenOfType<KtNamedFunction>())
//    val helloWorldProject = Project.createFromCode(File("tmp/arrays/helloWorld.kt").readText())
//    JVMCompiler("-Xuse-ir").checkCompiling(helloWorldProject)
//    val helloWorldCoverage = ProgramCoverage.createFromMethodProbes()
//    println(helloWorldCoverage.size)

//    for (fi in File("tmp").listFiles().filter { it.absolutePath.endsWith(".kt")}) {
//        if (fi.name != "myTest2.kt") continue
//        println(fi.name)
//        val p = Project.createFromCode(fi.readText())
//        if (p.language != LANGUAGE.KOTLIN) {
//            println("K = UNKNOWN\n")
//            continue
//        }
//        JVMCompiler("-Xuse-ir").checkCompiling(p)
//        val coverage = ProgramCoverage.createFromMethodProbes()
//        val f = coverage.getMethodProbes().entries.map { CoverageEntry.parseFromKtCoverage(it.key) to it.value }.toMap()
//        var koef = 0
//        for (sig in signatures) {
//            koef += f[sig] ?: 0
//        }
//        println("k = $koef\n")
//    }
//    exitProcess(0)
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
        //TODO
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
    val file = File(CompilerArgs.baseDir).listFiles()?.filter { it.path.endsWith(".kt") }?.random() ?: exitProcess(0)
    SingleFileBugFinder("tmp/myTest2.kt").findBugsInFile()
    exitProcess(0)
}