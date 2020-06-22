package com.stepanov.bbf.reduktor.passes

import com.stepanov.bbf.bugfinder.util.debugPrint
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.bugfinder.util.replaceThis
import com.stepanov.bbf.reduktor.executor.CompilerTestChecker
import org.jetbrains.kotlin.psi.*

class ValueArgumentListSimplifying : SimplificationPass() {

    override fun simplify() {
        file.getAllPSIChildrenOfType<KtValueArgumentList>().reversed().forEach { argList ->
            if (argList.arguments.size == 0) return@forEach
            var i = 0
            while (i < argList.arguments.size - 1) {
                val cur = argList.arguments[i].copy() as KtValueArgument
                val next = argList.arguments[i + 1]
                argList.removeArgument(i)
                if (!checker.checkTest()) {
                    ++i
                    argList.addArgumentBefore(cur, next)
                }
            }
            //Handle last arg
            val last = argList.arguments.last()
            val argListCopy = argList.copy() as KtValueArgumentList
            argList.removeArgument(last)
            if (!checker.checkTest()) {
                argList.replaceThis(argListCopy)
            }
        }
    }

}