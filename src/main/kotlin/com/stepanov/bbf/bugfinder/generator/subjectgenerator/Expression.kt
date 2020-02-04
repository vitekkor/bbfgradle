package com.stepanov.bbf.bugfinder.generator.subjectgenerator

import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtPsiFactory

abstract class Expression {

    companion object {
        lateinit var factory: KtPsiFactory
    }
}