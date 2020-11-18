package com.stepanov.bbf.bugfinder.mutator.transformations.abi.generators

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GClass
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GFunction
import com.stepanov.bbf.bugfinder.util.getAllWithoutLast
import com.stepanov.bbf.bugfinder.util.getRandomVariableName
import com.stepanov.bbf.bugfinder.util.getTrue
import com.stepanov.bbf.bugfinder.util.replaceTypeOrRandomSubtypeOnTypeParam
import org.jetbrains.kotlin.fir.lightTree.fir.modifier.ModifierSets
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.types.expressions.OperatorConventions
import kotlin.random.Random
import kotlin.system.exitProcess

class RandomFunctionGenerator(
    file: KtFile,
    ctx: BindingContext,
    val gClass: GClass? = null
) : DSGenerator(file, ctx) {

    private val gFunc = GFunction()

    private fun generateModifiers(): List<String> =
        mutableListOf(
            listOf("inline", "", "", "", "", "").random(),
            (ModifierSets.FUNCTION_MODIFIER.types.toList().map { it.toString() } + listOf("", "", "")).random(),
            (ModifierSets.VISIBILITY_MODIFIER.types.toList().getAllWithoutLast().map { it.toString() } + listOf(
                "",
                ""
            )).random()
        ).let { list ->
            if (gClass?.isFunInterface() == true) {
                if (list[1].trim().let { it != "infix" || it != "operator" } ) list[1] = ""
                list[2] = ""
            }
            if (list[1] == "external") list[0] = ""
            list
        }

    private fun generateExtension(typeArgs: List<String>): String {
        val prob = if (gFunc.modifiers.contains("infix") || gFunc.modifiers.contains("operator")) 100 else 20
        if (!Random.getTrue(prob)) return ""
        val t = randomTypeGenerator.generateRandomTypeWithCtx() ?: return ""
        val strT =
            if (t.arguments.isEmpty() || typeArgs.isEmpty()) {
                t.toString()
            } else {
                t.toString().substringBefore('<') + t.arguments.joinToString(prefix = "<", postfix = ">") {
                    if (Random.getTrue(20)) typeArgs.random() else it.toString()
                }
            }
        return strT
    }

    private fun generateArgs(typeArgs: List<String>): List<String> {
        var rightBound = if (gFunc.modifiers.contains("infix")) 2 else 5
        if (gFunc.modifiers.contains("operator")) rightBound = getNumOfArgsAndRTVForOperator(gFunc.name).first + 1
        val args =
            if (rightBound - 1 <= 0)
                mutableListOf()
            else
                MutableList(Random.nextInt(1, rightBound)) {
                    "${'a' + it}: ${randomTypeGenerator.generateRandomTypeWithCtx()}"
                }
        val argsWithTypeArgs =
            if (typeArgs.isEmpty())
                args
            else
                args.map {
                    if (Random.getTrue(30)) {
                        it.replaceAfter(':', " ${typeArgs.random()}")
                    } else it
                }
        return argsWithTypeArgs
    }

    private fun generateName(): String {
        if (gFunc.modifiers.contains("operator")) {
            return OperatorConventions.CONVENTION_NAMES.random().asString()
        }
        return Random.getRandomVariableName(5)
    }

    //TODO handle all possible operators
    private fun getNumOfArgsAndRTVForOperator(operator: String): Pair<Int, String> {
        return when (operator) {
            in OperatorConventions.UNARY_OPERATION_NAMES.values.map { it.asString() } -> 0 to "type"
            in OperatorConventions.BINARY_OPERATION_NAMES.values.map { it.asString() } -> 1 to "_"
            in OperatorConventions.ASSIGNMENT_OPERATIONS.values.map { it.asString() } -> 1 to "Unit"
            else -> 0 to "Boolean"
        }
    }

    private fun generateRtv(): String {
        if (gFunc.modifiers.contains("operator")) {
            when (getNumOfArgsAndRTVForOperator(gFunc.name).second) {
                "type" -> return gFunc.extensionReceiver
                "_" -> return randomTypeGenerator.generateRandomTypeWithCtx().toString()
                else -> return getNumOfArgsAndRTVForOperator(gFunc.name).second
            }
        }
        val randomType = randomTypeGenerator.generateRandomTypeWithCtx() ?: return ""
        return gClass?.let { randomType.replaceTypeOrRandomSubtypeOnTypeParam(it.typeParams) } ?: "$randomType"
    }


    override fun generate(): PsiElement? {
        val genTypeArgs = if (gClass?.isFunInterface() == true) listOf() else generateTypeParams(false)
        val genTypeArgsWObounds = genTypeArgs.map { it.substringBefore(':') }
        with(gFunc) {
            modifiers = generateModifiers()
            typeArgs = genTypeArgs
            extensionReceiver = generateExtension(genTypeArgsWObounds)
            name = generateName()
            args = generateArgs(genTypeArgsWObounds)
            rtvType = generateRtv()
        }
        return gFunc.toPsi()
    }

}