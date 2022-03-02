package com.stepanov.bbf.bugfinder.gitinfocollector

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiMethod
import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.executor.checkers.CoverageGuider
import com.stepanov.bbf.bugfinder.util.*
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.util.getAllChildren
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.psiUtil.parents
import java.io.File
import kotlin.collections.flatMap

class FilePatchHandler(private val patches: List<FilePatch>) {

    data class FilePatchWithModifiedFunctions(
        val filePath: String,
        val psiFile: PsiFile,
        val modifiedFunctions: List<PsiElement>
    )

    fun getListOfAffectedLines(skipTests: Boolean, pathToSerialized: String): List<Pair<String, List<Int>>> =
        try {
            val serializedAffectedLines = File(pathToSerialized).readText()
            Json.decodeFromString(serializedAffectedLines)
        } catch (e: Exception) {
            calculateListOfAffectedLines(skipTests).also { File(pathToSerialized).writeText(Json.encodeToString(it)) }
        }


    private fun calculateListOfAffectedLines(skipTests: Boolean): List<Pair<String, List<Int>>> {
        val diffMatchPatch = DiffMatchPatch()
        val listOfAffectedLines = mutableListOf<Pair<String, List<Int>>>()
        val filesPathsToModifiedFunctions =
            getFilePatchesWithModifiedFunctions(skipTests)
                .groupBy { it.filePath }
                .entries
                .map {
                    FilePatchWithModifiedFunctions(
                        it.value.first().filePath,
                        it.value.first().psiFile,
                        it.value.flatMap { it.modifiedFunctions }.filterDuplicatesBy { it.text.trim() }
                    )
                }
                .filter { it.modifiedFunctions.isNotEmpty() }
                .map {
                    FilePatchWithModifiedFunctions(
                        it.filePath,
                        it.psiFile,
                        it.modifiedFunctions.filterDuplicatesBy { SignatureCollector.collectSignature(it) })
                }
        val lastCommit = CoverageGuider.lastCommit
        val size = filesPathsToModifiedFunctions.size
        var i = 0
        for ((filePath, _, modifiedFunctions) in filesPathsToModifiedFunctions) {
            if (!filePath.contains("KotlinTypeMapper")) continue
            var replacedFilePath = filePath
            println("f = $filePath $i from $size")
            ++i
            val localChangedLines = mutableSetOf<Int>()
            var fl = false
            val endFileSourceText =
                GitRepo.getFileOnRevision(lastCommit, filePath).trim().ifEmpty {
                    fl = true
                    GitRepo.getFileOnRevision(lastCommit, filePath.replace(".java", ".kt"))
                }
            val startFileText = getPreviousState(filePath)
            val (_, startFuncToSignatures) = createPsiFileAndCalcSignatures(filePath, startFileText)
            val (_, endFuncToSignatures) =
                if (fl) {
                    createPsiFileAndCalcSignatures(filePath.replace(".java", ".kt"), endFileSourceText)
                } else {
                    createPsiFileAndCalcSignatures(filePath, endFileSourceText)
                }
            val modifiedFunctionsToItsSignatures =
                modifiedFunctions.map { it to SignatureCollector.collectSignatures(listOf(it)).first() }
            for ((modifiedFunc, modifiedFuncSign) in modifiedFunctionsToItsSignatures) {
                println("modified funcName = ${(modifiedFunc as? KtNamedFunction)?.name ?: ((modifiedFunc as? PsiMethod)?.name)}")
                println("text = ${modifiedFunc.text}")
                println("\n")
                val startPsiFunc = startFuncToSignatures.find { it.second.almostEquals(modifiedFuncSign) }?.first
                val endPsiFunc =
                    endFuncToSignatures.find { it.second.almostEquals(modifiedFuncSign) }?.first ?: continue
                val sourceCodeRange =
                    with(endPsiFunc) {
                        when (this) {
                            is KtNamedFunction -> getSourceCodeLinesRange()
                            is PsiMethod -> getSourceCodeLinesRange()
                            else -> listOf()
                        }
                    }
                val baseLineNumber = sourceCodeRange.first()
                if (startPsiFunc == null) {
                    println("ADD FULL FUNC")
                    sourceCodeRange.forEach(localChangedLines::add)
                } else {
                    println("CALC DIFF")
                    val diffs = diffMatchPatch.diffMain(endPsiFunc.text, startPsiFunc.text)
                    val diffsWithLineNumbers = mutableListOf<Pair<IntRange, DiffMatchPatch.Diff>>()
                    var curLines = 0
                    for (i in 0 until diffs.size) {
                        val numOfSpaces = diffs[i].text.count { it == '\n' }
                        diffsWithLineNumbers.add(curLines..curLines + numOfSpaces to diffs[i])
                        curLines += numOfSpaces
                    }
                    diffsWithLineNumbers.forEach {
                        if (it.second.operation != DiffMatchPatch.Operation.EQUAL) {
                            it.first.forEach { localChangedLines.add(it + baseLineNumber) }
                        }
                    }
                }
            }
            listOfAffectedLines.add(filePath to localChangedLines.toList())
        }
        return listOfAffectedLines
    }

    private fun getPreviousState(filePath: String): String {
        val commitsWhichModifiedFile =
            GitRepo
                .executeProcess("git --git-dir ${CompilerArgs.pathToKotlin}.git log --follow -- $filePath")
                .split("\n")
                .filter { it.matches(Regex("commit [0-9a-z]{40}")) }
                .map { it.split(" ").last() }
                .reversed()
        val myCommits = File(CoverageGuider.pathToCommits).readText().split("\n").reversed()
        for (commit in myCommits) {
            val indexInCommitsOfModifiedFile = commitsWhichModifiedFile.indexOfFirst {
                it.contains(commit)
            }
            if (indexInCommitsOfModifiedFile != -1) {
                val previousCommit = commitsWhichModifiedFile.getOrNull(indexInCommitsOfModifiedFile - 1)
                    ?: commitsWhichModifiedFile.getOrNull(indexInCommitsOfModifiedFile)!!
                return GitRepo.getFileOnRevision(previousCommit, filePath)
            }
        }
        return ""
    }

    //RTV: Filename to modified functions
    fun getFilePatchesWithModifiedFunctions(skipTests: Boolean): List<FilePatchWithModifiedFunctions> {
        val affectedFuncs = mutableSetOf<FilePatchWithModifiedFunctions>()
        val patchToPsi = getPatchesToPsi(skipTests).reversed()
        for ((filePatch, psiFile) in patchToPsi) {
            println("fileName = ${psiFile?.name}")
            psiFile ?: continue
            val affectedFuncsForFile = mutableSetOf<PsiElement>()
            for (patch in filePatch.patches) {
                //println("ADDED LINES = ${patch.addedLines.size}")
                //println(patch.addedLines.joinToString("\n"))
                val affectedNodes =
                    psiFile.getNodesBetweenLines(patch.startNewLine, patch.startNewLine + patch.numOfNewLines)
                affectedNodes.forEach { node ->
                    if (node is PsiMethod || node is KtNamedFunction) {
                        affectedFuncsForFile.add(node)
                    } else {
                        val parentFunNode =
                            node.parents.firstOrNull { it is KtNamedFunction || it is PsiMethod } ?: return@forEach
                        if (parentFunNode is KtNamedFunction && parentFunNode.bodyExpression != null) {
                            if (node in parentFunNode.bodyExpression!!.getAllChildren()) {
                                affectedFuncsForFile.add(parentFunNode)
                            }
                        }
                        if (parentFunNode is PsiMethod && parentFunNode.body != null) {
                            if (node in parentFunNode.body!!.getAllChildren()) {
                                affectedFuncsForFile.add(parentFunNode)
                            }
                        }
                    }
//                    node.parents.forEach { pnode ->
//                        if (pnode is KtNamedFunction && pnode.bodyExpression != null) {
//                            if (node in pnode.bodyExpression!!.getAllChildren()) {
//                                affectedFuncsForFile.add(pnode)
//                            }
//                        }
//                        if (pnode is PsiMethod && pnode.body != null) {
//                            if (node in pnode.body!!.getAllChildren()) {
//                                affectedFuncsForFile.add(pnode)
//                            }
//                        }
//                    }
                }
            }
            //println("Affected funcs = ${affectedFuncsForFile.map { it.getName() }}")
            //println("----------------------------------")
            if (affectedFuncsForFile.isNotEmpty()) {
                affectedFuncs.add(
                    FilePatchWithModifiedFunctions(
                        filePatch.fileName,
                        psiFile,
                        affectedFuncsForFile.toList()
                        // affectedFuncsForFile.groupBy { it.first }.entries.map { it.key to it.value.map { it. } }
                    )
                )
            }
        }
        return affectedFuncs.toList()
    }

    private fun createPsiFileAndCalcSignatures(filePath: String, fileSourceText: String) =
        if (filePath.endsWith("java")) {
            with(PSICreator.getPsiForJava(fileSourceText)) {
                val funcToSign = getAllPSIChildrenOfType<PsiMethod>().map {
                    it as PsiElement to SignatureCollector.collectSignatures(listOf(it)).first()
                }
                this to funcToSign
            }
        } else {
            with(PSICreator.getPsiForTextWithName(fileSourceText, filePath.substringAfterLast('/'))) {
                val funcToSign = getAllPSIChildrenOfType<KtNamedFunction>().map {
                    it as PsiElement to SignatureCollector.collectSignatures(listOf(it)).first()
                }
                this to funcToSign
            }
        }

    fun getListOfAffectedFunctions(skipTests: Boolean) =
        getFilePatchesWithModifiedFunctions(skipTests).flatMap { it.modifiedFunctions }

//    fun getListOfAffectedFunctions(skipTests: Boolean): List<PsiElement> {
//        val affectedFuncs = mutableSetOf<PsiElement>()
//        val patchToPsi = getPatchesToPsi(skipTests)
//        for ((filePatch, psiFile) in patchToPsi) {
//            for (patch in filePatch.patches) {
//                val affectedNodes =
//                    psiFile!!.getNodesBetweenLines(patch.startNewLine, patch.startNewLine + patch.numOfNewLines)
//                affectedNodes.forEach { node ->
//                    if (node is PsiMethod || node is KtNamedFunction) affectedFuncs.add(node)
//                    node.parents.forEach { pnode ->
//                        if (pnode is PsiMethod || pnode is KtNamedFunction) affectedFuncs.add(pnode)
//                    }
//                }
//            }
//        }
//        return affectedFuncs.toList()
//    }

    private fun getPatchesToPsi(skipTests: Boolean): List<Pair<FilePatch, PsiFile?>> {
        val filteredPatches =
            if (skipTests) patches.filterNot { it.fileName.let { it.contains("testData") || it.contains("tests") } }
            else patches
        val patchToPsi = filteredPatches.map {
            when {
                it.fileName.endsWith(".kt") -> it to PSICreator.getPsiForTextWithName(
                    it.text,
                    it.fileName.substringAfterLast('/')
                )
                it.fileName.endsWith(".java") -> it to PSICreator.getPsiForJavaWithName(
                    it.text,
                    CompilerArgs.baseDir + "/" + it.fileName.substringAfterLast('/')
                )
                else -> it to null
            }
        }.filter { it.second != null }
        return patchToPsi
    }
}