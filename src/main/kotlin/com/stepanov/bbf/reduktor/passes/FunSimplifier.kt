package com.stepanov.bbf.reduktor.passes

import com.stepanov.bbf.bugfinder.util.getAllChildrenOfCurLevel
import com.stepanov.bbf.reduktor.executor.CompilerTestChecker
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.util.getSignature
import org.jetbrains.kotlin.psi.*

class FunSimplifier(private val file: KtFile, private val checker: CompilerTestChecker) {
    fun transform() {
        val namedFuncs = file.getAllPSIChildrenOfType<KtNamedFunction>()
        for (f in namedFuncs) {
            try {
                if (f.hasBlockBody()) {
                    val newNode = f.bodyBlockExpression?.copy() as KtBlockExpression? ?: continue
                    newNode.lBrace?.delete()
                    newNode.rBrace?.delete()
                    newNode.getAllPSIChildrenOfType<KtReturnExpression>().forEach {
                        it.returnKeyword.delete()
                    }
                    val signature = f.getAllChildrenOfCurLevel()
                        .takeWhile { it !is KtBlockExpression }
                        .joinToString("") { it.text }
                    val newFun = KtPsiFactory(file.project).createFunction("$signature = ${newNode.text}")
                    checker.replaceNodeIfPossible(file, f, newFun)
                }
            } catch (e: Exception) {
                continue
            }
        }
    }
}