package com.stepanov.bbf.bugfinder.mutator.metamorphicTransformations

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.util.replaceThis
import org.jetbrains.kotlin.psi.KtBlockExpression
import org.jetbrains.kotlin.psi.KtNamedFunction
import kotlin.random.Random

class RunLetTransformation: MetamorphicTransformation() {
    override fun transform(
        mutationPoint: PsiElement,
        scope: HashMap<Variable, MutableList<String>>,
        expected: Boolean
    ) {
        val block = file.getAllPSIChildrenOfType<KtBlockExpression>().randomOrNull() ?: return
        val isFunctionBody = (block.parent as? KtNamedFunction) != null
        val runOrLet = if (isFunctionBody) "=" else "" + if (Random.nextBoolean()) "kotlin.run" else "let"
        val newBlock = Factory.psiFactory.createExpressionIfPossible("$runOrLet ${block.text}") ?: return
        block.replaceThis(newBlock)
    }
}