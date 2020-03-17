package com.stepanov.bbf.bugfinder.mutator.transformations

import com.intellij.psi.PsiFile
import org.jetbrains.kotlin.psi.KtPsiFactory

abstract class Factory {
    companion object {
        lateinit var file: PsiFile
    }

    val psiFactory = KtPsiFactory(file.project)
}