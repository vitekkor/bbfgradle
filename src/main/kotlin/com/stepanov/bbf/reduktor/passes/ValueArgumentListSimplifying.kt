package com.stepanov.bbf.reduktor.passes

import com.stepanov.bbf.bugfinder.util.debugPrint
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.executor.CompilerTestChecker
import org.jetbrains.kotlin.psi.*

class ValueArgumentListSimplifying(private val file: KtFile, private val checker: CompilerTestChecker) {

    fun transform() {
        file.getAllPSIChildrenOfType<KtValueArgumentList>().reversed().forEach { argList ->
            if (argList.arguments.size == 0) return@forEach
            var i = 0
            while (i < argList.arguments.size - 1) {
                val cur = argList.arguments[i].copy() as KtValueArgument
                val next = argList.arguments[i + 1]
                argList.removeArgument(i)
                if (!checker.checkTest(file.text)) {
                    ++i
                    argList.addArgumentBefore(cur, next)
                }
            }
            //Handle last arg
            val last = argList.arguments.last()
            val lastCopy = argList.arguments.last().copy() as KtValueArgument
            argList.removeArgument(last)
            if (!checker.checkTest(file.text)) {
                argList.addArgument(lastCopy)
            }
        }
    }

}