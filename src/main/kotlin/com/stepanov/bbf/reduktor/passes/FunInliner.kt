package com.stepanov.bbf.reduktor.passes

import com.intellij.psi.PsiWhiteSpace
import com.stepanov.bbf.reduktor.executor.CompilerTestChecker
import com.stepanov.bbf.reduktor.util.getAllChildrenNodes
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.util.replaceThis
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtCallExpression
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.KtPsiFactory
import java.lang.Exception

class FunInliner : SimplificationPass() {

    override fun simplify() {
        val funcs = file.getAllPSIChildrenOfType<KtNamedFunction>()
        for (f in funcs) {
            for (c in f.getAllPSIChildrenOfType<KtCallExpression>()) {
                val calledFunc = funcs.filter { c.calleeExpression?.text == it.name }
                if (calledFunc.size == 1) {
                    val called = calledFunc.first()
                    val lines = called.getAllPSIChildrenOfType<PsiWhiteSpace>()
                        .fold(0, { acc, next -> acc + next.text.count { it == '\n' } })
                    if (called != f && lines < 10 && called.valueParameters.size == c.valueArguments.size) {
                        performInlining(c, called)
                    }
                }
            }
        }
    }

    private fun performInlining(call: KtCallExpression, f: KtNamedFunction) {
        val funCopy = f.copy() as KtNamedFunction
        try {
            funCopy.bodyExpression?.let { body ->
                for ((i, arg) in call.valueArguments.withIndex()) {
                    val par = f.valueParameters[i]
                    val argCopy = factory.createExpressionIfPossible(arg.text) ?: return

                    body.node.getAllChildrenNodes()
                        .filter { it.elementType == KtTokens.IDENTIFIER }
                        .filter { it.text == par.nameIdentifier?.text }
                        .forEach { it.psi.replaceThis(argCopy) }
                }
                if (f.hasBlockBody()) {
                    KtPsiFactory(file.project).createExpressionIfPossible("run ${body.text}")
                } else {
                    KtPsiFactory(file.project).createExpressionIfPossible("run {${body.text}}")
                }?.let {
                    checker.replaceNodeIfPossible(call.node, it.node)
                }
            }
        } catch (e: Exception) {
            return
        } catch (e: Error) {
            return
        }
    }

    private val factory = KtPsiFactory(file)
}