package com.stepanov.bbf.bugfinder.mutator.metamorphicTransformations

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.util.*
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.psi.KtBinaryExpression
import org.jetbrains.kotlin.psi.KtNameReferenceExpression
import org.jetbrains.kotlin.psi.KtProperty
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
        typeReferences.forEach { if (it.text == random.text) it.replace(typeAliasReference) }
    }

    private fun inlineClass() {
        val typeReferences = file.getAllPSIChildrenOfType<KtTypeReference>()
        val random = typeReferences.randomOrNull() ?: return
        val inlineClassName = "Inline_${Random.getRandomVariableName(5)}"
        wrapOrInlineClass(inlineClassName, random, true)
    }

    private fun wrappedClass() {
        val typeReferences = file.getAllPSIChildrenOfType<KtTypeReference>()
        val random = typeReferences.randomOrNull() ?: return
        val wrappedClassName = "Wrapped_${Random.getRandomVariableName(5)}"
        wrapOrInlineClass(wrappedClassName, random, false)
    }

    private fun wrapOrInlineClass(className: String, wrappedInlinedType: KtTypeReference, isInlineClass: Boolean) {
        val valueKeyWord = if (isInlineClass) "value" else ""
        val propertyName = if (isInlineClass) "inlinedProperty" else "wrappedProperty"

        val resultClass = Factory.psiFactory.createClass(
            "$valueKeyWord class $className(val $propertyName: ${wrappedInlinedType.text}){ override fun toString(): String = $propertyName.toString() }"
        )

        if (isInlineClass) {
            val inlineAnnotation = Factory.psiFactory.createAnnotationEntry("@JvmInline")
            inlineAnnotation.addAfterThisWithWhitespace(resultClass, "\n")
            file.addAtTheEnd(inlineAnnotation)
        } else
            file.addAtTheEnd(resultClass)

        val prop =
            file.getAllPSIChildrenOfType<KtProperty>().filter { it.getType(ctx!!)?.name == wrappedInlinedType.text }
        prop.forEach { property ->
            if (property.initializer != null) {
                val newProperty =
                    Factory.psiFactory.createProperty("${property.valOrVarKeyword.text} ${property.name}: $className = $className(${property.initializer!!.text})")
                property.replaceThis(newProperty)
            } else { // lateinit
                val newProperty =
                    Factory.psiFactory.createProperty("${property.valOrVarKeyword.text} ${property.name}: $className")
                property.replaceThis(newProperty)
            }
        }
        val ktNameReferenceExpression = file.getAllPSIChildrenOfType<KtNameReferenceExpression>()
        prop.forEach { property ->
            ktNameReferenceExpression.filter { it.text.contains(property.name!!) }.forEach { replacement ->
                val binaryExpression = (replacement.parent as? KtBinaryExpression)
                if (binaryExpression != null) {
                    val newExpression =
                        Factory.psiFactory.createExpression("${binaryExpression.left?.text} = $className(${binaryExpression.right?.text?.split("\n")?.first()})")
                    binaryExpression.replace(newExpression)
                } else {
                    replacement.replaceThis(Factory.psiFactory.createExpression("${replacement.text}.$propertyName"))
                }
            }
        }
    }
}