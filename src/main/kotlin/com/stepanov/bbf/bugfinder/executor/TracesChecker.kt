package com.stepanov.bbf.bugfinder.executor

import com.intellij.psi.PsiErrorElement
import com.stepanov.bbf.bugfinder.util.*
import com.stepanov.bbf.reduktor.util.getAllChildrenNodes
import org.apache.log4j.Logger
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtImportDirective
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.KtPsiFactory
import org.jetbrains.kotlin.resolve.ImportPath
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.lang.StringBuilder

// Transformation is here only for PSIFactory
class TracesChecker(private val compilers: List<CommonCompiler>) : CompilationChecker(compilers) {

    private companion object FalsePositivesTemplates {
        //Regex and replacing
        val exclErrorMessages = listOf(
            "IndexOutOfBoundsException"
        )
    }

    fun checkTest(text: String): List<CommonCompiler>? {
        var resText = text
        if (!resText.contains("fun main(")) {
            resText += "fun main(args: Array<String>) {\n" +
                    "    println(box())\n" +
                    "}"
        }
        val writer = BufferedWriter(FileWriter(CompilerArgs.pathToTmpFile))
        writer.write(resText)
        writer.close()
        val res = checkTest(resText, CompilerArgs.pathToTmpFile)
        File(CompilerArgs.pathToTmpFile).delete()
        return res
    }

    fun addMainForProject(project: Project): Project {
        if (project.texts.size == 1) {
            val newText = project.texts.first() +
                    "\nfun main(args: Array<String>) {\n" +
                    "    println(box())\n" +
                    "}"
            return Project(listOf(newText))
        } else {
            val files = project.texts.map { psiFactory.createFile(it) }
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
                val newImport = psiFactory.createImportDirective(ImportPath(FqName("${'a' + i + 1}.box$i"), false))
                firstFile.addImport(newImport)
            }
            firstFile.addMain(files)
            return Project(null, files)
        }
    }

    private fun KtFile.addMain(files: List<KtFile>) {
        val m = StringBuilder()
        m.append("fun main(args: Array<String>) {\n")
        for (i in files.indices) m.append("println(box$i())\n")
        m.append("}")
        val mainFun = KtPsiFactory(this.project).createFunction(m.toString())
        this.add(KtPsiFactory(this.project).createWhiteSpace("\n\n"))
        this.add(mainFun)
    }

    private fun KtFile.addImport(import: KtImportDirective) {
        this.importList?.add(KtPsiFactory(this.project).createWhiteSpace("\n"))
        this.importList?.add(import)
        this.importList?.add(KtPsiFactory(this.project).createWhiteSpace("\n"))
    }


    fun compareTraces(project: Project): List<CommonCompiler>? {
        val path = project.generateCommonName()
        //Check if already checked
        val text = project.getCommonText(path)
        val hash = text.hashCode()
        if (alreadyChecked.containsKey(hash)) {
            log.debug("ALREADY CHECKED!!!")
            return alreadyChecked[hash]!!
        }

        //Add main
        val projectWithMain = addMainForProject(project)
        if (!isCompilationSuccessful(projectWithMain)) return null
        projectWithMain.saveOrRemoveToTmp(true)
        val results = mutableListOf<Pair<CommonCompiler, String>>()
        for (comp in compilers) {
            val status = comp.compile(path)
            if (status.status == -1)
                return null
            val res = comp.exec(status.pathToCompiled)
            val errors = comp.exec(status.pathToCompiled, Stream.ERROR)
            log.debug("Result of ${comp.compilerInfo}: $res\n")
            log.debug("Errors: $errors")
            if (exclErrorMessages.any { errors.contains(it) })
                return null
            results.add(comp to res.trim())
        }
        val groupedRes = results.groupBy({ it.second }, valueTransform = { it.first })
        return if (groupedRes.size == 1) {
            null
        } else {
            val res = groupedRes.map { it.value.first() }
            alreadyChecked[hash] = res
            res
        }
    }

    fun checkTestForProject(commonPath: String): List<CommonCompiler>? {
        val results = mutableListOf<Pair<CommonCompiler, String>>()
        for (comp in compilers) {
            val status = comp.compile(commonPath)
            if (status.status == -1)
                return null
            val res = comp.exec(status.pathToCompiled)
            val errors = comp.exec(status.pathToCompiled, Stream.ERROR)
            log.debug("Result of ${comp.compilerInfo}: $res\n")
            log.debug("Errors: $errors")
            results.add(comp to res.trim())
        }
        val groupedRes = results.groupBy({ it.second }, valueTransform = { it.first })
        return if (groupedRes.size == 1) {
            null
        } else {
            groupedRes.map { it.value.first() }
        }
    }

    fun checkTest(text: String, pathToFile: String): List<CommonCompiler>? {
        val hash = text.hashCode()
        if (alreadyChecked.containsKey(hash)) {
            log.debug("ALREADY CHECKED!!!")
            return alreadyChecked[hash]!!
        }

        val psiFile = psiFactory.createFile(text)
        //Check for syntax correctness
        if (psiFile.node.getAllChildrenNodes().any { it.psi is PsiErrorElement }) {
            log.debug("Not correct syntax")
            alreadyChecked[hash] = null
            return null
        }

        log.debug("Trying to compile with main function:")
        if (!compilers.checkCompilingForAllBackends(psiFile)) {
            log.debug("Cannot compile with main")
            return null
        }

        log.debug("Executing traced code:\n$text")
        val results = mutableListOf<Pair<CommonCompiler, String>>()
        for (comp in compilers) {
            val status = comp.compile(pathToFile)
            if (status.status == -1)
                return null
            val res = comp.exec(status.pathToCompiled)
            val errors = comp.exec(status.pathToCompiled, Stream.ERROR)
            log.debug("Result of ${comp.compilerInfo}: $res\n")
            log.debug("Errors: $errors")
            if (FalsePositivesTemplates.exclErrorMessages.any { errors.contains(it) })
                return null
            results.add(comp to res.trim())
        }
        val groupedRes = results.groupBy({ it.second }, valueTransform = { it.first })
        return if (groupedRes.size == 1) {
            null
        } else {
            val res = groupedRes.map { it.value.first() }
            alreadyChecked[hash] = res
            res
        }
    }

    var alreadyChecked: HashMap<Int, List<CommonCompiler>?> = HashMap()
    private val log = Logger.getLogger("bugFinderLogger")
}
