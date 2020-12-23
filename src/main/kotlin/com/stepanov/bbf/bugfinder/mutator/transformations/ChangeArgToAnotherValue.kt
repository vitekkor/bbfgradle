package com.stepanov.bbf.bugfinder.mutator.transformations

import org.jetbrains.kotlin.psi.KtCallExpression
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.psiUtil.getCallNameExpression
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory.psiFactory as psiFactory
import com.stepanov.bbf.bugfinder.util.generateDefValuesAsString
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType

class ChangeArgToAnotherValue : Transformation() {

    //TODO For user classes
    override fun transform() {
        file.getAllPSIChildrenOfType<KtNamedFunction>().forEach { f ->
            getAllInvocations(f).forEach { inv ->
                inv.valueArguments.forEachIndexed { argInd, arg ->
                    if (argInd < f.valueParameters.size) {
                        val type = f.valueParameters[argInd].typeReference?.text ?: return@forEachIndexed
                        val newRandomValue = generateDefValuesAsString(type)
                        log.debug("generated value for type $type is $newRandomValue")
                        if (newRandomValue.trim().isEmpty()) return@forEachIndexed
                        val newArg = psiFactory.createArgument(newRandomValue)
                        checker.replacePSINodeIfPossible(arg, newArg)
                    }
                }
            }
        }
    }

    private fun getAllInvocations(func: KtNamedFunction): List<KtCallExpression> {
        val res = mutableListOf<KtCallExpression>()
        file.getAllPSIChildrenOfType<KtCallExpression>()
                .filter {
                    it.getCallNameExpression()?.getReferencedName() == func.name &&
                            it.valueArguments.size == func.valueParameters.size
                }
                .forEach { res.add(it) }
        return res
    }
}