package com.stepanov.bbf.reduktor.passes

import com.intellij.psi.PsiWhiteSpace
import com.stepanov.bbf.reduktor.executor.CompilerTestChecker
import com.stepanov.bbf.reduktor.util.getAllChildrenNodes
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtPsiFactory

class RemoveWhitespaces : SimplificationPass() {

    override fun simplify() {
        file.node.getAllChildrenNodes()
                .filter { it.psi is PsiWhiteSpace }
                .filter { it.text.count { it == '\n' } > 1}
                .forEach {
                    checker.replaceNodeIfPossible( it, KtPsiFactory(file.project).createWhiteSpace("\n").node)
                }
        val children = file.node.getAllChildrenNodes()
        children
                .filterIndexed { index, astNode -> index > 0 && children[index - 1] is PsiWhiteSpace && astNode.psi is PsiWhiteSpace }
                .forEach { checker.replaceNodeIfPossible(it.psi, KtPsiFactory(file.project).createWhiteSpace(" ")) }
    }
}