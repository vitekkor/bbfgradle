package com.stepanov.bbf.bugfinder.mutator.metamorphicTransformations

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.util.getTrue
import kotlin.random.Random

class AddTryExpression : MetamorphicTransformation() {
    override fun transform(
        mutationPoint: PsiElement,
        scope: HashMap<Variable, MutableList<String>>,
        expected: Boolean
    ): String {
        if (Random.getTrue(40)) {
            val casts = AddCasts().transform(mutationPoint, scope, false)
            return "try{\n$casts\n}catch(e: ClassCastException){}"
        }
        val variable = scope.keys.randomOrNull()
        TODO("Not yet implemented")
    }
}