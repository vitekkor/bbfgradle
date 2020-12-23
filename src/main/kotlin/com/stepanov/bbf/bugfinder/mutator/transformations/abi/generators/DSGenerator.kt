package com.stepanov.bbf.bugfinder.mutator.transformations.abi.generators

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GStructure
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.RandomInstancesGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.UsageSamplesGeneratorWithStLibrary
import com.stepanov.bbf.bugfinder.util.addImport
import com.stepanov.bbf.bugfinder.util.addToTheEnd
import com.stepanov.bbf.bugfinder.util.typeGenerators.RandomTypeGenerator
import com.stepanov.bbf.bugfinder.util.getTrue
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.resolve.BindingContext
import kotlin.random.Random
import kotlin.system.exitProcess

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

    protected open fun generateTypeParams(withModifiers: Boolean): List<String> {
        val typeArgs =
            if (Random.getTrue(40)) List(Random.nextInt(1, 3)) { 'T' - it }.toMutableList()
            else mutableListOf()
        if (typeArgs.isEmpty()) return listOf()
        return typeArgs.map {
            val modifier =
                if (withModifiers) when (Random.nextInt(0, 8)) {
                    0 -> "in "
                    1 -> "out "
                    else -> ""
                } else ""

            if (Random.getTrue(50)) {
                var upperBoundsClass =
                    if (Random.nextBoolean())
                        UsageSamplesGeneratorWithStLibrary.generateOpenClassType(false)
                    else
                        randomTypeGenerator.generateOpenClassType()
                //TODO!! BUG WITH INLINE CLASS AS TYPE BOUND
                if (upperBoundsClass?.isInline == true) upperBoundsClass = null
                val typeParams =
                    upperBoundsClass?.declaredTypeParameters
                        ?.map { randomTypeGenerator.generateRandomTypeWithCtx(it.upperBounds.firstOrNull()) }
                        ?.let {
                            if (it.isEmpty() || it.any { it == null })
                                ""
                            else
                                it.joinToString(
                                    prefix = "<",
                                    postfix = ">"
                                )
                        }
                        ?: ""
                val upperBounds = "${upperBoundsClass?.name}$typeParams"
                if (upperBoundsClass == null || upperBoundsClass.toString().startsWith("Array")) "$modifier$it"
                else "$modifier$it: $upperBounds"
            } else "$modifier$it"
        }
    }

    fun calcImports(): List<String> = UsageSamplesGeneratorWithStLibrary.calcImports(file)

    protected abstract fun simpleGeneration(): PsiElement?
    protected abstract fun partialGeneration(initialStructure: GStructure): PsiElement?
    abstract fun afterGeneration(psi: PsiElement)
    abstract fun beforeGeneration()

    fun generate(): PsiElement? {
        beforeGeneration()
        val r = simpleGeneration() ?: return null
        afterGeneration(r)
        return r
    }

    fun finishGeneration(initialStructure: GStructure): PsiElement? {
        beforeGeneration()
        val r = partialGeneration(initialStructure) ?: return null
        afterGeneration(r)
        return r
    }

    fun generateAndAddToFile(): PsiElement? {
        generate()?.let {
            val added = file.addToTheEnd(it)
            calcImports().forEach { file.addImport(it.substringBeforeLast('.'), true) }
            return added
        } ?: return null
    }
}