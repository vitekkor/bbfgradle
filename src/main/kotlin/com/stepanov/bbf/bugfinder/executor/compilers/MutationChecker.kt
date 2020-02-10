package com.stepanov.bbf.bugfinder.executor.compilers

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.impl.source.tree.TreeElement
import com.stepanov.bbf.bugfinder.executor.CommonCompiler
import com.stepanov.bbf.bugfinder.executor.ProjectCompilationChecker
import com.stepanov.bbf.bugfinder.util.getAllParentsWithoutNode
import org.jetbrains.kotlin.psi.KtFile

class MutationChecker(compilers: List<CommonCompiler>): ProjectCompilationChecker(compilers) {

    fun replacePSINodeIfPossible(file: KtFile, node: PsiElement, replacement: PsiElement) =
        replaceNodeIfPossible(file, node.node, replacement.node)

    fun replaceNodeIfPossible(file: KtFile, node: ASTNode, replacement: ASTNode): Boolean {
        if (node.text.isEmpty() || node == replacement) return checkCompiling(file)
        for (p in node.getAllParentsWithoutNode()) {
            try {
                if (node.treeParent.elementType.index == DUMMY_HOLDER_INDEX) continue
                val oldText = file.text
                val replCopy = replacement.copyElement()
                if ((node as TreeElement).treeParent !== p) {
                    continue
                }
                p.replaceChild(node, replCopy)
                if (oldText == file.text)
                    continue
                if (!checkCompiling(file)) {
                    p.replaceChild(replCopy, node)
                    return false
                } else {
                    return true
                }
            } catch (e: Error) {
            }
        }
        return false
    }
    fun addNodeIfPossible(file: KtFile, anchor: PsiElement, node: PsiElement, before: Boolean = false): Boolean {
        if (node.text.isEmpty() || node == anchor) return checkCompiling(
            file
        )
        try {
            val addedNode =
                if (before) anchor.parent.addBefore(node, anchor)
                else anchor.parent.addAfter(node, anchor)
            if (checkCompiling(file)) return true
            addedNode.parent.node.removeChild(addedNode.node)
            return false
        } catch (e: Throwable) {
            println("e = $e")
            return false
        }
    }

    fun addNodeIfPossible(file: KtFile, anchor: ASTNode, node: ASTNode, before: Boolean = false): Boolean =
        addNodeIfPossible(file, anchor.psi, node.psi, before)

    private val DUMMY_HOLDER_INDEX: Short = 86
}