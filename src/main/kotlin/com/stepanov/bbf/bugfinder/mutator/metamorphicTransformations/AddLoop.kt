package com.stepanov.bbf.bugfinder.mutator.metamorphicTransformations

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.RandomInstancesGenerator
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.typeGenerators.RandomTypeGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.StdLibraryGenerator
import com.stepanov.bbf.bugfinder.util.*
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.replace
import org.jetbrains.kotlin.types.typeUtil.asTypeProjection
import kotlin.random.Random

class AddLoop : MetamorphicTransformation() {
    override fun transform(
        mutationPoint: PsiElement,
        scope: HashMap<Variable, MutableList<String>>,
        expected: Boolean
    ): String {
        val rig = RandomInstancesGenerator(file as KtFile, ctx!!)
        RandomTypeGenerator.setFileAndContext(file as KtFile, ctx!!)
        val body = if (!expected) {
            file.getAllPSIChildrenOfType<KtExpression>().filter { it.getType(ctx!!) != null }.randomOrNull()?.text
                ?: return ""
        } else {
            removeMutation(AddLoop::class)
            synthesisIfBody(mutationPoint, scope, expected)
        }
        val forExpr = generateForExpression(scope.keys, rig, body)
        return forExpr?.text ?: ""
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
                val left = randomVar.psiElement
                val rightFromScope =
                    variablesFromScopeToIterate
                        .find { it.type.name == randomVar.type.name && it.psiElement.text != randomVar.psiElement.text }
                val right =
                    if (rightFromScope != null && Random.getTrue(50))
                        rightFromScope.psiElement
                    else rig.generateValueOfTypeAsExpression(randomVar.type)!!
                Factory.psiFactory.createExpression("${left.text}..${right.text}")
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
                    .map { it.name.asString() }
                    .let { it.contains("Iterable") || it.contains("ClosedRange") }
            }
            .filterDuplicatesBy { it.name }


    private val typesToIterate = listOf("Byte", "UByte", "Char", "Int", "UInt", "Long", "ULong", "Short", "UShort")
}