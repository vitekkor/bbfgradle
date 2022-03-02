package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.reduktor.util.getAllChildren
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.util.replaceThis
import org.jetbrains.kotlin.psi.KtContextReceiverList
import org.jetbrains.kotlin.psi.KtFile
import kotlin.system.exitProcess

class RemoveContextFromFunction: Transformation() {
    override fun transform() {
        val ktFile = file as KtFile
        val randomContextReceiver = ktFile.getAllPSIChildrenOfType<KtContextReceiverList>().randomOrNull() ?: return
        val whiteSpace = Factory.psiFactory.createWhiteSpace("\n")
        randomContextReceiver.replaceThis(whiteSpace)
        if (!checker.checkCompiling()) {
            whiteSpace.replaceThis(randomContextReceiver)
        }
    }
}