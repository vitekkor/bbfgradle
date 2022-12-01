package com.stepanov.bbf.bugfinder.mutator.metamorphicTransformations

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.util.getTrue
import com.stepanov.bbf.reduktor.util.getAllChildren
import org.jetbrains.kotlin.psi.*
import java.io.File
import java.util.stream.Collectors
import kotlin.random.Random

class AddRandomExpressions: MetamorphicTransformation() {
    private val database by lazy { File("database.txt").bufferedReader().lines().collect(Collectors.toList()) }

    override fun transform(
        mutationPoint: PsiElement,
        scope: HashMap<Variable, MutableList<String>>,
        expected: Boolean
    ) {
        for (i in 0..Random.nextInt(10)) {
            val node: ASTNode = if (Random.getTrue(69)) {
                file.chooseMutationPoint() ?: mutationPoint
            } else {
                mutationPoint
            }.node

            val psi = getRandomFile()
            val ingredient = psi.getIngredient() ?: continue
            resolveIngredient(ingredient)
        }
    }

    private fun getRandomFile(): KtFile {
        val line = database.random()
        val files = line.dropLast(1).takeLastWhile { it != '[' }.split(", ")
        val randomFile = files.random()
        return Factory.psiFactory.createFile(File("${CompilerArgs.baseDir}/$randomFile").readText())
    }

    private fun KtFile.getIngredient(): PsiElement? {
        return getAllChildren().filter {
            it is KtIfExpression || it is KtLoopExpression || it is KtWhenExpression
        }.randomOrNull()
    }

    private fun resolveIngredient(ingredient: PsiElement) {
        when (ingredient) {
            is KtIfExpression -> {
                ingredient.condition!!.children.forEach {

                }
            }
            is KtLoopExpression -> {

            }
            is KtWhenExpression -> {

            }
        }
    }
}