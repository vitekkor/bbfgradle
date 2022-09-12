package com.stepanov.bbf.bugfinder

import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.executor.compilers.JVMCompiler
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.util.Stream
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
    val oneFile = true

    val fir = true

    val files = if (oneFile)
        arrayOf(File("tmp/results/test/MyTest.kt"))
    else
        File("/home/vitekkor/IdeaProjects/bbfgradle/tmp/results/1.7.10/diffBehavior").listFiles()
    for (f in files) {
        println(f.name)
        val code = f.readText()
        println(code)
        val p = Project.createFromCode(code)
        val c = JVMCompiler(if (fir) "-Xuse-fir" else "")
        val cRes = c.compile(p)
        println(cRes)
        if (cRes.status == 0) {
            val execRes = c.exec(cRes.pathToCompiled, Stream.BOTH)
            println("EXEC RES = $execRes")
            println("--------------------------")
        } else {
            println("EXEC RES = NOT COMPILED")
            println(c.getErrorMessage(p))
            println("--------------------------")
        }
    }
    exitProcess(0)
}
