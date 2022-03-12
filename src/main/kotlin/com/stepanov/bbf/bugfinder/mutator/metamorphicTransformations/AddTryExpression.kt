package com.stepanov.bbf.bugfinder.mutator.metamorphicTransformations

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.RandomInstancesGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.util.addAfterThisWithWhitespace
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
            val tryBlock = tmp.children.joinToString("\n") { it.text }
            mutationPoint.addAfterThisWithWhitespace(
                Factory.psiFactory.createExpression("try{\n$tryBlock\n}catch(e: ClassCastException){}"),
                "\n"
            )
            return
        }
        if (Random.getTrue(30)) {
            mutationPoint.addAfterThisWithWhitespace(
                Factory.psiFactory.createExpression("try{\nTODO(\"Not yet implemented\")}catch(e: NotImplementedError){}"),
                "\n"
            )
            return
        }
        val ktFile = file as KtFile
        rig = RandomInstancesGenerator(ktFile, ctx!!)

        if (expected) {
            val check = if (Random.nextBoolean()) "check" else "require"
            val checkValue = AddIf().synthesisPredicate(scope, Random.nextBoolean(), Random.nextInt(1, maxOf(scope.size, 2)))
            mutationPoint.addAfterThisWithWhitespace(
                Factory.psiFactory.createExpression("try{$check($checkValue)}catch(e: IllegalStateException){}\ncatch(e: IllegalArgumentException){}"),
                "\n"
            )
        } else {
            removeMutation(AddTryExpression::class)
            val tmp = tmpMutationPoint
            executeMutations(tmp, scope, true, defaultMutations.toMutableList().apply {  })
            val tryBlock = tmp.children.joinToString("\n") { it.text }
            mutationPoint.addAfterThisWithWhitespace(
                Factory.psiFactory.createExpression("try{$tryBlock}catch(e: Exception){}"),
                "\n"
            )
        }
    }

    private lateinit var rig: RandomInstancesGenerator
}