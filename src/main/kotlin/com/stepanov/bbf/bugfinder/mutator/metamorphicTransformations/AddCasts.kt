package com.stepanov.bbf.bugfinder.mutator.metamorphicTransformations

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.RandomInstancesGenerator
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.typeGenerators.RandomTypeGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory.tryToCreateExpression
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.StdLibraryGenerator
import com.stepanov.bbf.bugfinder.util.*
import org.jetbrains.kotlin.cfg.getDeclarationDescriptorIncludingConstructors
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.resolve.descriptorUtil.module
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.typeUtil.supertypes
import kotlin.random.Random

class AddCasts : MetamorphicTransformation() {
    private lateinit var rig: RandomInstancesGenerator
    override fun transform(
        mutationPoint: PsiElement,
        scope: HashMap<Variable, MutableList<String>>,
        expected: Boolean
    ) {
        val ktFile = file as KtFile
        RandomTypeGenerator.setFileAndContext(ktFile, ctx!!)
        rig = RandomInstancesGenerator(ktFile, ctx!!)
        val variable = scope.keys.randomOrNull() ?: generateVariable() ?: return
        val currentModule =
            ktFile.getMainFunc()?.getDeclarationDescriptorIncludingConstructors(ctx!!)?.module ?: return

        val userClassesDescriptors =
            StdLibraryGenerator.getUserClassesDescriptorsFromProject(project, currentModule)

        val randomTypeToCast =
            if (Random.getTrue(10)) {
                val randomType = RandomTypeGenerator.generateRandomTypeWithCtx()
                randomType?.constructor?.declarationDescriptor as? ClassDescriptor
            } else {
                chooseRandomTypeToCast(variable.type, userClassesDescriptors)
            } ?: return
        if (expected) {
            val supertype = variable.type.supertypes().randomOrNull() ?: return
            val values = scope[variable]
            val updated = variable.copy(type = supertype)
            values?.let { scope[updated] = it; scope.remove(variable) }
            addAfterMutationPoint(mutationPoint) { it.createExpression("$variable as ${supertype.name}") }
        } else {
            addAfterMutationPoint(mutationPoint) {
                it.tryToCreateExpression(tryToCast(variable.psiElement as KtProperty, variable.type, randomTypeToCast))
            }
        }
    }

    private fun generateVariable(): Variable? {
        val randomType = RandomTypeGenerator.generateRandomTypeWithCtx() ?: return null
        val expr = rig.generateValueOfTypeAsExpression(randomType) ?: return null
        val property =
            Factory.psiFactory.createProperty(Random.getRandomVariableName(), randomType.toString(), true, expr.text)
        return Variable(property.name!!, randomType, property)
    }

    private fun chooseRandomTypeToCast(
        typeOfRandomExpression: KotlinType,
        userClassesDescriptors: List<ClassDescriptor>
    ): ClassDescriptor? {
        val descriptorOfRandomType =
            typeOfRandomExpression.constructor.declarationDescriptor as? ClassDescriptor ?: return null
        val isUserType = userClassesDescriptors.any { it.name == descriptorOfRandomType.name }
        val descendants =
            if (isUserType) {
                userClassesDescriptors.filter { userClassDescriptor ->
                    userClassDescriptor.getAllSuperClassifiersWithoutAnyAndItself()
                        .any { it.name == descriptorOfRandomType.name }
                }
            } else {
                StdLibraryGenerator.getAllDescendantsFromStdLibrary(descriptorOfRandomType)
            }
        val supertypes = typeOfRandomExpression
            .supertypesWithoutAny()
            .toList()
            .mapNotNull { it.constructor.declarationDescriptor as? ClassDescriptor }
        val supertypesNames = supertypes.map { it.name }
        val listWithPotentialSiblings =
            if (isUserType) {
                userClassesDescriptors
            } else {
                StdLibraryGenerator.klasses
            }
        val siblings = listWithPotentialSiblings.filter {
            val superClassClassifiersNames =
                it.getAllSuperClassifiersWithoutAnyAndItself()
                    .toList()
                    .map { it.name }
            superClassClassifiersNames.intersect(supertypesNames).isNotEmpty()
        }
        val compatibleDescendants = (descendants + supertypes + siblings)
            .filter { it.visibility.isPublicAPI }
        return compatibleDescendants.randomOrNull()
    }

    private fun tryToCast(
        expression: KtProperty,
        typeOfExpression: KotlinType,
        castToTypeDescriptor: ClassDescriptor
    ): String {
        val typeArgsOfExpressionAsString = throwTypeParams(typeOfExpression, castToTypeDescriptor)
        val castExpressionAsString = "${castToTypeDescriptor.name.asString()}$typeArgsOfExpressionAsString"
        val newExpression =
            try {
                if (Random.getTrue(5)) {
                    Factory.psiFactory.createExpressionIfPossible("${expression.name} as T")
                } else {
                    Factory.psiFactory.createExpressionIfPossible("${expression.name} as $castExpressionAsString")
                }
            } catch (e: Exception) {
                null
            }
        if (newExpression != null) return newExpression.text
        val newExpressionInBrackets =
            try {
                Factory.psiFactory.createExpressionIfPossible("(${expression.name}) as $castExpressionAsString")
            } catch (e: Exception) {
                null
            } ?: return ""
        return newExpressionInBrackets.text
    }

    private fun throwTypeParams(fromType: KotlinType, toType: ClassDescriptor): String {
        val toTypeTypeParams = toType.typeConstructor.parameters
        if (toTypeTypeParams.isEmpty()) return ""
        if (fromType.arguments.size == toTypeTypeParams.size) return fromType.arguments.joinToString(
            prefix = "<",
            postfix = ">"
        )
        val fromTypeTypeArgs = fromType.arguments
        val fromTypeTypeParams = fromType.constructor.parameters
        val typeParamsToArgs = fromTypeTypeParams.zip(fromTypeTypeArgs).associate { it.first.name to it.second }
        val toTypeTypeArgsAsString = toTypeTypeParams.map { tp ->
            typeParamsToArgs[tp.name] ?: RandomTypeGenerator.generateRandomTypeWithCtx(tp.upperBounds.firstOrNull())
        }.joinToString(prefix = "<", postfix = ">")
        return toTypeTypeArgsAsString
    }
}