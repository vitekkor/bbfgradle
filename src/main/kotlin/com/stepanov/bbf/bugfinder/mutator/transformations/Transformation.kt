package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.executor.compilers.MutationChecker
import org.apache.log4j.Logger
import org.jetbrains.kotlin.psi.KtFile

abstract class Transformation: Factory() {
    abstract fun transform()

    companion object {
        var file: KtFile = Factory.file
        lateinit var checker: MutationChecker
        val log = Logger.getLogger("mutatorLogger")
    }

}