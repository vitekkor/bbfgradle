package com.stepanov.bbf.bugfinder.mutator.metamorphicTransformations

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.RandomInstancesGenerator
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.typeGenerators.RandomTypeGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.util.addAfterThisWithWhitespace
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
    ) {
        val ktFile = file as KtFile
        RandomTypeGenerator.setFileAndContext(ktFile, ctx!!)
        rig = RandomInstancesGenerator(ktFile, ctx!!)
        val variable = scope.keys.randomOrNull() ?: generateVariable() ?: return

        val functionName = variable.type.memberScope.getFunctionNames().randomOrNull() ?: return
        val function =
            variable.type.memberScope.getContributedFunctions(functionName, KotlinLookupLocation(file as KtFile))
                .randomOrNull() ?: return
        val funCall = rig.generateFunctionCall(function) ?: return
        if (expected) {
            if (scope[variable] != null) {
                val backup =
                    Factory.psiFactory.createProperty(
                        "backup_var_${variable.name}",
                        variable.type.toString(),
                        true,
                        variable.name
                    )
                if (variable.isVar) {
                    mutationPoint.addAfterThisWithWhitespace(
                        Factory.psiFactory.createBlock("kotlin.run {\n${backup.text}\n${variable.name}?.${funCall.text}\n${variable.name} = ${backup.name}}"),
                        "\n"
                    )
                } else {
                    mutationPoint.addAfterThisWithWhitespace(
                        Factory.psiFactory.createBlock("kotlin.run {\n${backup.text}\n${backup.name}?.${funCall.text}}"),
                        "\n"
                    )
                }
            } else {
                mutationPoint.addAfterThisWithWhitespace(
                    Factory.psiFactory.createBlock("kotlin.run {\n${variable.psiElement.text}\n${variable.name}.${funCall.text}}"),
                    "\n"
                )
            }
        } else {
            if (scope[variable] != null)
                mutationPoint.addAfterThisWithWhitespace(
                    Factory.psiFactory.createBlock("kotlin.run {\n${variable.name}?.${funCall.text}}"),
                    "\n"
                )
            else
                mutationPoint.addAfterThisWithWhitespace(
                    Factory.psiFactory.createBlock("kotlin.run {\n${variable.psiElement.text}\n${variable.name}?.${funCall.text}}"),
                    "\n"
                )
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