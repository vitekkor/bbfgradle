package com.stepanov.bbf.reduktor.passes

import com.stepanov.bbf.reduktor.executor.CompilerTestChecker
import com.stepanov.bbf.reduktor.util.generateDefValuesAsString
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtNameReferenceExpression
import org.jetbrains.kotlin.psi.KtPsiFactory
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.calls.callUtil.getType

//
class MinorSimplifyings(private val file: KtFile, private val checker: CompilerTestChecker,
                        private val ctx: BindingContext) {
    fun transform() {
        //Replace variable on constant if we can get type
        try {
            file.getAllPSIChildrenOfType<KtNameReferenceExpression>().forEach {
                it.getType(ctx)?.let { type ->
                    val exp = KtPsiFactory(file.project).createExpression(
                        generateDefValuesAsString(
                            type.toString()
                        )
                    )
                    checker.replaceNodeIfPossible(file, it, exp)
                }
            }
        } catch (e: Exception) {
           return
        }
    }

}