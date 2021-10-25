package com.stepanov.bbf.reduktor.passes

import com.stepanov.bbf.bugfinder.util.generateDefValuesAsString
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.util.getAllChildren
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.resolve.calls.callUtil.getType

//
class MinorSimplifyings : SimplificationPass() {

    private val ctx = PSICreator.analyze(file)
    private val customStructures = file.getAllPSIChildrenOfType<KtClassOrObject>().map { it to it.name }

    override fun simplify() {
        ctx ?: return
        //Replace variable on constant if we can get type
        try {
            file.getAllPSIChildrenOfType<KtExpression>()
                //TODO DO SMTH WITH THAT
                .filter {
                    it !is KtConstantExpression &&
                            (it is KtReferenceExpression || it is KtOperationExpression || it is KtCallExpression ||
                                    it is KtDotQualifiedExpression)
                }
                .forEach {
                    if (!file.getAllChildren().contains(it)) return@forEach
                    it.getType(ctx)?.let { type ->
                        val defValue =
                            customStructures.find { it.second == "$type" }?.let { getDefValueForCustomClass(it.first) }
                                ?: generateDefValuesAsString("$type")
                        if (defValue.isEmpty()) return@forEach
                        val exp = KtPsiFactory(file.project).createExpression(defValue)
                        if (it.text.length < exp.text.length) return@forEach
                        checker.replaceNodeIfPossible(it, exp)
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
}


