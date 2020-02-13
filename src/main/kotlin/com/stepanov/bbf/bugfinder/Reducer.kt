package com.stepanov.bbf.bugfinder

import com.intellij.psi.PsiFile
import com.stepanov.bbf.bugfinder.executor.*
import com.stepanov.bbf.bugfinder.manager.Bug
import com.stepanov.bbf.bugfinder.manager.BugType
import com.stepanov.bbf.bugfinder.util.saveOrRemoveToTmp
import com.stepanov.bbf.reduktor.executor.CompilerTestChecker
import com.stepanov.bbf.reduktor.manager.TransformationManager
import com.stepanov.bbf.reduktor.parser.PSICreator
import org.jetbrains.kotlin.psi.KtFile
import java.io.File

object Reducer {

    fun reduce(bug: Bug, shouldSave: Boolean = false): Project {
        //First we need to find more project bugs!!
        if (bug.crashedProject.texts.size != 1) return bug.crashedProject
        //Saving to tmp
        val path = bug.crashedProject.saveOrRemoveToTmp(true)
        val compilers = bug.compilers
        val checker = when (bug.type) {
            BugType.BACKEND, BugType.FRONTEND -> MultiCompilerCrashChecker(compilers.first())
            BugType.DIFFCOMPILE -> DiffCompileChecker(compilers)
            //BugType.DIFFBEHAVIOR -> DiffBehaviorChecker(compilers)
            else -> return bug.crashedProject
        }
        val reduced = reduceFile(path, checker)
        if (shouldSave) saveFile(reduced)
        return Project(reduced.text)
    }

//    fun reduce(path: String, compiler: CommonCompiler, shouldSave: Boolean = false): List<PsiFile> {
//        val f = File(path)
//        val files = if (f.isDirectory) f.listFiles().toList() else listOf(f)
//        val res = files.asSequence()
//            .map { PSICreator("").getPSIForFile(it.absolutePath, false) }
//            .map {
//                reduceFile(
//                    it,
//                    MultiCompilerCrashChecker(compiler)
//                )
//            }
//            .map {
//                if (shouldSave) {
//                    saveFile(it)
//                    it
//                } else it
//            }
//        return res.toList()
//    }
//
//    fun reduceDiffBehavior(pathToFile: String, compilers: List<CommonCompiler>, shouldSave: Boolean = false): String {
//        val ktFile = PSICreator("").getPSIForFile(pathToFile, false)
//        val res = reduceFile(
//            ktFile,
//            DiffBehaviorChecker(compilers)
//        )
//        if (shouldSave) saveFile(res)
//        return res.text
//    }
//
//    fun reduceDiffCompile(pathToFile: String, compilers: List<CommonCompiler>, shouldSave: Boolean = false): String {
//        val ktFile = PSICreator("").getPSIForFile(pathToFile, false)
//        val res = reduceFile(
//            ktFile,
//            DiffCompileChecker(compilers)
//        )
//        if (shouldSave) saveFile(res)
//        return res.text
//    }

    private fun saveFile(f: PsiFile) {
        val pathToSave = StringBuilder(f.name)
        pathToSave.insert(pathToSave.indexOfLast { it == '/' }, "/minimized")
        File(pathToSave.toString().substringBeforeLast('/')).mkdirs()
        File("$pathToSave").run {
            writeText(f.text)
        }
    }

    private fun reduceFile(path: String, checker: CompilerTestChecker): KtFile {
        val psiFile = PSICreator("").getPSIForFile(path)
        return TransformationManager(listOf(psiFile))
            .doTransformationsForFile(psiFile, checker)
    }

    private fun reduceProject(path: String, checker: CompilerTestChecker): List<KtFile> {
        val files = path.split(" ").map { PSICreator("").getPSIForFile(it) }
        TransformationManager(files).doProjectTransformations(files, PSICreator(""), checker)
        return listOf()
    }

    private fun reduceFile(file: KtFile, checker: CompilerTestChecker): KtFile {
        return TransformationManager(listOf(file))
            .doTransformationsForFile(file, checker)
    }


}