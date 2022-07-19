package com.stepanov.bbf.bugfinder.mutator.metamorphicTransformations

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiWhiteSpace
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.RandomInstancesGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.util.getNodesBetweenWhitespaces
import com.stepanov.bbf.bugfinder.util.getPropertyType
import com.stepanov.bbf.bugfinder.util.getReturnType
import com.stepanov.bbf.bugfinder.util.getTrue
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.prevLeaf
import kotlin.random.Random

class AddDeadCodeTransformation : MetamorphicTransformation() {
    override fun transform(
        mutationPoint: PsiElement,
        scope: HashMap<Variable, MutableList<String>>,
        expected: Boolean
    ) {
        for (i in 1..Random.nextInt(1, 10)) {
            AddVariablesToScope().transform(mutationPoint, scope, false)
        }
        removeMutation(AddDeadCodeTransformation::class)
        AddIf().apply {
            transform(mutationPoint, scope, false)
            transform(mutationPoint, scope, false)
        }
        addAfterReturn(scope)
    }

    private fun addAfterReturn(scope: HashMap<Variable, MutableList<String>>) {
        val returnExpr = file.getAllPSIChildrenOfType<KtReturnExpression>().randomOrNull()
        val funExpr = file.getAllPSIChildrenOfType<KtNamedFunction>().randomOrNull()
        val throwExpr = file.getAllPSIChildrenOfType<KtThrowExpression>().randomOrNull()
        val expr = mutableListOf(returnExpr, funExpr, throwExpr).filterNotNull().randomOrNull() ?: return

        when (expr) {
            is KtReturnExpression, is KtThrowExpression -> {
                if (Random.getTrue(55)) {
                    val all = getAllMutations()
                    executeMutations(expr, scope, false, all)
                } else {
                    val randomLines = getRandomLinesFromFile() ?: return
                    addAfterMutationPoint(expr) { it.createExpression(randomLines) }
                }
            }
            is KtNamedFunction -> {
                val result = createReturn(expr)
                result?.let {
                    if (Random.getTrue(55)) {
                        val all = getAllMutations()
                        executeMutations(it, scope, false, all)
                    } else {
                        val randomLines = getRandomLinesFromFile() ?: return
                        addAfterMutationPoint(it) { factory -> factory.createExpression(randomLines) }
                    }
                }
            }
        }
    }

    private fun getRandomLinesFromFile(): String? {
        val begin = Random.nextInt(file.text.split("\n").size - 2)
        val end = Random.nextInt(begin + 1, file.text.split("\n").size - 1)
        val nodesBetweenWhS = file.getNodesBetweenWhitespaces(begin, end)
        if (nodesBetweenWhS.isEmpty() || nodesBetweenWhS.all { it is PsiWhiteSpace }) return null

        return nodesBetweenWhS.filter { it.parent !in nodesBetweenWhS }.joinToString("", "kotlin.run {\n", "\n}") {
            if (it is KtProperty) {
                Factory.psiFactory.createProperty(
                    "deadcode_${it.name}",
                    it.getPropertyType(ctx!!).toString(),
                    Random.nextBoolean(),
                    it.initializer?.text
                ).text
            } else it.text
        }
    }

    private fun createReturn(expr: KtNamedFunction): PsiElement? {
        val rig = RandomInstancesGenerator(file as KtFile, ctx!!)
        var returnValue = expr.getReturnType(ctx!!)?.let { rig.generateValueOfType(it) } ?: return null
        if (returnValue == "{}") returnValue = ""
        val result = expr.bodyExpression?.lastChild?.prevLeaf()?.let { mp ->
            checker.addNodeIfPossibleWithNode(mp, Factory.psiFactory.createExpression("return $returnValue"))
        }
        if (result == expr.lastChild.prevSibling) return null
        return result
    }

    private fun getAllMutations(): MutableList<Pair<MetamorphicTransformation, Int>> {
        val old = defaultMutations
        restoreMutations()
        val all = defaultMutations
        all.map {
            if (it !in old) {
                removeMutation(it.first::class)
            }
            it.first to it.second / 2
        }
        return all
    }
}