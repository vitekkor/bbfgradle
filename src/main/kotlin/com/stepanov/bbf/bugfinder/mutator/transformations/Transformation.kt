package com.stepanov.bbf.bugfinder.mutator.transformations

import com.intellij.psi.PsiFile
import com.stepanov.bbf.bugfinder.executor.compilers.MutationChecker
import org.apache.log4j.Logger

abstract class Transformation: Factory() {
    abstract fun transform()

    companion object {
        var file: PsiFile = Factory.file
        lateinit var checker: MutationChecker
        val log = Logger.getLogger("mutatorLogger")
    }

}