package com.stepanov.bbf.bugfinder.decompiler

import com.intellij.psi.JavaTokenType
import com.intellij.psi.PsiAnnotation
import com.intellij.psi.PsiExpressionStatement
import com.intellij.psi.PsiModifierList
import com.stepanov.bbf.bugfinder.executor.addMain
import com.stepanov.bbf.bugfinder.executor.compilers.JVMCompiler
import com.stepanov.bbf.bugfinder.util.debugPrint
import com.stepanov.bbf.bugfinder.util.getAllChildrenNodes
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.parser.PSICreator
import org.jetbrains.java.decompiler.main.decompiler.ConsoleDecompiler
import org.jetbrains.kotlin.KtNodeTypes
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtNamedFunction
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.util.zip.ZipFile

class K2JConverter {

    fun convert(pathToFile: String): String {
        val pathToDecompiled = "tmp/decompiled"
        File(pathToDecompiled).deleteRecursively()
        File(pathToDecompiled).mkdirs()
        val file = File(pathToFile)
        val compiled = JVMCompiler().compile(file.absolutePath, false)
        if (compiled.status == -1) return ""
        val pathToJar = compiled.pathToCompiled
        ConsoleDecompiler.main(arrayOf(file.absolutePath, pathToJar, pathToDecompiled))
        val zipFile = ZipFile("${pathToDecompiled}/tmp.jar")
        zipFile.copyContentTo(pathToDecompiled) { it.name.endsWith(".java") }
        try {
            val resPath = handleDecompiledFiles(pathToDecompiled)
            return resPath
        } catch (e: Exception) {
            System.exit(0)
        }
        return ""
    }

    private fun handleDecompiledFiles(path: String): String {
        val files = File(path).listFiles()?.toList() ?: return ""
        val mainFile = files.find { it.absolutePath.endsWith("Main.kt") } ?: return ""
        val javaFiles = files.filter { it.absolutePath.endsWith(".java") && !it.absolutePath.contains("Main") }
        val mainPsi = PSICreator("").getPSIForText(mainFile.readText())
        val javaPsi = javaFiles.map { PSICreator("").getPsiForJava(it.readText(), mainPsi.project) }
        //Remove metadata and intrinsics
        for (psiFile in javaPsi) {
            psiFile.node.getAllChildrenNodes()
                .filter { it.psi is PsiAnnotation && it.text.contains("@Metadata") }
                .forEach { it.psi.delete() }
            psiFile.node.getAllChildrenNodes()
                .filter { it.psi is PsiExpressionStatement && it.text.contains("Intrinsics.") }
                .forEach { it.psi.delete() }
        }
        //Remove classes from main
        mainPsi.getAllPSIChildrenOfType<KtClassOrObject>().filter { it.isTopLevel() }.forEach { it.delete() }
        //Add main
        mainPsi.addMain(mainPsi.getAllPSIChildrenOfType<KtNamedFunction>().filter { it.name?.contains("box") ?: false })
        //Save new files
        mainFile.writeText(mainPsi.text)
        javaFiles.forEachIndexed { index, file -> file.writeText(javaPsi[index].text) }
        return (listOf(mainFile.absolutePath) + javaFiles.map { it.absolutePath }).joinToString(" ")
    }
}