package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.util.*
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.util.capitalizeDecapitalize.toLowerCaseAsciiOnly
import kotlin.random.Random

object AddLabels : Transformation() {

    override fun transform() {
        repeat(10) {
            val randomLoop =
                file.getAllPSIChildrenOfFourTypes<KtForExpression, KtWhileExpression, KtDoWhileExpression, KtLambdaExpression>()
                    .filter { it.parent !is KtLabeledExpression }
                    .randomOrNull() as? KtExpression ?: return
            val labelRandomName = Random.getRandomVariableName(1).toLowerCaseAsciiOnly()
            val newLabeledExpression = Factory.psiFactory.createLabeledExpression("$labelRandomName@${randomLoop.text}")
            checker.replaceNodeIfPossible(randomLoop, newLabeledExpression)
        }
    }

}