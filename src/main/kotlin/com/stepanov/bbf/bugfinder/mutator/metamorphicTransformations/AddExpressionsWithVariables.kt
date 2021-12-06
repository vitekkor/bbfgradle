package com.stepanov.bbf.bugfinder.mutator.metamorphicTransformations

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.RandomInstancesGenerator
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.typeGenerators.RandomTypeGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.util.getRandomVariableName
import org.jetbrains.kotlin.incremental.KotlinLookupLocation
import org.jetbrains.kotlin.psi.KtFile
import kotlin.random.Random

class AddExpressionsWithVariables : MetamorphicTransformation() {
    private lateinit var rig: RandomInstancesGenerator
    override fun transform(
        mutationPoint: PsiElement,
        scope: HashMap<Variable, MutableList<String>>,
        expected: Boolean
    ): String {
        val ktFile = file as KtFile
        RandomTypeGenerator.setFileAndContext(ktFile, ctx!!)
        rig = RandomInstancesGenerator(ktFile, ctx!!)
        val variable = scope.keys.randomOrNull() ?: generateVariable() ?: return ""

        val functionName = variable.type.memberScope.getFunctionNames().randomOrNull() ?: return ""
        val function =
            variable.type.memberScope.getContributedFunctions(functionName, KotlinLookupLocation(file as KtFile))
                .randomOrNull() ?: return ""
        val funCall = rig.generateFunctionCall(function) ?: return ""
        return if (expected) {
            if (scope[variable] != null) {
                val backup =
                    Factory.psiFactory.createProperty(
                        "backup_var_${variable.name}",
                        variable.type.toString(),
                        true,
                        variable.name
                    )
                if (variable.isVar) {
                    "${backup.text}\n${variable.name}.${funCall.text}\n${variable.name} = ${backup.name}"
                } else {
                    "${backup.text}\n${backup.name}.${funCall.text}"
                }
            } else {
                "${variable.psiElement.text}\n${variable.name}.${funCall.text}"
            }
        } else {
            if (scope[variable] != null)
                "${variable.name}.${funCall.text}"
            else
                "${variable.psiElement.text}\n${variable.name}.${funCall.text}"
        }
    }

    private fun generateVariable(): Variable? {
        val randomType = RandomTypeGenerator.generateRandomTypeWithCtx() ?: return null
        val expr = rig.generateValueOfTypeAsExpression(randomType) ?: return null
        val property =
            Factory.psiFactory.createProperty(Random.getRandomVariableName(), randomType.toString(), true, expr.text)
        return Variable(property.name!!, randomType, property)
    }
}