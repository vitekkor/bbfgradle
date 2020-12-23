package com.stepanov.bbf.reduktor.executor

import com.intellij.lang.ASTNode
import com.intellij.lang.FileASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.impl.source.tree.TreeElement
import com.stepanov.bbf.bugfinder.executor.CommonCompiler
import com.stepanov.bbf.bugfinder.executor.project.BBFFile
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.reduktor.executor.error.Error
import com.stepanov.bbf.reduktor.util.getAllChildrenNodes
import com.stepanov.bbf.reduktor.util.getAllParentsWithoutNode
import com.stepanov.bbf.reduktor.util.replaceThis
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageLocation
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSourceLocation
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtPsiFactory

interface CompilerTestChecker {

    fun removeNodeIfPossible(node: ASTNode): Boolean
    fun removeNodesIfPossible(nodes: List<ASTNode>): Boolean
    fun replaceNodeIfPossible(node: ASTNode, replacement: ASTNode): Boolean
    fun replaceNodeIfPossible(node: PsiElement, replacement: PsiElement) =
            replaceNodeIfPossible(node.node, replacement.node)
    fun replaceNodeOnItChild(node: ASTNode, replacement: ASTNode): ASTNode? = node

    @Deprecated("")
    fun replaceNodeIfPossible(tree: FileASTNode, node: ASTNode, replacement: ASTNode): Boolean
    @Deprecated("")
    fun removeNodeIfPossible(tree: FileASTNode, node: ASTNode): Boolean

    fun checkTest(): Boolean
    fun checkTest(text: String): Boolean

    fun getErrorMessage(): String
    fun getErrorMessageWithLocation(): Pair<String, List<CompilerMessageSourceLocation>>

    fun refreshAlreadyCheckedConfigurations() {
        alreadyChecked.clear()
    }

    val project: Project
    var curFile: BBFFile
    val alreadyChecked: HashMap<Int, Boolean>
}