package com.stepanov.bbf.bugfinder.mutator.metamorphicTransformations

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.RandomInstancesGenerator
import com.stepanov.bbf.bugfinder.util.getTrue
import org.jetbrains.kotlin.psi.KtFile
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
        if (Random.getTrue(30)) {
            return "try{\nTODO(\"Not yet implemented\"}catch(e: NotImplementedError){}"
        }
        val ktFile = file as KtFile
        rig = RandomInstancesGenerator(ktFile, ctx!!)

        return if (expected) {
            val check = if (Random.nextBoolean()) "check" else "require"
            val checkValue = synthesisPredicate(scope, Random.nextBoolean(), Random.nextInt(1, maxOf(scope.size, 2)))
            "try{$check($checkValue)}catch(e: Exception){}"
        } else {
            removeMutation(AddTryExpression::class)
            val additionalBody = synthesisIfBody(mutationPoint, scope, true)
            "try{$additionalBody}catch(e: Exception){}"
        }
    }

    private lateinit var rig: RandomInstancesGenerator
}