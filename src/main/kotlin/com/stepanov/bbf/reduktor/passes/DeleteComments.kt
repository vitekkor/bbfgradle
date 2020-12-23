package com.stepanov.bbf.reduktor.passes

import com.intellij.psi.PsiComment
import com.stepanov.bbf.reduktor.executor.CompilerTestChecker
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType

class DeleteComments(private val checker: CompilerTestChecker): SimplificationPass() {

    override fun simplify() {
        file.getAllPSIChildrenOfType<PsiComment>().forEach { checker.removeNodeIfPossible(it.node) }
    }
}

