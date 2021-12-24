package com.stepanov.bbf.bugfinder.mutator.metamorphicTransformations

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.util.getTrue
import kotlin.random.Random

class AddIf : MetamorphicTransformation() {
    override fun transform(
        mutationPoint: PsiElement,
        scope: HashMap<Variable, MutableList<String>>,
        expected: Boolean
    ): String {
        val exp = Random.nextBoolean()
        val predicate = synthesisPredicate(scope, exp, Random.nextInt(4))
        val thenStatement = synthesisIfBody(mutationPoint, scope, exp)
        if (thenStatement.isEmpty()) {
            return ""
        }
        val elseStatement = if (Random.getTrue(15)) "else {${synthesisIfBody(mutationPoint, scope, !exp)}}" else ""
        return "if ($predicate) {$thenStatement}$elseStatement"
    }
}