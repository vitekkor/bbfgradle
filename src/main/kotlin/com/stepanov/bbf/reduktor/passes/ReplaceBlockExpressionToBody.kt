package com.stepanov.bbf.reduktor.passes

import com.stepanov.bbf.reduktor.executor.CompilerTestChecker
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.parents

class ReplaceBlockExpressionToBody : SimplificationPass() {

    override fun simplify() {
        val blocks = file.getAllPSIChildrenOfType<KtBlockExpression>()
        val parents = blocks.map { it.parents.filter { it::class in avExpressions }.toList() }
        val bodies = blocks.map { it.copy() as KtBlockExpression }
        for (body in bodies) {
            if (body.lBrace != null)
                body.removeChild(body.lBrace!!.node)
            if (body.rBrace != null)
                body.removeChild(body.rBrace!!.node)
        }
        for (i in 0 until blocks.size) {
            for (par in parents[i]) {
                if (checker.replaceNodeIfPossible(par, bodies[i]))
                    break
            }
        }
    }

    val avExpressions = listOf(KtIfExpression::class, KtWhenExpression::class, KtWhileExpression::class,
            KtDoWhileExpression::class, KtNamedFunction::class, KtForExpression::class, KtTryExpression::class)

}