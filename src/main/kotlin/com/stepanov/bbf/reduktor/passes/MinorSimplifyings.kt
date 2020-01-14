package com.stepanov.bbf.reduktor.passes

import com.stepanov.bbf.reduktor.executor.CompilerTestChecker
import com.stepanov.bbf.reduktor.util.generateDefValuesAsString
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.getValueParameters
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.calls.callUtil.getType

//
class MinorSimplifyings(
    private val file: KtFile, private val checker: CompilerTestChecker,
    private val ctx: BindingContext
) {

    fun transform() {
        //Replace variable on constant if we can get type
        try {
            file.getAllPSIChildrenOfType<KtExpression>()
                    //TODO DO SMTH WITH THAT
                .filter { it !is KtConstantExpression &&
                        (it is KtReferenceExpression || it is KtOperationExpression || it is KtCallExpression) }
                .forEach {
                    it.getType(ctx)?.let { type ->
                        val defValue =
                            customStructures.find { it.second == "$type" }?.let { getDefValueForCustomClass(it.first) }
                                ?: generateDefValuesAsString("$type")
                        val exp = KtPsiFactory(file.project).createExpression(defValue)
                        checker.replaceNodeIfPossible(file, it, exp)
                    }
                }
        } catch (e: Exception) {
            return
        }
    }

    private fun getDefValueForCustomClass(classOrObject: KtClassOrObject): String? {
        if (classOrObject is KtObjectDeclaration) return classOrObject.name
        if (classOrObject is KtClass) {
            val res = StringBuilder()
            val constructor = classOrObject.primaryConstructor ?: return "${classOrObject.name}()"
            res.append("${classOrObject.name}(")
            for (par in constructor.valueParameters) {
                val type = par.typeReference ?: return null
                val defValue =
                    customStructures.find { it.second == type.text }?.let { getDefValueForCustomClass(it.first) }
                        ?: generateDefValuesAsString(type.text)
                res.append("${defValue},")
            }
            res.deleteCharAt(res.length - 1)
            res.append(")")
            return res.toString()
        }
        return null
    }

    val customStructures = file.getAllPSIChildrenOfType<KtClassOrObject>().map { it to it.name }

}