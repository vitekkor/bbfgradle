package com.stepanov.bbf.reduktor.passes

import com.stepanov.bbf.reduktor.executor.CompilerTestChecker
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.psi.*

class EqualityMapper: SimplificationPass() {

    override fun simplify() {
        val table = file.getAllPSIChildrenOfType<KtProperty>()
                .map { it.nameIdentifier to it.initializer }
                .filter { it.first != null && it.second != null }
                .map { it.first!! to it.second!! }

        for (entry in file.getAllPSIChildrenOfType<KtExpression>()) {
            val expressions = entry.getAllPSIChildrenOfType<KtReferenceExpression>()
            for (exp in expressions) {
                val replacement = table.find { it.first.text == exp.text } ?: continue
                //Avoid recursion
                if (exp.text.trim() == replacement.first.text.trim()) continue
                val copyOfReplacement = ktFactory.createExpression(replacement.second.text)
                checker.replaceNodeIfPossible(exp, copyOfReplacement)
            }
        }
    }

    private val ktFactory = KtPsiFactory(file.project)
}