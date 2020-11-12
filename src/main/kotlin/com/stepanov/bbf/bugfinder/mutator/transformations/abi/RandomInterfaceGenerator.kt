package com.stepanov.bbf.bugfinder.mutator.transformations.abi

import com.intellij.psi.PsiElement
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
            .random())

    override fun generateConstructor(): List<String> {
        return listOf()
    }

    override fun generateSupertypes(): List<String> {
        //if (Random.nextBoolean()) return listOf()
        val res = mutableListOf<String>()
        for (i in 0..Random.nextInt(1, 3)) {
            val openClass = randomTypeGenerator.generateOpenClassType(onlyInterfaces = true) ?: return listOf()
            val typeParams = openClass.declaredTypeParameters.map { typeParam ->
                val upperBounds = typeParam.upperBounds.let { if (it.isEmpty()) null else it.first() }
                randomTypeGenerator.generateRandomTypeWithCtx(upperBounds).toString()
            }
            val strTP = if (typeParams.isEmpty()) "" else typeParams.joinToString(prefix = "<", postfix = ">")
            if (res.any { it.substringBefore('<').trim() == openClass.name.asString().trim() }) continue
            res.add("${openClass.name}$strTP")
        }
        return res
//        return listOf()
    }

}