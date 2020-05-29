package com.stepanov.bbf.reduktor.passes

import com.stepanov.bbf.reduktor.executor.CompilerTestChecker
import com.stepanov.bbf.reduktor.util.getAllChildrenNodes
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.util.replaceThis

import org.apache.log4j.Logger
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.KtReturnExpression

class ReturnValueToVoid : SimplificationPass() {
    override fun simplify() {
        transformFile(file as KtFile, false)
        transformFile(file as KtFile, true)
    }

    fun transformFile(file: KtFile, justKeyword: Boolean) {
        val funcs = file.getAllPSIChildrenOfType<KtNamedFunction>().filter { it.hasDeclaredReturnType() }
        for (f in funcs) {
            val oldFun = f.copy() as KtNamedFunction
            f.node.removeChild(f.typeReference!!.node)
            f.node.removeChild(f.colon!!.node)
            if (!justKeyword) {
                f.node.getAllChildrenNodes()
                        .filter { it.psi is KtReturnExpression }
                        .forEach { f.node.removeChild(it) }
            } else {
                f.node.getAllChildrenNodes()
                        .filter { it.psi is KtReturnExpression }
                        .filter { it.findChildByType(KtTokens.RETURN_KEYWORD) != null }
                        .map { it.psi as KtReturnExpression }
                        .forEach { f.node.removeChild(it.returnKeyword.node) }
            }

            if (!checker.checkTest()) {
                f.replaceThis(oldFun)
                log.debug("REPLACED BACK")
            } else {
                log.debug("SUCCESSFUL DELETING OF RETURN VALUE")
            }
        }
    }

    private val log = Logger.getLogger("transformationManagerLog")
}