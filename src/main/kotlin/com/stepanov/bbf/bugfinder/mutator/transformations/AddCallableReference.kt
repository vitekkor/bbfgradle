package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.util.filterDuplicatesBy
import com.stepanov.bbf.bugfinder.util.replaceThis
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.builtins.isFunctionType
import org.jetbrains.kotlin.psi.KtCallExpression
import org.jetbrains.kotlin.psi.KtReferenceExpression
import org.jetbrains.kotlin.psi.KtValueArgument
import org.jetbrains.kotlin.resolve.calls.callUtil.getType
import kotlin.system.exitProcess

class AddCallableReference : Transformation() {
    override fun transform() {
        var ctx = PSICreator.analyze(file) ?: return
        val callsWithFunTypes =
            file.getAllPSIChildrenOfType<KtCallExpression>()
                .filter { it.valueArguments.any { it.getArgumentExpression()?.getType(ctx)?.isFunctionType == true } }
        val potentialCallableReference =
            checker.project.files.flatMap {
                it.psiFile.getAllPSIChildrenOfType<KtReferenceExpression>()
                    .filter { it !is KtCallExpression }
                    .filterDuplicatesBy { it.text }
            }

        //Deal with single or not single lamda arg
        for (c in callsWithFunTypes) {
            val lambdaArgument = c.lambdaArguments.firstOrNull() ?: continue
            if (c.valueArgumentList == null) {
                val newValueArgumentList = Factory.psiFactory.createCallArguments("(${lambdaArgument.text})")
                lambdaArgument.replaceThis(newValueArgumentList)
            } else {
                val oldValueArgs = c.valueArguments.joinToString(", ") { it.text }
                val newValueArgumentList = Factory.psiFactory.createCallArguments("($oldValueArgs)")
                c.valueArgumentList!!.replaceThis(newValueArgumentList)
                lambdaArgument.delete()
            }
        }
        ctx = PSICreator.analyze(file) ?: return
        for (c in callsWithFunTypes) {
            c.valueArguments.forEach { arg ->
                val argType = arg.getArgumentExpression()?.getType(ctx) ?: return@forEach
                if (!argType.isFunctionType) return@forEach
                for (ref in potentialCallableReference.shuffled()) {
                    val newCallable = Factory.psiFactory.createCallableReferenceExpression("::${ref.text}")
                    print("TRYING TO REPLACE ${arg.text} on ${newCallable?.text} ")
                    if (newCallable != null) {
                        val res = checker.replaceNodeIfPossible(arg, newCallable)
                        println(res)
                    }
                }
            }
        }
    }
}