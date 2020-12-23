package com.stepanov.bbf.reduktor.passes

import com.intellij.psi.PsiFile
import com.stepanov.bbf.reduktor.executor.CompilerTestChecker
import org.apache.log4j.Logger
import org.jetbrains.kotlin.psi.KtVisitorVoid

abstract class SimplificationPass: KtVisitorVoid() {
    abstract fun simplify()

    companion object {
        lateinit var checker: CompilerTestChecker
        val file: PsiFile
            get() = checker.curFile.psiFile
        val log = Logger.getLogger("transformationManagerLog")
    }

}