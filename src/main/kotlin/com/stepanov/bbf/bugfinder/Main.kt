package com.stepanov.bbf.bugfinder

import com.stepanov.bbf.bugfinder.executor.Checker
import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.executor.compilers.JVMCompiler
import com.stepanov.bbf.bugfinder.executor.project.LANGUAGE
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.generator.constructor.ProgramConstructor
import com.stepanov.bbf.bugfinder.mutator.transformations.constructor.UsagesSamplesGenerator
import com.stepanov.bbf.bugfinder.util.*
import com.stepanov.bbf.reduktor.parser.PSICreator
import net.sourceforge.argparse4j.ArgumentParsers
import net.sourceforge.argparse4j.impl.Arguments
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.log4j.PropertyConfigurator
import org.jetbrains.kotlin.cfg.pseudocode.getSubtypesPredicate
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.types.getSubtypeRepresentative
import org.jetbrains.kotlin.types.typeUtil.supertypes
import java.io.File
import kotlin.reflect.jvm.internal.impl.types.TypeCapabilitiesKt
import kotlin.system.exitProcess


fun main(args: Array<String>) {
    //Init log4j
    PropertyConfigurator.configure("src/main/resources/bbfLog4j.properties")
    val f1 = File(CompilerArgs.baseDir).listFiles()?.random() ?: exitProcess(0)
    SingleFileBugFinder(f1.absolutePath).findBugsInFile()
    System.exit(0)

//    for (f in File("/home/stepanov/Kotlin/bbfgradle/tmp/arrays/").listFiles().filter { !it.isDirectory && it.name.endsWith(".kt") }) {
//    //val f = File("tmp/test.kt")
//        println("Name = ${f.name}")
//        //if (!f.absolutePath.contains("kt26103_original.kt")) continue
//        val fi = creator.getPSIForFile(f.absolutePath)
//        val r = RandomInstancesGenerator(fi)
//        fi.getAllPSIChildrenOfType<KtClassOrObject>().forEach {
//            println("INSTANCE OF ${it.text}")
//            val res = r.generateRandomInstanceOfClass(it); println("${res?.text}\n\n")
//        }
//        //System.exit(0)
//    }
    System.exit(0)
    while (true) ProgramConstructor(Checker(JVMCompiler())).construct()
    System.exit(0)

//    val creator = PSICreator("")
//    val f = creator.getPSIForFile("tmp/test.kt")
//    val r = RandomInstancesGenerator(f)
//    f.getAllPSIChildrenOfType<KtClassOrObject>()[6].let {
//        val r = r.generateRandomInstanceOfClass(it); println("FINRES = ${r?.text}")
//    }
    //while (true) ProgramConstructor(Checker(JVMCompiler())).construct()
    System.exit(0)
//    val code = File("/home/stepanov/Kotlin/kotlin/compiler/testData/codegen/box/callableReference/adaptedReferences/localFunctionWithDefault.kt").readText()
//    val project = Project.createFromCode(code)
//    println(project.files.map { it.name })
//    val checker = MutationChecker(JVMCompiler(), project, project.files.first())
//    val psi = PSICreator("").getPSIForText(File("tmp/test.kt").readText())
//    checker.checkCompilingWithBugSaving(psi)
//    System.exit(0)
//    for (f in File("/home/stepanov/Kotlin/bbfgradle/tmp/testDir/").listFiles()) {
//        if (!f.name.contains("nezivic_FILE")) continue
//        println(f.name)
//        val c1 = JVMCompiler()
//        val c2 = JSCompiler()
//        val proj = Project.createFromCode(f.readText())
//        val c1R = c1.compile(proj)
//        println(c1R)
//        val res1 = c1.exec(c1R.pathToCompiled, Stream.ERROR)
//        println(res1)
//        exitProcess(0)
//        if (res1.trim().isEmpty()) {
//            val res11 = c1.exec(c1R.pathToCompiled, Stream.ERROR)
//            val fl = res11.contains("Java.lang.object", true)
//            println("BE OF OBJ = ${fl}")
//            if (!fl) println(res11)
//        }
//        println("r1 = $res1")
////        exitProcess(0)
//        val c2R = c2.compile(proj)
//        println("c2r = $c2R")
//        val res2 = c2.exec(c2R.pathToCompiled)
//        println("r2 = $res2")
////        exitProcess(0)
//    }
//    exitProcess(0)
//    BugManager.saveBug(Bug(
//        listOf(JVMCompiler(), JVMCompiler("-Xuse-ir")),
//        "",
//        Project.createFromCode(File("tmp/test.kt").readText()),
//        BugType.FRONTEND
//    ))
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
//    if (Random.nextBoolean()) {
//        ProjectBugFinder("tmp/arrays/kotlinAndJava").findBugsInKJProjects()
//    } else {
    //ProjectBugFinder("tmp/arrays/classTests").findBugsInProjects()
//    }
    val file = File(CompilerArgs.baseDir).listFiles()?.random() ?: exitProcess(0)
    //val file = File("tmp/test.kt")
//    val regex = Regex("""import kotlin.reflect.typeOf""")
//    val files =
//        Files.walk(Paths.get("/home/stepanov/Kotlin/kotlin/compiler/testData/codegen/box/callableReference/adaptedReferences/"))
//            .map { it.toFile() }.filter { it.isFile }.toList()
//    //val condition = {f: File -> okFiles.any { f.name.contains(it.name) }}
//    val condition2 = { f: File -> f.readText().contains(regex) }
//    val condition3 = { f: File -> f.readText().contains(Regex("""FunctionalInterfaceConversion""")) }
//    val condition4 = { f: File -> f.readText().contains(Regex("""inline class""")) }
//    val cond = listOf(condition2, condition3, condition4)
//    val files2 = File(CompilerArgs.baseDir).listFiles().filter { f -> f.isFile && (cond.any { it.invoke(f) }) }
////    System.exit(0)
//    val file = (files + files2).random()
    SingleFileBugFinder(file.absolutePath).findBugsInFile()
    exitProcess(0)
}