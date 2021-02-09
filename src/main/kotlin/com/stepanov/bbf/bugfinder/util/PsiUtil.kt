package com.stepanov.bbf.bugfinder.util

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiComment
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiWhiteSpace
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.KtStringTemplateEntry
import kotlin.system.exitProcess

fun PsiFile.getNodesBetweenWhitespaces(begin: Int, end: Int): List<PsiElement> {
    val resList = mutableListOf<PsiElement>()
    var whiteSpacesCounter = 0
    for (node in getAllPSIDFSChildrenOfType<PsiElement>()) {
        if (node is PsiWhiteSpace || node is PsiComment) whiteSpacesCounter += node.text.count { it == '\n' }
        if (this is KtFile && node is KtStringTemplateEntry) whiteSpacesCounter += node.text.count { it == '\n' }
        if (whiteSpacesCounter in begin..end) resList.add(node)
        if (whiteSpacesCounter > end) break
    }
    return resList
}

fun KtNamedFunction.isUnit() = this.typeReference == null && this.hasBlockBody()