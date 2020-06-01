package com.stepanov.bbf.bugfinder.executor.checkers

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.stepanov.bbf.bugfinder.executor.CommonCompiler
import com.stepanov.bbf.bugfinder.executor.project.BBFFile
import com.stepanov.bbf.bugfinder.executor.project.Header
import com.stepanov.bbf.bugfinder.executor.project.LANGUAGE
import com.stepanov.bbf.bugfinder.executor.project.Project

class TreeChanger(private val compilers: List<CommonCompiler>) {
    constructor(compiler: CommonCompiler) : this(listOf(compiler))

    private fun prepareChecker(file: PsiFile): MutationChecker {
        val bbfFile = BBFFile("tmp.kt", file)
        val pr = Project(Header.createHeader(""), listOf(bbfFile), LANGUAGE.KOTLIN)//Project.createFromCode(file.text)
        return MutationChecker(compilers, pr, pr.files.first())
    }

    fun replaceNodeIfPossible(file: PsiFile, node: ASTNode, replacement: ASTNode) =
        prepareChecker(file).replaceNodeIfPossible(node, replacement)

    fun addNodeIfPossible(file: PsiFile, anchor: PsiElement, node: PsiElement, before: Boolean = false) =
        prepareChecker(file).addNodeIfPossible(anchor, node, before)

    fun addNodeIfPossibleWithNode(
        file: PsiFile,
        anchor: PsiElement,
        node: PsiElement,
        before: Boolean = false
    ): PsiElement? = prepareChecker(file).addNodeIfPossibleWithNode(anchor, node, before)
}