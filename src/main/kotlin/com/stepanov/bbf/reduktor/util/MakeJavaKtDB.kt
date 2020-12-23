package com.stepanov.bbf.reduktor.util

//import com.stepanov.bbf.bugfinder.executor.compilers.JVMCompiler
//import com.stepanov.bbf.bugfinder.executor.compilers.KJCompiler
//import com.stepanov.bbf.bugfinder.util.decompiler.copyContentTo
//import org.jetbrains.java.decompiler.main.decompiler.ConsoleDecompiler
//import java.io.File
//import java.nio.charset.Charset
//import java.util.zip.ZipFile
//
//fun make() {
//    var i = 0
//    for (dir in File("tmp/arrays/JavaAndKotlin").listFiles().reversed()) {
//        println("i = $i")
//        println(dir.name)
//        if (dir.listFiles().size < 2) {
//            println("REMOVING ${dir.name}")
//            dir.deleteRecursively()
//            continue
//        }
//        val anotherFile = File(dir.absolutePath).listFiles().filter { !it.name.contains("BoxFun") }.first()
//        val st = JVMCompiler().compile(dir.absolutePath, false)
//        val kotlinJar = ZipFile(st.pathToCompiled, Charset.forName("CP866"))
//        kotlinJar.copyContentTo(dir.absolutePath) { it.name == "BoxFunKt.class" }
//        File(st.pathToCompiled).delete()
//        ConsoleDecompiler.main(arrayOf("${dir.absolutePath}/BoxFunKt.class", dir.absolutePath))
//        val path = "${dir.absolutePath}/BoxFunKt.java ${anotherFile.absolutePath}"
//        val res = KJCompiler().checkCompiling(path)
//        if (res) {
//            val t1 = File("${dir.absolutePath}/BoxFunKt.java").readText()
//            val t2 = anotherFile.readText()
//            val commonText = "FILE: BoxFunKt.java\n\n$t1\n\nFILE: ${anotherFile.name}\n\n$t2"
//            File("tmp/arrays/JavaAndKotlin/javaAndKotlin$i.kt").writeText(commonText)
//        } else {
//            --i
//        }
//        println("res = ${res}")
//        ++i
//    }
//}