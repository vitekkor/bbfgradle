package com.stepanov.bbf.bugfinder.mutator.transformations.abi

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.util.*
import com.stepanov.bbf.bugfinder.util.typeGenerators.RandomTypeGeneratorForAnClass
import org.jetbrains.kotlin.backend.common.serialization.findPackage
import org.jetbrains.kotlin.fir.lightTree.fir.modifier.ModifierSets
import org.jetbrains.kotlin.psi.KtCallExpression
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.types.typeUtil.isInterface
import kotlin.random.Random
import kotlin.system.exitProcess

open class RandomClassGenerator(
    override val file: KtFile,
    override val ctx: BindingContext,
    override val depth: Int = 0
) : ClassGenerator(file, ctx, depth) {

    override val classWord: String
        get() = "class"

    override fun generateModifiers(): List<String> {
        val classModifiers =
            ModifierSets.CLASS_MODIFIER.types.toList()
                .map { it.toString() }
                .filter { it != "fun" && it != "companion" }
                .let {
                    if (depth == 0) it.filter { it != "inner" } else it
                }
        //TODO!!!
        //val classModifier = if (Random.getTrue(70)) "" else classModifiers.random()
        val classModifier = ""
        val visibilityModifiers =
            ModifierSets.VISIBILITY_MODIFIER.types.toList().map { it.toString() }.filter { it != "protected" }
        val visibilityModifier = if (Random.getTrue(20)) "" else visibilityModifiers.random()
        val inheritanceModifiers = ModifierSets.INHERITANCE_MODIFIER.types.toList().map { it.toString() }
        //TODO!!!
//        val inheritanceModifier =
//            if (Random.getTrue(20) || classModifier.isNotEmpty())
//                ""
//            else
//                inheritanceModifiers.random().let { if (it == "inner" && classModifier.isNotEmpty()) "" else it }
        val inheritanceModifier = "open"
        return listOf(classModifier, visibilityModifier, inheritanceModifier)
    }

    private fun generateModifierForConstructorProperty(haveVararg: Boolean): String =
        if (gClass.isData()) if (Random.nextBoolean()) "var " else "val "
        else when (Random.nextInt(0, 3)) {
            1 -> "var "
            2 -> if (Random.nextBoolean()) "vararg " else ""
            else -> "val "
        }

    override fun generateConstructor(): List<String> {
        var lb = 0
        val rb = 5
        var haveVararg = false
        if (gClass.isData()) lb = 1
        var numOfArgs = (lb..rb).random()
        if (gClass.isAnnotation()) {
            lb = 1
            numOfArgs = (lb..rb).random()
            return MutableList(numOfArgs) {
                "val ${Random.getRandomVariableName(3)}: ${RandomTypeGeneratorForAnClass.generateTypeForAnnotationClass()}"
            }
        }
        val args =
            if (numOfArgs == 0)
                mutableListOf()
            else
                MutableList(numOfArgs) {
                    val m = generateModifierForConstructorProperty(haveVararg)
                    if (m == "vararg") haveVararg = true
                    "$m${Random.getRandomVariableName(3)}: " to randomTypeGenerator.generateRandomTypeWithCtx()
                }
        val typeArgs = gClass.typeParams.map { it.substringBefore(':') }
        if (args.any { it.second == null }) return emptyList()
        if (typeArgs.isEmpty())
            return args.map {
                val defaultValue =
                    if (Random.getTrue(30)) {
                        randomInstancesGenerator.generateValueOfType(it.second!!).let {
                            if (it.trim().isNotEmpty()) " = $it"
                            else ""
                        }
                    } else ""
                "${it.first}${it.second.toString()}$defaultValue"
            }
        return args.map { arg ->
            val type = arg.second!!
            val finalType =
                if (type.arguments.isNotEmpty()) {
                    //println("TYPE = $type")
                    type.toString().substringBefore('<') + type.arguments.joinToString(prefix = "<", postfix = ">") {
                        if (Random.getTrue(50)) typeArgs.random() else it.toString()
                    }
                } else arg.second.toString()
            val defaultValue =
                if (finalType == arg.second.toString() && Random.getTrue(30) && arg.second != null && !type.isInterface())
                    " = ${randomInstancesGenerator.generateValueOfType(arg.second!!)}"
                else ""
            if (Random.getTrue(30)) "${arg.first}${typeArgs.random()}" else "${arg.first}$finalType$defaultValue"
        }
    }

    private fun generateSupertypes1(): Pair<String, String>? {
        val openClass = randomTypeGenerator.generateOpenClassType() ?: return null
        val importPath = openClass.findPackage().fqName.asString()
        var typeParams = openClass.declaredTypeParameters.map { typeParam ->
            val upperBounds = typeParam.upperBounds.let { if (it.isEmpty()) null else it.first() }
            randomTypeGenerator.generateRandomTypeWithCtx(upperBounds).toString()
        }
        val valueParams =
            when {
                openClass.constructors.any { it.valueParameters.isNotEmpty() && it.visibility.isPublicAPI } -> {
                    val generated = randomInstancesGenerator.generateValueOfType(openClass.defaultType, onlyImpl = true)
                    if (generated.isEmpty()) {
                        println("CANT GENERATE EXPRESSION FROM ${openClass.defaultType}")
                        exitProcess(0)
                    }
                    val call = Factory.psiFactory.createExpression(generated) as KtCallExpression
                    typeParams = call.typeArguments.map { it.text }
                    val strValueParams = generated.substringAfter('(').substringBeforeLast(')')
                    "(${strValueParams})"
                }
                openClass.constructors.firstOrNull()?.valueParameters?.isEmpty() == true -> "()"
                else -> ""
            }
        val strTP = if (typeParams.isEmpty()) "" else typeParams.joinToString(prefix = " <", postfix = ">")
        return "${openClass.name}$strTP$valueParams" to importPath
    }

    override fun generateSupertypes(): List<String> {
        val specifiers =
            if (gClass.isEnum() || gClass.isAnnotation())
                listOf()
            else if (Random.nextBoolean())
                listOf()
            else
                List(Random.nextInt(1, 3)) { generateSupertypes1() }.filterNotNull()
        gClass.imports.addAll(
            specifiers
                .filter { it.second.trim().isNotEmpty() }
                .map {
                    "${it.second}.${
                        it.first.substringBefore('<').substringBefore("(").filter { it != '`' }.trim()
                    }"
                }
        )
        gClass.supertypes = specifiers.map { it.first }
        return specifiers.map { it.first }
    }

    override fun generateTypeParams(): List<String> {
        if (gClass.isEnum() || gClass.isAnnotation()) return listOf()
        return super.generateTypeParams()
    }

    override fun generate(): PsiElement? {
//        repeat(25) { ind ->
        val modifiers = generateModifiers()
        gClass.modifiers = modifiers
        val specifiers = generateSupertypes().let { if (it.isEmpty()) "" else it.joinToString(prefix = ": ") }
        val genTypeParams = generateTypeParams()
        val genTypeArgsWObounds = genTypeParams.map { it.substringBefore(':') }
        gClass.typeParams = genTypeParams
        val c = generateConstructor().let {
            gClass.constructorArgs = it
            it.joinToString(prefix = "(", postfix = ")")
        }
        val sta = if (genTypeParams.isEmpty()) "" else genTypeParams.joinToString(prefix = "<", postfix = "> ")
        //TMP!! TODO
        //gClass.delegationSpecifiers = listOf("ABC<Int>")
        val body = ClassBodyGenerator(file, ctx, gClass, depth + 1).generateBodyAsString()
        val psiBody = Factory.psiFactory.createBlock(body)
        //val imports = gClass.imports.joinToString("\n"){ "import $it "}
        val a =
            if (gClass.isAnnotation())
                "@Retention(AnnotationRetention.RUNTIME)\n"
            else
                ""
        val kl = "$a${modifiers.joinToString(" ")} class ${
            Random.getRandomVariableName(3).capitalize()
        }$sta$c$specifiers${psiBody.text}"
        try {
            Factory.psiFactory.createClass(kl)
        } catch (e: Exception) {
            println("cant create class $kl")
            exitProcess(0)
        }
        val psi = Factory.psiFactory.createClass(kl)
        //Kostyl'
        gClass.imports.forEach { file.addImport(it, false) }
        return psi
//            println("${modifiers.joinToString(" ")} class ${'A' + ind}$sta$c$specifiers${psiBody.text}")
//            exitProcess(0)
//        }
//        exitProcess(0)
//        TODO("Not yet implemented")
    }

}