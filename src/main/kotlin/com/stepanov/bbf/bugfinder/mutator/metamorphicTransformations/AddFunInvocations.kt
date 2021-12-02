package com.stepanov.bbf.bugfinder.mutator.metamorphicTransformations

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.RandomInstancesGenerator
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.util.getAllChildren
import org.jetbrains.kotlin.cfg.getDeclarationDescriptorIncludingConstructors
import org.jetbrains.kotlin.cfg.getElementParentDeclaration
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.psi.KtCallExpression
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtNamedFunction
import kotlin.random.Random

class AddFunInvocations : MetamorphicTransformation() {
    private lateinit var rig: RandomInstancesGenerator
    private lateinit var functions: List<KtNamedFunction>
    override fun transform(
        mutationPoint: PsiElement,
        scope: HashMap<Variable, MutableList<String>>,
        expected: Boolean
    ): String {
        val ktFile = file as KtFile
        rig = RandomInstancesGenerator(ktFile, ctx!!)
        functions =
            ktFile.getAllPSIChildrenOfType { !it.text.contains("fun main(.*)".toRegex()) }
        if (!expected) {
            val func = functions.randomOrNull() ?: return ""
            if (func.getElementParentDeclaration() != null) {
                val (klass, type) = rig.generateRandomInstanceOfClass(func.getElementParentDeclaration() as KtClassOrObject)!!
                val v = scope.keys.find { it.type == type }?.name ?: Random.getRandomVariableNameNotIn(scope.keys)
                return if (scope.keys.any { it.name == v }) {
                    "$v." + rig.generateFunctionCall(functions[5].getDeclarationDescriptorIncludingConstructors(ctx!!) as FunctionDescriptor)?.text
                } else {
                    "var $v = ${klass!!.text}\n$v." + rig.generateFunctionCall(
                        func.getDeclarationDescriptorIncludingConstructors(
                            ctx!!
                        ) as FunctionDescriptor
                    )?.text
                }
            } else {
                return rig.generateFunctionCall(func.getDeclarationDescriptorIncludingConstructors(ctx!!) as FunctionDescriptor)?.text
                    ?: ""
            }
        } else {
            val parent = findMutationPointParentFun(mutationPoint) ?: return ""
            val correct = functions.filter {
                it.isTopLevel &&
                        !it.getAllChildren().contains(mutationPoint) &&
                        !checkRecursiveCall(it.getAllChildren(), mutationPoint, parent)
            }
            var func = correct.randomOrNull() ?: return ""
            var i = 0
            while (func.getElementParentDeclaration() != null && ++i < 5) {
                func = correct.random()
            }
            if (func.getElementParentDeclaration() != null) return ""
            return rig.generateFunctionCall(func.getDeclarationDescriptorIncludingConstructors(ctx!!) as FunctionDescriptor)?.text
                ?: ""
        }
    }

    private fun checkRecursiveCall(
        children: Collection<PsiElement>,
        mutationPoint: PsiElement,
        function: KtNamedFunction
    ): Boolean {
        for (child in children) {
            if (child == mutationPoint) return true
            if (function.name?.let { child.text.contains(it) } == true) return true
            if (child is KtCallExpression) {
                val recursiveCall = functions.find { child.text.contains(it.name!!) }
                if (recursiveCall != null && checkRecursiveCall(
                        recursiveCall.getAllChildren(),
                        mutationPoint,
                        function
                    )
                ) return true
            }
            if (checkRecursiveCall(child.getAllChildren(), mutationPoint, function)) return true
        }
        return false
    }

    private fun findMutationPointParentFun(mutationPoint: PsiElement): KtNamedFunction? {
        var parent: PsiElement? = mutationPoint.parent
        while (parent != null && parent !is KtNamedFunction) {
            parent = parent.parent
        }
        return parent as? KtNamedFunction
    }
}