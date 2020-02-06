package com.stepanov.bbf.bugfinder

import com.stepanov.bbf.bugfinder.executor.ProjectCompilingChecker
import com.stepanov.bbf.bugfinder.executor.compilers.JVMCompiler
import com.stepanov.bbf.bugfinder.util.*
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.passes.ImportsGetter
import com.stepanov.bbf.reduktor.passes.PreliminarySimplification
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.resolve.ImportPath
import java.io.File
import java.lang.StringBuilder
import kotlin.random.Random

class ProjectBugFinder(private val pathToDir: String) {

    fun findBugsInProject() {
        val dir = File(pathToDir).listFiles()!!
        val numOfFiles = Random.nextInt(2, 4)
        val files = (1..numOfFiles)
            .map { dir[Random.nextInt(0, dir.size)] }
            .map { PSICreator("").getPSIForText(it.readText()) }
        val factory = KtPsiFactory(files.first().project)
        //Rename of box() fun
        files.forEachIndexed { index, file ->
            file.getAllPSIChildrenOfType<KtNamedFunction>()
                .find { it.name == "box" }
                ?.setName("box$index")
        }
        files.forEachIndexed { index, file ->
            val newPackageDirecrive = factory.createPackageDirective(FqName("${'a' + index}"))
            file.packageDirective?.replaceThis(newPackageDirecrive)
            file.packageDirective?.add(factory.createWhiteSpace("\n\n\n"))
        }
        val c = ProjectCompilingChecker.checkTextCompiling(files.map { it.text })
        if (!c) return
        val imports = files.map { ImportsGetter().getAllImportsFromFile(it).filter { !it.text.contains(".box") } }

        for (i in 1 until files.size) {
            files[i].importList?.add(factory.createWhiteSpace("\n"))
            imports[i - 1].forEach { files[i].addImport(it) }
        }
        imports.last().forEach { files.first().addImport(it) }
        val boxFuncs = files.map { file ->
            file.getAllPSIChildrenOfType<KtNamedFunction>().find { it.name?.contains("box") ?: false }!!
        }
        val copyOfBox = boxFuncs.map { it.copy() as KtNamedFunction }.toMutableList()
        val lastBox = copyOfBox.last().copy() as KtNamedFunction
        copyOfBox.add(0, lastBox)
        copyOfBox.removeAt(copyOfBox.size - 1)
        boxFuncs.forEachIndexed { index, f -> f.replaceThis(copyOfBox[index]) }
        //Add import of box_I functions
        val firstFile = files.first()
        for (i in 0 until files.size - 1) {
            val newImport = factory.createImportDirective(ImportPath(FqName("${'a' + i + 1}.box$i"), false))
            firstFile.addImport(newImport)
        }
        firstFile.addMain(files)
        val res = ProjectCompilingChecker.checkTextCompiling(files.map { it.text })
        println("result = $res\n")
        return
    }

    private fun KtFile.addMain(files: List<KtFile>) {
        val m = StringBuilder()
        m.append("fun main(args: Array<String>) {\n")
        for (i in files.indices) m.append("println(box$i())\n")
        m.append("}")
        val mainFun = KtPsiFactory(this.project).createFunction(m.toString())
        this.add(mainFun)
    }

    private fun KtFile.addImport(import: KtImportDirective) {
        this.importList?.add(import)
        this.importList?.add(KtPsiFactory(this.project).createWhiteSpace("\n"))
    }
}