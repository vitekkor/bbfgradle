package com.stepanov.bbf.reduktor.passes

import com.stepanov.bbf.reduktor.executor.CompilerTestChecker
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.util.replaceThis
import org.jetbrains.kotlin.psi.KtCallExpression
import org.jetbrains.kotlin.psi.KtFile

class SimplifyCallExpression(private val file: KtFile, private val checker: CompilerTestChecker) {

    fun transform() {
        file.getAllPSIChildrenOfType<KtCallExpression>().forEach { call ->
            for (arg in call.valueArguments + call.lambdaArguments) {
                val argCopy = arg.copy()
                call.replaceThis(argCopy)
                if (!checker.checkTest(file.text)) {
                    argCopy.replaceThis(call)
                } else return@forEach
            }
        }
    }
}