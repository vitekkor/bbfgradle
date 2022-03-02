package com.stepanov.bbf.bugfinder.gitinfocollector

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiMethod
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.bugfinder.util.getAllPSIDFSChildrenOfType
import coverage.CoverageEntry
import org.jetbrains.kotlin.psi.KtNamedFunction

object SignatureCollector {

    fun collectSignature(func: PsiElement): CoverageEntry = collectSignatures(listOf(func)).first()

    fun collectSignatures(funcs: List<PsiElement>): List<CoverageEntry> =
        if (funcs.isEmpty()) listOf()
        else
            KotlinSignatureCollector().collect(funcs.filterIsInstance<KtNamedFunction>()) +
                    JavaSignatureCollector().collect(funcs.filterIsInstance<PsiMethod>())

    fun collectSignatures(psiFile: PsiFile): List<CoverageEntry> =
        collectSignatures(psiFile.getAllPSIChildrenOfType<KtNamedFunction>() + psiFile.getAllPSIDFSChildrenOfType<PsiMethod>())

}