package com.stepanov.bbf.bugfinder.mutator.transformations

import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtPsiFactory

abstract class Factory {
    companion object {
        lateinit var file: KtFile
    }

    val psiFactory = KtPsiFactory(file.project)
}