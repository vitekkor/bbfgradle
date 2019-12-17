package com.stepanov.bbf.reduktor.passes

import com.intellij.psi.PsiComment
import com.stepanov.bbf.reduktor.executor.CommonCompilerCrashTestChecker
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.psi.KtFile

class DeleteComments(private val checker: CommonCompilerCrashTestChecker) {

    fun transform(file: KtFile) {
        file.getAllPSIChildrenOfType<PsiComment>().forEach { checker.removeNodeIfPossible(file.node, it.node) }
    }
}

