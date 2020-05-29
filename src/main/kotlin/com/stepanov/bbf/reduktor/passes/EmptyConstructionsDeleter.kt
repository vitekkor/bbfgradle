package com.stepanov.bbf.reduktor.passes

import com.stepanov.bbf.reduktor.executor.CompilerTestChecker
import com.stepanov.bbf.reduktor.util.getAllChildrenNodes
import org.jetbrains.kotlin.KtNodeTypes
import org.jetbrains.kotlin.psi.KtFile

class ConstructionsDeleter: SimplificationPass() {

    override fun simplify() {
        file.node.getAllChildrenNodes()
                .filter { deletingConstructions.contains(it.elementType) }
                .forEach { checker.removeNodeIfPossible(it) }
    }

    val deletingConstructions = listOf(KtNodeTypes.IF, KtNodeTypes.FOR, KtNodeTypes.TRY,
            KtNodeTypes.CATCH, KtNodeTypes.FINALLY, KtNodeTypes.WHILE, KtNodeTypes.DO_WHILE, KtNodeTypes.WHEN)

}