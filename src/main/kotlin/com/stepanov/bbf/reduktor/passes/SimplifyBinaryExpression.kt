package com.stepanov.bbf.reduktor.passes

import com.stepanov.bbf.bugfinder.util.debugPrint
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.executor.CompilerTestChecker
import org.jetbrains.kotlin.psi.KtBinaryExpression
import org.jetbrains.kotlin.psi.KtFile

class SimplifyBinaryExpression : SimplificationPass() {
    override fun simplify() {
        file.getAllPSIChildrenOfType<KtBinaryExpression>().reversed().forEach { binExp ->
            tryToReplace(binExp, true)
        }
        file.getAllPSIChildrenOfType<KtBinaryExpression>().reversed().forEach { binExp ->
            tryToReplace(binExp, false)
        }
    }

    //leftOrRight = true if left
    private fun tryToReplace(binExp: KtBinaryExpression, leftOrRight: Boolean): Boolean {
        val replacement = if (leftOrRight) binExp.left else binExp.right
        replacement?.let { return checker.replaceNodeIfPossible(binExp, it) }
        return false
    }
}