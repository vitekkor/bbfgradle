package com.stepanov.bbf.bugfinder.mutator.metamorphicTransformations

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.mutator.MetamorphicMutator

class AddCasts : MetamorphicTransformation() {
    override fun transform(mutationPoint: PsiElement, scope: HashMap<MetamorphicMutator.Variable, MutableList<String>>, expected: Boolean): String {
        TODO("Not yet implemented")
    }
}