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
    ) {
        if (Random.getTrue(40)) {
            val tmp = tmpMutationPoint
            AddCasts().transform(tmp, scope, false)
            val tryBlock = tmp.children.joinToString("\n") { it.text }.split("\n").drop(1).joinToString("\n")
            addAfterMutationPoint(mutationPoint) { it.createExpression("try{\n$tryBlock\n}catch(e: ClassCastException){}") }
            return
        }
        if (Random.getTrue(30)) {
            addAfterMutationPoint(mutationPoint) { it.createExpression("try{\nTODO(\"Not yet implemented\")}catch(e: NotImplementedError){}") }
            return
        }
        val ktFile = file as KtFile
        rig = RandomInstancesGenerator(ktFile, ctx!!)

        if (expected) {
            val check = if (Random.nextBoolean()) "check" else "require"
            val checkValue =
                AddIf().synthesisPredicate(scope, Random.nextBoolean(), Random.nextInt(1, maxOf(scope.size, 2)))
            addAfterMutationPoint(mutationPoint) {
                it.createExpression("try{$check($checkValue)}catch(e: IllegalStateException){}\ncatch(e: IllegalArgumentException){}")
            }
        } else {
            val mutations = removeMutation(AddTryExpression::class)
            val tmp = tmpMutationPoint
            executeMutations(tmp, scope, true, mutations.toMutableList().apply { })
            val tryBlock = tmp.children.joinToString("\n") { it.text }.split("\n").drop(1).joinToString("\n")
            addAfterMutationPoint(mutationPoint) {
                it.createExpression("try{$tryBlock}catch(e: Exception){}")
            }
        }
    }

    private lateinit var rig: RandomInstancesGenerator
}