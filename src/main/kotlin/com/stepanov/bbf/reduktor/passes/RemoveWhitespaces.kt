package com.stepanov.bbf.reduktor.passes

import com.intellij.psi.PsiWhiteSpace
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory.psiFactory
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.executor.CompilerTestChecker
import com.stepanov.bbf.reduktor.util.getAllChildren
import com.stepanov.bbf.reduktor.util.getAllChildrenNodes
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtPsiFactory
import java.lang.StringBuilder

class RemoveWhitespaces : SimplificationPass() {

    override fun simplify() {
        file.node.getAllChildrenNodes()
            .filter { it.psi is PsiWhiteSpace }
            .filter { it.text.count { it == '\n' } > 1 }
            .forEach {
                val count = it.text.count { it == '\n' }
                for (i in 1 until count) {
                    val newWhitespace =
                        psiFactory.createWhiteSpace(StringBuilder().also { str -> repeat(i) { str.append("\n") } }
                            .toString())
                    log.debug("TRYING TO REPLACE ${count} on ${newWhitespace.text.count { it == '\n' }}: ")
                    val res = checker.replaceNodeIfPossible(it, newWhitespace.node)
                    log.debug(res)
                    if (res) break
                }
                //checker.replaceNodeIfPossible(it, psiFactory.createWhiteSpace("\n").node)
            }
        val children = file.node.getAllChildrenNodes()
        children
            .filterIndexed { index, astNode -> index > 0 && children[index - 1] is PsiWhiteSpace && astNode.psi is PsiWhiteSpace }
            .forEach { checker.replaceNodeIfPossible(it.psi, Factory.psiFactory.createWhiteSpace(" ")) }
    }
}