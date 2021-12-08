package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.mutator.transformations.Factory.tryToCreateExpression
import com.stepanov.bbf.bugfinder.util.createOperationReferenceExpression
import com.stepanov.bbf.bugfinder.util.getTrue
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.util.getAllWithout
import org.jetbrains.kotlin.psi.KtBinaryExpression
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtForExpression
import org.jetbrains.kotlin.psi.KtIfExpression
import kotlin.random.Random

class ModifyForExpression : Transformation() {

    val ktFile = file as KtFile

    override fun transform() {
        repeat(5) {
            if (Random.getTrue(70)) {
                modifyFor()
            } else {
                modifyIf()
            }
        }
    }

    private fun getOtherOperations(operation: String): List<String> =
        listOf(">", ">=", "==", "<", "<=").getAllWithout(operation)

    private fun modifyIf() {
        val randomIf = ktFile.getAllPSIChildrenOfType<KtIfExpression>()
            .filter { it.condition is KtBinaryExpression }
            .randomOrNull() ?: return
        val conditionOperationReference = (randomIf.condition as KtBinaryExpression).operationReference
        val newOperationReference =
            when (conditionOperationReference.text) {
                "!=" -> "=="
                "==" -> "!="
                "!==" -> "==="
                "===" -> "!=="
                ">" -> getOtherOperations(">").randomOrNull()
                ">=" -> getOtherOperations(">=").randomOrNull()
                "<" -> getOtherOperations("<").randomOrNull()
                "<=" -> getOtherOperations("<=").randomOrNull()
                "in" -> "!in"
                "is" -> "!is"
                else -> conditionOperationReference.text
            }
        newOperationReference?.let { opRef ->
            val newRef = Factory.psiFactory.createOperationReferenceExpression(opRef) ?: return
            checker.replaceNodeIfPossible(conditionOperationReference, newRef)
        }
    }

    private fun modifyFor() {
        val randomFor = ktFile.getAllPSIChildrenOfType<KtForExpression>()
            .filter { it.loopRange is KtBinaryExpression }
            .randomOrNull() ?: return
        val subBinaryExpressions =
            listOf(randomFor.loopRange) + randomFor.loopRange!!.getAllPSIChildrenOfType<KtBinaryExpression>()
        val randomBinExp = subBinaryExpressions.randomOrNull() as? KtBinaryExpression ?: return
        val replacement =
            with(randomBinExp) {
                Factory.psiFactory.tryToCreateExpression("${right?.text} ${operationReference.text} ${left?.text}")
            } ?: return
        checker.replaceNodeIfPossible(randomBinExp, replacement)
    }
}