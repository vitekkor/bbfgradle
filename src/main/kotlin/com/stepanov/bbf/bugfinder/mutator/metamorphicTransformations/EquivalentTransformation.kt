package com.stepanov.bbf.bugfinder.mutator.metamorphicTransformations

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.util.addAfterThisWithWhitespace
import com.stepanov.bbf.bugfinder.util.addAtTheEnd
import com.stepanov.bbf.bugfinder.util.getRandomVariableName
import com.stepanov.bbf.bugfinder.util.getTrue
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.psi.KtTypeReference
import kotlin.random.Random

class EquivalentTransformation : MetamorphicTransformation() {
    override fun transform(
        mutationPoint: PsiElement,
        scope: HashMap<Variable, MutableList<String>>,
        expected: Boolean
    ) {
        if (Random.getTrue(65))
            typeAlias()
        if (Random.getTrue(60))
            inlineClass()
        if (Random.getTrue(50))
            wrappedClass()
    }

    private fun typeAlias() {
        val typeReferences = file.getAllPSIChildrenOfType<KtTypeReference>()
        val random = typeReferences.randomOrNull() ?: return
        val typeAliasName = Random.getRandomVariableName(5)
        val typeAlias = Factory.psiFactory.createTypeAlias(typeAliasName, listOf(), random.text)
        file.addAtTheEnd(typeAlias)
        val typeAliasReference = Factory.psiFactory.createType(typeAliasName)
        typeReferences.forEach { it.replace(typeAliasReference) }
    }

    private fun inlineClass() {
        val typeReferences = file.getAllPSIChildrenOfType<KtTypeReference>()
        val random = typeReferences.randomOrNull() ?: return
        val inlineClassName = Random.getRandomVariableName(5)
        val inlineClass = Factory.psiFactory.createClass("value class $inlineClassName(val inlineType: ${random.text})")

        val inlineAnnotation = Factory.psiFactory.createAnnotationEntry("@JvmInline")
        inlineAnnotation.addAfterThisWithWhitespace(inlineClass, "\n")

        file.addAtTheEnd(inlineAnnotation)

        /*val typeAliasReference = Factory.psiFactory.createType(inlineClassName)
        typeReferences.forEach { it.replace(typeAliasReference) }*/

        // TODO replace
    }

    private fun wrappedClass() {
    }
}