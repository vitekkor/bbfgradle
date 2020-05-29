package com.stepanov.bbf.reduktor.executor

import com.intellij.lang.ASTNode
import com.intellij.lang.FileASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.stepanov.bbf.bugfinder.executor.project.BBFFile
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.reduktor.executor.error.Error
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtPsiFactory

interface CompilerTestChecker {

    fun removeNodeIfPossible(node: ASTNode): Boolean
    fun removeNodesIfPossible(nodes: List<ASTNode>): Boolean
    fun replaceNodeIfPossible(node: ASTNode, replacement: ASTNode): Boolean
    fun replaceNodeIfPossible(node: PsiElement, replacement: PsiElement) =
            replaceNodeIfPossible(node.node, replacement.node)
    fun replaceNodeOnItChild(node: ASTNode, replacement: ASTNode): ASTNode? = node
    fun checkTest(): Boolean
    fun getErrorMessage(): String
    fun refreshAlreadyCheckedConfigurations() {
        alreadyChecked.clear()
    }

    val project: Project
    val curFile: BBFFile
    val alreadyChecked: HashMap<Int, Boolean>
}