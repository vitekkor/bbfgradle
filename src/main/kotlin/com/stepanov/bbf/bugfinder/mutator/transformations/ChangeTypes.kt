package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.mutator.transformations.Factory.psiFactory
import com.stepanov.bbf.bugfinder.util.filterDuplicatesBy
import com.stepanov.bbf.bugfinder.util.getNameWithoutError
import com.stepanov.bbf.bugfinder.util.getTrue
import com.stepanov.bbf.bugfinder.util.supertypesWithoutAny
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.resolve.bindingContextUtil.getAbbreviatedTypeOrType
import kotlin.random.Random

object ChangeTypes : Transformation() {

    override fun transform() {
        val ctx = PSICreator.analyze(file, project) ?: return
        repeat(20) {
            val (typeRef, type) = file.getAllPSIChildrenOfType<KtTypeReference>()
                .filterDuplicatesBy { it.text }
                .map { it to it.getAbbreviatedTypeOrType(ctx) }
                .filter { it.second != null }
                .randomOrNull() ?: return
            val replacement =
                type!!.supertypesWithoutAny()
                    .randomOrNull()
                    ?.let { psiFactory.createTypeIfPossible(it.getNameWithoutError()) }
                    ?: return
            checker.replaceNodeIfPossible(typeRef, replacement)
        }
    }
}