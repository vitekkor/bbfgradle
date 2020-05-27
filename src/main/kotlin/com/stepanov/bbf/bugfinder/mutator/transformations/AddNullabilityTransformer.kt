package com.stepanov.bbf.bugfinder.mutator.transformations

import org.jetbrains.kotlin.psi.KtTypeReference
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory.psiFactory as psiFactory

class AddNullabilityTransformer: Transformation() {

    override fun transform() {
        file.getAllPSIChildrenOfType<KtTypeReference>()
                .asSequence()
                .filterNot { it.textContains('?') }
                .map { addNullability(it) }
                .toList()
    }

    fun addNullability(ref: KtTypeReference) {
        val newRef = psiFactory.createTypeIfPossible("(${ref.typeElement?.text})?") ?: return
        checker.replacePSINodeIfPossible(file, ref, newRef)
    }

}