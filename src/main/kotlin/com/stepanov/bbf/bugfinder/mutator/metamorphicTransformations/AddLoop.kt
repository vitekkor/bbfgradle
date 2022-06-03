package com.stepanov.bbf.bugfinder.mutator.metamorphicTransformations

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.RandomInstancesGenerator
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.typeGenerators.RandomTypeGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.StdLibraryGenerator
import com.stepanov.bbf.bugfinder.util.*
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.replace
import org.jetbrains.kotlin.types.typeUtil.asTypeProjection
import kotlin.random.Random

class AddLoop : MetamorphicTransformation() {
    override fun transform(
        mutationPoint: PsiElement,
        scope: HashMap<Variable, MutableList<String>>,
        expected: Boolean
    ) {
        val rig = RandomInstancesGenerator(file as KtFile, ctx!!)
        RandomTypeGenerator.setFileAndContext(file as KtFile, ctx!!)
        val body = run {
            val mutations = removeMutation(AddLoop::class)
            val tmp = tmpMutationPoint
            executeMutations(tmp, scope, expected, mutations)
            tmp.children.joinToString("\n") { it.text }.split("\n").drop(1).joinToString("\n")
        }
        println("Body: $body")
        val forExpr: KtExpression = if (Random.getTrue(33)) { // normal
            generateForExpression(scope.keys, rig, body) ?: return
        } else if (Random.getTrue(33)) { // one iteration
            val value = Random.nextInt()
            val forExpression = "for (${Random.getRandomVariableName(5)} in $value..$value) {\n$body\n}"
            println("forExpression: $forExpression")
            Factory.psiFactory.createExpressionIfPossible(forExpression) ?: return
        } else { // endless loop with break
            val forExpression =
                "for (${Random.getRandomVariableName(5)} in generateSequence(0) { it }) {\n$body\nbreak\n}"
            println("forExpression: $forExpression")
            Factory.psiFactory.createExpressionIfPossible(forExpression) ?: return
        }
        addAfterMutationPoint(mutationPoint, forExpr)
    }

    private fun generateForExpression(
        scope: Set<Variable>,
        rig: RandomInstancesGenerator,
        body: String
    ): KtExpression? {
        val containerFromScopeToIterate =
            scope.filter { it.type.isIterable() || it.type.getNameWithoutError().contains("Range") }
        val variablesFromScopeToIterate =
            scope.filter { it.type.getNameWithoutError() in typesToIterate }
        val loopRange =
            if (containerFromScopeToIterate.isNotEmpty() && Random.getTrue(50)) {
                containerFromScopeToIterate.random().psiElement
            } else if (variablesFromScopeToIterate.isNotEmpty() && Random.getTrue(20)) {
                val randomVar = variablesFromScopeToIterate.random()
                val left = randomVar.psiElement as? KtProperty ?: return null
                val rightFromScope =
                    variablesFromScopeToIterate
                        .find { it.type.name == randomVar.type.name && it.psiElement.text != randomVar.psiElement.text }
                val right =
                    if (rightFromScope != null && Random.getTrue(50))
                        rightFromScope.psiElement
                    else rig.generateValueOfTypeAsExpression(randomVar.type)!!
                Factory.psiFactory.createExpressionIfPossible("${left.name}..${right.text}")
            } else if (Random.getTrue(20)) { // downTo for
                val typeToIterate = typesToIterate.random()
                val type = RandomTypeGenerator.generateType(typeToIterate) ?: return null
                val left = rig.generateValueOfType(type)
                val right = rig.generateValueOfType(type)
                Factory.psiFactory.createExpressionIfPossible("$left downTo $right")
            } else {
                var resExpr: KtExpression? = null
                for (i in 0 until 10) {
                    val randomClassToIterate = getRandomClassToIterate()
                    resExpr = rig.generateValueOfTypeAsExpression(randomClassToIterate)
                    if (resExpr != null) break
                }
                resExpr
            }
        if (loopRange == null) return null
        val loopParameter = Random.getRandomVariableName(3)
        val forExpression = "for ($loopParameter in ${loopRange.text}) { \n$body\n}"
        return try {
            Factory.psiFactory.createExpression(forExpression)
        } catch (e: Exception) {
            null
        } catch (e: Error) {
            null
        }
    }

    private fun getRandomClassToIterate(): KotlinType {
        val randomClass = randomClassesToIterate.random()
        //Substitute type parameters
        val realTypeParams = randomClass.typeConstructor.parameters.map {
            RandomTypeGenerator.generateRandomTypeWithCtx(it.upperBounds.firstOrNull()) ?: DefaultKotlinTypes.intType
        }
        return randomClass.defaultType.replace(realTypeParams.map { it.asTypeProjection() })
    }

    private val randomClassesToIterate =
        StdLibraryGenerator.klasses
            .filter {
                it.getAllSuperClassifiersWithoutAnyAndItself()
                .map { it.name.asString() }.contains("Iterable")
            }
            .filterDuplicatesBy { it.name }


    private val typesToIterate = listOf("Byte", "UByte", "Char", "Int", "UInt", "Long", "ULong", "Short", "UShort")
}