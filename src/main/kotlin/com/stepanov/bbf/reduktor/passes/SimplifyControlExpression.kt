package com.stepanov.bbf.reduktor.passes

import com.stepanov.bbf.reduktor.executor.CompilerTestChecker
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.psi.*


class SimplifyControlExpression : SimplificationPass() {
    override fun simplify() {
        val conExp = file.getAllPSIChildrenOfType<KtExpression>().filter { it.javaClass.simpleName in controlExpressions }
        val expToCond =
                conExp.map { exp ->
                    exp to
                            when (exp) {
                                is KtIfExpression -> exp.condition
                                is KtForExpression -> exp.loopParameter
                                is KtWhileExpression -> exp.condition
                                is KtWhenExpression -> exp.subjectExpression
                                else -> null
                            }
                }
                        .filter { it.second != null }
        expToCond.forEach { checker.replaceNodeIfPossible(it.first, it.second!!) }
    }


    private val controlExpressions =
            listOf("KtIfExpression", "KtWhenExpression", "KtWhileExpression", "KtForExpression")
}