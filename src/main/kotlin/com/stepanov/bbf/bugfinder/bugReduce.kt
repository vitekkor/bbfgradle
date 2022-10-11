package com.stepanov.bbf.bugfinder

import com.stepanov.bbf.bugfinder.executor.compilers.JVMCompiler
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.util.Stream
import java.io.File
import kotlin.system.exitProcess

val bwr = File("results.txt").bufferedWriter()
const val oneFile = true
const val fir = true

fun main(args: Array<String>) {
    val files = if (oneFile)
        arrayOf(File("tmp/results/test/MyTest.kt"))
    else
        File("/home/vitekkor/IdeaProjects/bbfgradle/tmp/results/1.7.10/newResults/diffBehavior").listFiles()
    for (f in files) {
        if (f.name.contains("ORIGINAL")) {
            continue
        }
        val code = f.readText()

        println(code)
        val p = Project.createFromCode(code)
        val c = JVMCompiler(if (fir) "-Xuse-fir" else "")
        val cRes = c.compile(p)
        writeToFile(f.name)
        writeToFile(cRes.toString())
        println(f.name)
        println(cRes)
        if (cRes.status == 0) {
            val execRes = c.exec(cRes.pathToCompiled, Stream.BOTH)
            writeToFile("EXEC RES = $execRes")
            writeToFile("--------------------------")
            println("EXEC RES = $execRes")
            println("--------------------------")
        } else {
            writeToFile("EXEC RES = NOT COMPILED")
            writeToFile(c.getErrorMessage(p))
            writeToFile("--------------------------")
            println("EXEC RES = NOT COMPILED")
            println(c.getErrorMessage(p))
            println("--------------------------")
        }
    }
    bwr.close()
    exitProcess(0)
}

fun copyRequiredFiles() {
    var fileFound = false

    File("lol (2).txt").readLines().forEach {
        if (!fileFound && it.matches(""".*\.kt""".toRegex())) {
            fileFound = true
            File("/home/vitekkor/IdeaProjects/bbfgradle/tmp/results/1.7.10/diffBehavior/$it")
                .copyTo(File("/home/vitekkor/IdeaProjects/bbfgradle/tmp/results/1.7.10/diffBehavior/needToSee/$it"))
        }
        if (it == "--------------------------") {
            fileFound = false
        }

    }
}

val ignoredCode = listOf<String>()

fun String.ignore(): Boolean {
    return ignoredCode.any { contains(it) }
}

fun writeToFile(code: String) {
    if (oneFile) return
    bwr.write(code)
    bwr.newLine()
}
