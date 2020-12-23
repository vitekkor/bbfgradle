package com.stepanov.bbf.bugfinder.mutator.transformations

import org.jetbrains.kotlin.psi.KtTypeParameter

import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory.psiFactory as psiFactory

//TODO maybe add inline keyword to func
//TODO maybe add type params to func?
class AddReifiedToType: Transformation() {

    override fun transform() {
        val typeParameters = file.getAllPSIChildrenOfType<KtTypeParameter>()
        typeParameters.forEach {
            val newTypeModifier = psiFactory.createTypeParameter("reified ${it.text}")
            checker.replacePSINodeIfPossible(it, newTypeModifier)
        }
    }

}