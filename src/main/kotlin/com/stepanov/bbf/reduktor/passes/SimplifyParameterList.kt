package com.stepanov.bbf.reduktor.passes

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.util.debugPrint
import com.stepanov.bbf.bugfinder.util.getAllPSIDFSChildrenOfType
import com.stepanov.bbf.reduktor.executor.CompilerTestChecker
import com.stepanov.bbf.reduktor.util.getAllChildren
import org.jetbrains.kotlin.psi.*

class SimplifyParameterList(private val file: KtFile, private val checker: CompilerTestChecker) {

    fun transform() {
        simplifyParameterList()
        simplifyArgumentList()
    }

    private fun simplifyArgumentList() {
        val argLists = file.getAllChildren()
            .filter { it.children.any { it is KtValueArgumentList } }
            .map { it.valueArgumentsList }
            .filter { it.arguments.isNotEmpty() }

        for (argList in argLists) {
            var i = 0
            while (i < argList.arguments.size - 1) {
                val curParam = argList.arguments[i]
                val curParamCopy = curParam.copy() as KtValueArgument
                val nextParam = argList.arguments[i + 1]
                argList.removeArgument(curParam)
                if (!checker.checkTest(file.text)) {
                    argList.addArgumentBefore(curParamCopy, nextParam)
                    i++
                }
            }
            //Try to remove last
            val lastParam = argList.arguments.last()
            val lastParamCopy = lastParam.copy() as KtValueArgument
            argList.removeArgument(lastParam)
            if (!checker.checkTest(file.text)) {
                argList.addArgument(lastParamCopy)
            }
        }
    }

    private fun simplifyParameterList() {
        val parLists = file.getAllChildren()
            .filter { it.children.any { it is KtParameterList } }
            .map { it.parameterList }
            .filter { it.parameters.isNotEmpty() }

        for (parList in parLists) {
            var i = 0
            while (i < parList.parameters.size - 1) {
                val curParam = parList.parameters[i]
                val curParamCopy = curParam.copy() as KtParameter
                val nextParam = parList.parameters[i + 1]
                parList.removeParameter(curParam)
                if (!checker.checkTest(file.text)) {
                    parList.addParameterBefore(curParamCopy, nextParam)
                    i++
                }
            }
            //Try to remove last
            val lastParam = parList.parameters.last()
            val lastParamCopy = lastParam.copy() as KtParameter
            parList.removeParameter(lastParam)
            if (!checker.checkTest(file.text)) {
                parList.addParameter(lastParamCopy)
            }
        }
    }

    private val PsiElement.valueArgumentsList: KtValueArgumentList
        get() = this.getAllPSIDFSChildrenOfType<KtValueArgumentList>().first()

    private val PsiElement.parameterList: KtParameterList
        get() = this.getAllPSIDFSChildrenOfType<KtParameterList>().first()
}