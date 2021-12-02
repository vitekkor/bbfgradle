package com.stepanov.bbf.bugfinder.mutator.metamorphicTransformations

import com.intellij.psi.PsiElement

class AddCasts : MetamorphicTransformation() {
    override fun transform(mutationPoint: PsiElement, scope: HashMap<Variable, MutableList<String>>, expected: Boolean): String {
        return ""
    }
}