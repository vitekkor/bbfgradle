package com.stepanov.bbf.bugfinder.mutator.transformations

import com.intellij.psi.PsiFile
import com.stepanov.bbf.reduktor.parser.PSICreator
import org.jetbrains.kotlin.psi.KtPsiFactory

object Factory {
    val file = PSICreator("").getPSIForText("")
    val psiFactory = KtPsiFactory(file.project)
}
//abstract class Factory {
//    companion object {
//
//        val psiFactory = KtPsiFactory(file.project)
//    }
//
//    val psiFactory = KtPsiFactory(file.project)
//}