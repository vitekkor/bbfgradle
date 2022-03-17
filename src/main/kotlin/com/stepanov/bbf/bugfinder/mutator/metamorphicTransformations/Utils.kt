package com.stepanov.bbf.bugfinder.mutator.metamorphicTransformations

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.mutator.metamorphicTransformations.MetamorphicTransformation.Companion.defaultMutations
import com.stepanov.bbf.bugfinder.mutator.metamorphicTransformations.MetamorphicTransformation.Companion.removeMutation
import com.stepanov.bbf.bugfinder.mutator.metamorphicTransformations.MetamorphicTransformation.Companion.restoreMutations
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.util.getRandomVariableName
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.types.KotlinType
import kotlin.random.Random

fun Random.getRandomVariableNameNotIn(scope: Set<Variable>): String? {
    var name = this.getRandomVariableName()
    for (i in 0..10) {
        if (scope.all { it.name != name }) break
        name = this.getRandomVariableName()
    }
    if (scope.any { it.name == name }) return null
    return name
}

val tmpMutationPoint: PsiElement
    get() = Factory.psiFactory.createProperty("val ${Random.getRandomVariableName(5)} = ${Random.nextInt()}")

fun executeMutations(
    mutationPoint: PsiElement,
    scope: HashMap<Variable, MutableList<String>>,
    expected: Boolean,
    mutationList: MutableList<Pair<MetamorphicTransformation, Int>>
) {
    val mutations = mutationList.shuffled()
    removeMutation(AddVariablesToScope::class)
    //defaultMutations.find { it.first::class ==  }?.let { defaultMutations.remove(it) }

    //for (i in 0 until Random.nextInt(1, 3)) {
    for (it in mutations) {
        if (Random.nextInt(0, 100) < it.second) {
            //Update ctx
            MetamorphicTransformation.updateCtx()
            MetamorphicTransformation.ctx ?: continue
            it.first.transform(mutationPoint, scope, expected)
        }
    }
}

data class Variable(val name: String, val type: KotlinType, val psiElement: PsiElement) {
    override fun toString(): String {
        return name
    }

    val isVar: Boolean
        get() = (psiElement as? KtProperty)?.isVar ?: false
}