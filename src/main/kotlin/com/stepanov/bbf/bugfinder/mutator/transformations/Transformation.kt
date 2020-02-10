package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.executor.compilers.MutationChecker
import org.jetbrains.kotlin.psi.KtFile

abstract class Transformation: Factory() {
    abstract fun transform()

    companion object {
        lateinit var file: KtFile
        lateinit var checker: MutationChecker
    }

}