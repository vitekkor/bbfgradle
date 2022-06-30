package com.stepanov.bbf.bugfinder.mutator.metamorphicTransformations

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory.tryToCreateExpression
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.util.replaceThis
import org.jetbrains.kotlin.psi.KtBlockExpression
import org.jetbrains.kotlin.psi.KtNamedFunction
import kotlin.random.Random

class RunLetTransformation : MetamorphicTransformation() {
    override fun transform(
        mutationPoint: PsiElement,
        scope: HashMap<Variable, MutableList<String>>,
        expected: Boolean
    ) {
        val block = file.getAllPSIChildrenOfType<KtBlockExpression>().randomOrNull() ?: return
        val isFunctionBody = (block.parent as? KtNamedFunction) != null
        val runOrLet = (if (isFunctionBody) "=" else "") + (if (Random.nextBoolean()) "1.run" else "1.let")
        println("RunLet block: ${block.text.removePrefix("{").removeSuffix("}")}")
        println("RunLetTransformation: $runOrLet {${block.text.removePrefix("{").removeSuffix("}")}}")
        val newBlock =
            Factory.psiFactory.tryToCreateExpression("$runOrLet {${block.text.removePrefix("{").removeSuffix("}")}}")
                ?: return
        block.replaceThis(newBlock)
    }

    private fun doesntContainsMutationPoint(it: PsiElement, mutationPoint: PsiElement): Boolean {
        TODO("Not yet implemented")
    }
}