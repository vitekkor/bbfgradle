package com.stepanov.bbf.bugfinder.executor.compilers

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.impl.source.tree.TreeElement
import com.stepanov.bbf.bugfinder.executor.CommonCompiler
import com.stepanov.bbf.bugfinder.executor.CompilationChecker
import com.stepanov.bbf.bugfinder.executor.Project
import com.stepanov.bbf.bugfinder.util.getAllParentsWithoutNode
import org.apache.log4j.Logger
import org.jetbrains.kotlin.psi.KtFile

class MutationChecker(compilers: List<CommonCompiler>, val otherFiles: Project? = null) :
    CompilationChecker(compilers) {

    fun replacePSINodeIfPossible(file: KtFile, node: PsiElement, replacement: PsiElement) =
        replaceNodeIfPossible(file, node.node, replacement.node)

    fun replaceNodeIfPossible(file: KtFile, node: ASTNode, replacement: ASTNode): Boolean {
        log.debug("Trying to replace $node on $replacement")
        if (node.text.isEmpty() || node == replacement) return checkCompiling(file, otherFiles)
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
                if (!checkCompiling(file, otherFiles)) {
                    log.debug("Result = false\nText:\n${file.text}")
                    p.replaceChild(replCopy, node)
                    return false
                } else {
                    log.debug("Result = true\nText:\n${file.text}")
                    return true
                }
            } catch (e: Error) {
            }
        }
        return false
    }

    fun addNodeIfPossible(file: KtFile, anchor: PsiElement, node: PsiElement, before: Boolean = false): Boolean {
        log.debug("Trying to add $node to $anchor")
        if (node.text.isEmpty() || node == anchor) return checkCompiling(
            file,
            otherFiles
        )
        try {
            val addedNode =
                if (before) anchor.parent.addBefore(node, anchor)
                else anchor.parent.addAfter(node, anchor)
            if (checkCompiling(file, otherFiles)) {
                log.debug("Result = true\nText:\n${file.text}")
                return true
            }
            log.debug("Result = false\nText:\n${file.text}")
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
    private val log = Logger.getLogger("mutatorLogger")
}