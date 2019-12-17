package com.stepanov.bbf.bugfinder.mutator.transformations

import org.jetbrains.kotlin.psi.KtTypeParameter
import com.stepanov.bbf.bugfinder.executor.MutationChecker
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType

//TODO maybe add inline keyword to func
//TODO maybe add type params to func?
class AddReifiedToType: Transformation() {

    override fun transform() {
        val typeParameters = file.getAllPSIChildrenOfType<KtTypeParameter>()
        typeParameters.forEach {
            val newTypeModifier = psiFactory.createTypeParameter("reified ${it.text}")
            MutationChecker.replacePSINodeIfPossible(file, it, newTypeModifier)
        }
    }

}