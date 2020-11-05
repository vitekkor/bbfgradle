package com.stepanov.bbf.bugfinder.mutator.transformations.abi

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.RandomInstancesGenerator
import com.stepanov.bbf.bugfinder.util.getTrue
import org.jetbrains.kotlin.fir.lightTree.fir.modifier.ModifierSets
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.types.TypeSubstitution
import kotlin.random.Random
import kotlin.system.exitProcess

class RandomClassGenerator(
    private val file: KtFile,
    private val ctx: BindingContext,
    private val parentGClass: GClass? = null
) : DSGenerator(file, ctx) {

    private val gClass: GClass = GClass()
    private val randomInstancesGenerator = RandomInstancesGenerator(file)

    private fun generateModifiers(): List<String> {
        val classModifiers =
            ModifierSets.CLASS_MODIFIER.types.toList()
                .map { it.toString() }
                .filter { it != "fun" && it != "companion" }
                .let {
                    if (parentGClass == null) it.filter { it != "inner" } else it
                }
        val classModifier = if (Random.getTrue(70)) "" else classModifiers.random()
        val visibilityModifiers =
            ModifierSets.VISIBILITY_MODIFIER.types.toList().map { it.toString() }.filter { it != "protected" }
        val visibilityModifier = if (Random.getTrue(20)) "" else visibilityModifiers.random()
        val inheritanceModifiers = ModifierSets.INHERITANCE_MODIFIER.types.toList().map { it.toString() }
        val inheritanceModifier =
            if (Random.getTrue(20) || classModifier.isNotEmpty())
                ""
            else
                inheritanceModifiers.random().let { if (it == "inner" && classModifier.isNotEmpty()) "" else it }
        return listOf(classModifier, visibilityModifier, inheritanceModifier)
    }

    private fun generateConstructor(): List<String> {
        var lb = 0
        val rb = 5
        if (gClass.modifiers.contains("data")) lb = 1
        var numOfArgs = (lb..rb).random()
        if (gClass.modifiers.contains("annotation")) {
            lb = 1
            numOfArgs = (lb..rb).random()
            return MutableList(numOfArgs) {
                "${'a' + it}: ${randomTypeGenerator.generatePrimitive()}"
            }
        }
        val args =
            if (numOfArgs == 0)
                mutableListOf()
            else
                MutableList(numOfArgs) {
                    "${'a' + it}: " to randomTypeGenerator.generateRandomTypeWithCtx()
                }
        val typeArgs = gClass.typeParams.map { it.substringBefore(':') }
        if (args.any { it.second == null }) return emptyList()
        if (typeArgs.isEmpty())
            return args.map { "${it.first}${it.second.toString()}" }
        return args.map { arg ->
            val type = arg.second!!
            val finalType =
                if (type.arguments.isNotEmpty()) {
                    type.toString().substringBefore('<') + type.arguments.joinToString(prefix = "<", postfix = ">") {
                        if (Random.getTrue(50)) typeArgs.random() else it.toString()
                    }
                } else arg.second.toString()
            if (Random.getTrue(30)) "${arg.first}${typeArgs.random()}" else "${arg.first}$finalType"
        }
    }

    private fun delegationSpecifier(): String {
        val openClass = randomTypeGenerator.generateOpenClassType()
        val typeParams = openClass.declaredTypeParameters.map { typeParam ->
            val upperBounds = typeParam.upperBounds.let { if (it.isEmpty()) null else it.first() }
            randomTypeGenerator.generateRandomTypeWithCtx(upperBounds)
        }
        val valueParams =
            when {
                openClass.constructors.any { it.valueParameters.isNotEmpty() && it.visibility.isPublicAPI } -> {
                    val strValueParams =
                        randomInstancesGenerator.generateValueOfType(openClass.defaultType, onlyImpl = true)
                            .substringAfter('(')
                            .substringBeforeLast(')')
                    "(${strValueParams})"
                }
                openClass.constructors.firstOrNull()?.valueParameters?.isEmpty() == true -> "()"
                else -> ""
            }
        val strTP = if (typeParams.isEmpty()) "" else typeParams.joinToString(prefix = " <", postfix = ">") { "$it" }
        return "${openClass.name}$strTP$valueParams"
    }

    private fun generateDelegateSpecifiers(): String {
        val specifiers = if (gClass.modifiers.contains("enum") || gClass.modifiers.contains("annotation"))
            listOf()
        else
        //TODO!! Random.nextInt(0, 3)
            List(Random.nextInt(1, 2)) { delegationSpecifier() }
        gClass.delegationSpecifiers = specifiers
        return specifiers.let { if (it.isEmpty()) "" else it.joinToString(prefix = ": ") }
    }

    override fun generateTypeParams(): List<String> {
        if (gClass.modifiers.contains("enum")) return listOf()
        return super.generateTypeParams()
    }


    override fun generate(): PsiElement? {
//        repeat(25) { ind ->
            val modifiers = generateModifiers()
            gClass.modifiers = modifiers
            val specifiers = generateDelegateSpecifiers()
            val genTypeParams = generateTypeParams()
            val genTypeArgsWObounds = genTypeParams.map { it.substringBefore(':') }
            gClass.typeParams = genTypeParams
            val c = generateConstructor().joinToString(prefix = "(", postfix = ")") { "val $it" }
            val sta = if (genTypeParams.isEmpty()) "" else genTypeParams.joinToString(prefix = "<", postfix = "> ")
            //TMP!! TODO
            //gClass.delegationSpecifiers = listOf("Map<T, String>")
            val body = ClassBodyGenerator(file, ctx, gClass).generate(gClass.delegationSpecifiers.map {
                randomTypeGenerator.generateType(it) ?: exitProcess(0)
            })
            val psiBody = Factory.psiFactory.createBlock(body)
            val kl = "${modifiers.joinToString(" ")} class ${'A' + 0}$sta$c$specifiers${psiBody.text}"
            val psi = Factory.psiFactory.createClass(kl)
            return psi
//            println("${modifiers.joinToString(" ")} class ${'A' + ind}$sta$c$specifiers${psiBody.text}")
//            exitProcess(0)
//        }
//        exitProcess(0)
//        TODO("Not yet implemented")
    }

}

data class GClass(
    var modifiers: List<String> = listOf(),
    var classWord: String = "",
    var name: String = "",
    var typeParams: List<String> = listOf(),
    var constructor: List<String> = listOf(),
    var delegationSpecifiers: List<String> = listOf(),
    var typeConstrains: List<String> = listOf()
) {
    fun toPsi(): PsiElement {
        val m = modifiers.let { if (it.all { it.isEmpty() }) "" else it.joinToString(" ") }
        return Factory.psiFactory.createClass("$m class $name()")
    }
}