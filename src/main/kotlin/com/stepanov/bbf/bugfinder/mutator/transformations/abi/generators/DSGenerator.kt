package com.stepanov.bbf.bugfinder.mutator.transformations.abi.generators

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.RandomInstancesGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.UsageSamplesGeneratorWithStLibrary
import com.stepanov.bbf.bugfinder.util.addImport
import com.stepanov.bbf.bugfinder.util.addToTheEnd
import com.stepanov.bbf.bugfinder.util.typeGenerators.RandomTypeGenerator
import com.stepanov.bbf.bugfinder.util.getTrue
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.resolve.BindingContext
import kotlin.random.Random

abstract class DSGenerator(
    private val file: KtFile,
    ctx: BindingContext
) {
    val randomTypeGenerator: RandomTypeGenerator = RandomTypeGenerator
    val randomInstancesGenerator: RandomInstancesGenerator

    init {
        randomTypeGenerator.setFileAndContext(file, ctx)
        randomInstancesGenerator = RandomInstancesGenerator(file)
    }

    open fun generateTypeParams(withModifiers: Boolean): List<String> {
        val typeArgs =
            if (Random.getTrue(40)) List(Random.nextInt(1, 3)) { 'T' - it }.toMutableList()
            else mutableListOf()
        if (typeArgs.isEmpty()) return listOf()
        return typeArgs.map {
            val modifier =
                if (withModifiers) when (Random.nextInt(0, 5)) {
                    0 -> "in "
                    1 -> "out "
                    else -> ""
                } else ""
            "$modifier$it"
            //TODO!!! UNCOMMENT
//            if (Random.getTrue(20)) {
//                val upperBounds = randomTypeGenerator.generateRandomTypeWithCtx()
//                if (upperBounds == null || upperBounds.toString().startsWith("Array")) "$it"
//                else "$it: $upperBounds"
//            }
//            else "$it"
        }
    }

    fun calcImports(): List<String> = UsageSamplesGeneratorWithStLibrary.calcImports(file)

    abstract fun generate(): PsiElement?

    fun generateAndAddToFile(): PsiElement? {
        generate()?.let {
            val added = file.addToTheEnd(it)
            calcImports().forEach { file.addImport(it.substringBeforeLast('.'), true) }
            return added
        } ?: return null
    }
}