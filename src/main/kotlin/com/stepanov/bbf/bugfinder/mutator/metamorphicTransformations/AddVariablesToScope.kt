package com.stepanov.bbf.bugfinder.mutator.metamorphicTransformations

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.RandomInstancesGenerator
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.typeGenerators.RandomTypeGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.util.getRandomVariableName
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtProperty
import kotlin.random.Random

class AddVariablesToScope : MetamorphicTransformation() {
    private lateinit var rig: RandomInstancesGenerator
    override fun transform(
        mutationPoint: PsiElement,
        scope: HashMap<Variable, MutableList<String>>,
        expected: Boolean
    ) {
        val ktFile = file as KtFile
        RandomTypeGenerator.setFileAndContext(ktFile, ctx!!)
        rig = RandomInstancesGenerator(ktFile, ctx!!)
        val variable = generateVariable() ?: return
        val value = (variable.psiElement as KtProperty).initializer?.text ?: return
        scope[variable] = mutableListOf(value)
        addAfterMutationPoint(mutationPoint, variable.psiElement)
    }

    private fun generateVariable(): Variable? {
        val randomType = RandomTypeGenerator.generateRandomTypeWithCtx() ?: return null
        val expr = rig.generateValueOfTypeAsExpression(randomType) ?: return null
        val property =
            Factory.psiFactory.createProperty(Random.getRandomVariableName(), randomType.toString(), true, expr.text)
        return Variable(property.name!!, randomType, property)
    }
}