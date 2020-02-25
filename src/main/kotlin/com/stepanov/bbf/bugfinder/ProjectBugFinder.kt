package com.stepanov.bbf.bugfinder

import com.stepanov.bbf.bugfinder.executor.*
import com.stepanov.bbf.bugfinder.executor.compilers.JVMCompiler
import com.stepanov.bbf.bugfinder.executor.compilers.MutationChecker
import com.stepanov.bbf.bugfinder.manager.BugManager
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.util.*
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.passes.ImportsGetter
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.psi.*
import java.io.File
import java.lang.StringBuilder
import kotlin.random.Random

class ProjectBugFinder(dir: String) : BugFinder(dir) {

    fun findBugsInProjects() {
        val dir = File(dir).listFiles()!!
        val numOfFiles = Random.nextInt(2, 4)
        val files = (1..numOfFiles)
            .map { dir[Random.nextInt(0, dir.size)] }
            .map { PSICreator("").getPSIForText(it.readText()) }
        val factory = KtPsiFactory(files.first().project)
        Factory.file = files.first()
        val checker = CompilationChecker(compilers)
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
        val c = checker.checkCompiling(Project(null, files))
        if (!c) return
        val imports = files.map { ImportsGetter().getAllImportsFromFile(it).filter { !it.text.contains(".box") } }

        for (i in 1 until files.size) {
            files[i].importList?.add(factory.createWhiteSpace("\n"))
            imports[i - 1].forEach { files[i].addImport(it) }
        }
        imports.last().forEach { files.first().addImport(it) }
        //Shuffle box funcs
        files.boxShift(factory)
        val project = Project(null, files)
        val res = checker.checkCompiling(project)
        println("result = $res\n")
        if (!res) return
        //Execute mutations?
        val mutants = files.map { it.text }.toMutableList()
        for ((i, file) in files.withIndex()) {
            log.debug("File $i from ${files.size - 1} mutations began")
            val creator = PSICreator("")
            val m = makeMutant(
                creator.getPSIForText(file.text),
                creator.ctx!!,
                Project(mutants.getAllWithout(i)),
                listOf(::noBoxFunModifying)
            )
            mutants[i] = m.text
        }
        val proj = Project(mutants)
        log.debug("Res after mutation = ${proj.getCommonTextWithDefaultPath()}")
        TracesChecker(compilers).compareTraces(proj)
        return
    }

    private fun KtFile.addImport(import: KtImportDirective) {
        this.importList?.add(import)
        this.importList?.add(KtPsiFactory(this.project).createWhiteSpace("\n"))
    }

    private fun List<KtFile>.boxShift(psiFactory: KtPsiFactory) {
        val boxFuncs = this.map { file ->
            file.getAllPSIChildrenOfType<KtNamedFunction>().find { it.name?.contains("box") ?: false }!!
        }
        val copyOfBox = boxFuncs.map { it.copy() as KtNamedFunction }.toMutableList()
        val lastBox = copyOfBox.last().copy() as KtNamedFunction
        copyOfBox.add(0, lastBox)
        copyOfBox.removeAt(copyOfBox.size - 1)
        boxFuncs.forEachIndexed { index, f -> f.replaceThis(copyOfBox[index]) }
    }
}