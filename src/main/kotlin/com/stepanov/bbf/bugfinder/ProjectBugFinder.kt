package com.stepanov.bbf.bugfinder

import com.stepanov.bbf.bugfinder.executor.*
import com.stepanov.bbf.bugfinder.executor.compilers.JVMCompiler
import com.stepanov.bbf.bugfinder.executor.compilers.KJCompiler
import com.stepanov.bbf.bugfinder.executor.compilers.MutationChecker
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.projectfuzzer.ClassSplitter
import com.stepanov.bbf.bugfinder.util.*
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.passes.ImportsGetter
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.psi.*
import java.io.File
import kotlin.random.Random

class ProjectBugFinder(dir: String) : BugFinder(dir) {

    fun findBugsInKJProjects() {
        compilers.replaceAll { KJCompiler((it as JVMCompiler).arguments) }
        val file = File(dir).listFiles().random()
        val files =
            file.readText()
                .split(Regex("(//File)|(// FILE)"))
                .filter { it.contains(".java") || it.contains(".kt") }
                .map { "// FILE$it" }
                .map {
                    if (it.getFileLanguageIfExist() == LANGUAGE.KOTLIN) PSICreator("").getPSIForText(it)
                    else PSICreator("").getPsiForJava(it, Factory.file.project)
                }
        val checker = CompilationChecker(compilers)
        val res = checker.isCompilationSuccessful(Project(files.map { it.text }, null, LANGUAGE.KJAVA))
        log.debug("Try to compile $res")
        if (!res) return
        //Execute mutations?
        val mutants = files.map { it.text }.toMutableList()
        log.debug("Project:\n$mutants")
        for ((i, file) in files.withIndex()) {
            //if (file.text.getFileLanguageIfExist() == LANGUAGE.JAVA) continue
            log.debug("File $i from ${files.size - 1} mutations began")
            val creator = PSICreator("")
            val psi =
                if (file.text.getFileLanguageIfExist() == LANGUAGE.JAVA) creator.getPsiForJava(
                    file.text,
                    file.project
                )
                else creator.getPSIForText(file.text)
            val m = makeMutant(
                psi,
                creator.ctx,
                Project(mutants.getAllWithout(i), null, LANGUAGE.KJAVA),
                listOf(::noBoxFunModifying)
            )
            mutants[i] = m.text
        }
        val proj = Project(mutants, null, LANGUAGE.KJAVA)
        log.debug("Res after mutation = ${proj.texts}")
        TracesChecker(compilers).compareTraces(proj)
    }

    fun findBugsInProjects() {
        val dir = File(dir).listFiles()!!
        val numOfFiles = 2/*Random.nextInt(2, 4)*/
        val files = (1..numOfFiles)
            .map { dir[Random.nextInt(0, dir.size)] }
            .map { PSICreator("").getPSIForText(it.readText()) }
        if (files.any { !it.text.contains("fun box") }) return
        val factory = KtPsiFactory(files.first().project)
        Factory.file = files.first()
        val checker = CompilationChecker(compilers)
        //Rename of box() fun
        files.forEachIndexed { index, file ->
            file.getAllPSIChildrenOfType<KtNamedFunction>()
                .find { it.name == "box" }
                ?.setName("box$index")
        }
        //You didn't see it...
        var counters = mutableListOf(0, 0, 0, 0, 0)
        files.forEach { Anonymizer.anonymizeAnonimized(it, counters, true) }
        counters = mutableListOf(0, 0, 0, 0, 0)
        files.forEach { Anonymizer.anonymizeAnonimized(it, counters, false) }
        if (Random.nextBoolean()) {
            val newPackageDirectives = createRandomPackageDirectives(files.size, factory)
            files.forEachIndexed { index, file ->
                file.packageDirective?.replaceThis(newPackageDirectives[index])
                file.packageDirective?.add(factory.createWhiteSpace("\n\n\n"))
            }
        }
        val c = checker.isCompilationSuccessful(Project(null, files))
        if (!c) {
            log.debug("Cant compile project ${files.map { it.text }}")
            return
        }
        val imports =
            files.map { ImportsGetter().getAllImportsFromFile(it).filter { !it.text.contains(".box") } }

        for (i in 1 until files.size) {
            files[i].importList?.add(factory.createWhiteSpace("\n"))
            imports[i - 1].forEach { files[i].addImport(it) }
        }
        files.first().importList?.add(factory.createWhiteSpace("\n"))
        imports.last().forEach { files.first().addImport(it) }
        //Shuffle box funcs
        files.boxShift(factory)
        val project = Project(null, files)
        val res = checker.isCompilationSuccessful(project)
        if (!res) {
            log.debug("Cant compile after import transferring")
            return
        }
        val splitFiles = ClassSplitter(files, checker).split()
        if (!checker.isCompilationSuccessful(Project(null, splitFiles))) {
            log.debug("Cant compile after splitting")
            return
        }
        //Execute mutations?
        val mutants = splitFiles.map { it.text }.toMutableList()
        log.debug("Project:\n$mutants")
        for ((i, file) in splitFiles.withIndex()) {
            log.debug("File $i from ${splitFiles.size - 1} mutations began")
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
            file.getAllPSIChildrenOfType<KtNamedFunction>().find { it.name?.contains("box") ?: false } ?: return
        }
        val copyOfBox = boxFuncs.map { it.copy() as KtNamedFunction }.toMutableList()
        val lastBox = copyOfBox.last().copy() as KtNamedFunction
        copyOfBox.add(0, lastBox)
        copyOfBox.removeAt(copyOfBox.size - 1)
        boxFuncs.forEachIndexed { index, f -> f.replaceThis(copyOfBox[index]) }
    }

    private fun createRandomPackageDirectives(
        num: Int,
        factory: KtPsiFactory
    ): List<KtPackageDirective> {
        val result = mutableListOf<KtPackageDirective>()
        result.add(factory.createPackageDirective(FqName("a")))
        for (i in 1 until num) {
            if (Random.nextBoolean()) {
                //Not nested
                result.add(factory.createPackageDirective(FqName("${'a' + i}")))
            } else {
                //Nested
                val randomDirective = result.random()
                result.add(factory.createPackageDirective(FqName("${randomDirective.fqName}.${'a' + i}")))
            }
        }
        return result
    }
}