package com.stepanov.bbf.bugfinder.mutator.transformations

import org.jetbrains.kotlin.psi.KtExpression
import com.stepanov.bbf.bugfinder.executor.MutationChecker
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType

class AddNotNullAssertions : Transformation() {


    override fun transform() {
        file.getAllPSIChildrenOfType<KtExpression>()
                .map { tryToAddNotNullAssertion(it) }
    }

    private fun tryToAddNotNullAssertion(exp: KtExpression) {
        val newExp = psiFactory.createExpressionIfPossible("${exp.text}!!") ?: return
        MutationChecker.replacePSINodeIfPossible(file, exp, newExp)
    }
}
