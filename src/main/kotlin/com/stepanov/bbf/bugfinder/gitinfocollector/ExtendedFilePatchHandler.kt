package com.stepanov.bbf.bugfinder.gitinfocollector

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiMethod
import com.stepanov.bbf.bugfinder.util.getNodesBetweenLines
import com.stepanov.bbf.reduktor.parser.PSICreator
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.psiUtil.parents

class ExtendedFilePatchHandler(private val patches: List<Pair<FilePatch, String>>) {

    fun getListOfAffectedFunctions(skipTests: Boolean): List<PsiElement> {
        val affectedFuncs = mutableSetOf<PsiElement>()
        val filteredPatches =
            if (skipTests) patches.filterNot { it.first.fileName.let { it.contains("testData") || it.contains("tests") } }
            else patches
        val patchToPsi = filteredPatches.map {
            when {
                it.first.fileName.endsWith(".kt") -> it to PSICreator.getPsiForTextWithName(
                    it.first.text,
                    it.first.fileName.substringAfterLast('/')
                )
                it.first. fileName.endsWith(".java") -> it to PSICreator.getPsiForJava(it.first.text)
                else -> it to null
            }
        }.filter { it.second != null }
        for ((filePatch, psiFile) in patchToPsi) {
            for (patch in filePatch.first.patches) {
                val affectedNodes =
                    psiFile!!.getNodesBetweenLines(patch.startNewLine, patch.startNewLine + patch.numOfNewLines)
                affectedNodes.forEach { node ->
                    if (node is PsiMethod || node is KtNamedFunction) affectedFuncs.add(node)
                    node.parents.forEach { pnode ->
                        if (pnode is PsiMethod || pnode is KtNamedFunction) affectedFuncs.add(pnode)
                    }
                }
            }
        }
        return affectedFuncs.toList()
    }
}