package com.stepanov.bbf.bugfinder.mutator.transformations.abi

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.util.RandomTypeGenerator
import com.stepanov.bbf.bugfinder.util.getTrue
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.resolve.BindingContext
import kotlin.random.Random

abstract class DSGenerator(
    file: KtFile,
    ctx: BindingContext
) {
    val randomTypeGenerator: RandomTypeGenerator = RandomTypeGenerator

    init {
        randomTypeGenerator.setFileAndContext(file, ctx)
    }

    abstract fun generate(): PsiElement?

    open fun generateTypeParams(): List<String> {
        val typeArgs =
            if (Random.getTrue(30)) List(Random.nextInt(1, 3)) { 'T' - it }.toMutableList() else mutableListOf()
        if (typeArgs.isEmpty()) return listOf()
        val res = typeArgs.map {
            if (Random.getTrue(20)) "$it: ${randomTypeGenerator.generateRandomTypeWithCtx()}"
            else "$it"
        }
        return res
    }


}