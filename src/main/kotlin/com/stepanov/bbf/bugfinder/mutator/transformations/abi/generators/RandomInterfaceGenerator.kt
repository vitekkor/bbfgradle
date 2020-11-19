package com.stepanov.bbf.bugfinder.mutator.transformations.abi.generators

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.util.getTrue
import org.jetbrains.kotlin.descriptors.DescriptorVisibility
import org.jetbrains.kotlin.fir.lightTree.fir.modifier.ModifierSets
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.resolve.BindingContext
import kotlin.random.Random
import kotlin.system.exitProcess

class RandomInterfaceGenerator(file: KtFile, ctx: BindingContext, depth: Int = 0) : ClassGenerator(file, ctx, depth) {

    override val classWord: String
        get() = "interface"

    override fun generateModifiers(): List<String> =
        listOf(ModifierSets.VISIBILITY_MODIFIER.types.toList().map { it.toString() }.filter { it != "protected" }
            .random()).let {
            if (Random.getTrue(10)) it + listOf("fun") else it
        }

    override fun generateConstructor(): List<String> {
        return listOf()
    }

    override fun generateSupertypes(): List<String> {
        if (gClass.isFunInterface()) return listOf()
        val res = mutableListOf<String>()
        val supertypesAmount = Random.nextInt(0, 3)
        for (i in 0 until supertypesAmount) {
            val openClass = randomTypeGenerator.generateOpenClassType(onlyInterfaces = true) ?: return listOf()
            if (compareVisibilitiesAsString(gClass.getVisibility(), openClass.visibility.name) == -1) continue
            val typeParams = openClass.declaredTypeParameters.map { typeParam ->
                val upperBounds = typeParam.upperBounds.let { if (it.isEmpty()) null else it.first() }
                randomTypeGenerator.generateRandomTypeWithCtx(upperBounds).toString()
            }
            val strTP = if (typeParams.isEmpty()) "" else typeParams.joinToString(prefix = "<", postfix = ">")
            if (res.any { it.substringBefore('<').trim() == openClass.name.asString().trim() }) continue
            res.add("${openClass.name}$strTP")
        }
        return res
    }

    override fun generateAnnotations(): List<String> = listOf()

    private fun compareVisibilitiesAsString(v1: String, v2: String): Int =
        when {
            v1 == v2 -> 0
            v1 == "public" -> -1
            v2 == "public" -> 1
            v1 == "" -> -1
            v2 == "" -> 1
            v1 == "internal" -> -1
            v2 == "internal" -> 1
            else -> 0
        }


}