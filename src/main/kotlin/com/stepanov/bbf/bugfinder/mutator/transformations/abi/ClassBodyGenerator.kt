package com.stepanov.bbf.bugfinder.mutator.transformations.abi

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.RandomInstancesGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.UsageSamplesGeneratorWithStLibrary
import com.stepanov.bbf.bugfinder.util.getRandomVariableName
import com.stepanov.bbf.bugfinder.util.getTrue
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.descriptors.PropertyDescriptor
import org.jetbrains.kotlin.psi.KtCallExpression
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.types.KotlinType
import kotlin.random.Random
import kotlin.system.exitProcess

open class ClassBodyGenerator(
    private val file: KtFile,
    private val ctx: BindingContext,
    private val gClass: GClass,
    private val depth: Int = 0
) : DSGenerator(file, ctx) {

    private val randomInstancesGenerator = RandomInstancesGenerator(file)

    private fun generatePropertyOverriding(isVar: Boolean, name: String, returnType: String): String {
        val varOrVal = if (isVar) "var" else "val"
        val res = StringBuilder()
        res.append(
            "\noverride $varOrVal $name: $returnType\n" +
                    "    get() = TODO()\n"
        )
        if (isVar) res.append("    set(value) {}\n")
        return res.toString()
    }

    private fun generateOverrides(gClass: GClass, specifiers: List<KotlinType>): String {
        val res = StringBuilder()
        for (specifier in specifiers) {
            val membersToOverride = UsageSamplesGeneratorWithStLibrary.getMembersToOverride(specifier)
            val filteredMembers =
                if (gClass.modifiers.let { it.contains("interface") || it.contains("abstract") })
                    membersToOverride.filter { Random.getTrue(30) }
                else
                    membersToOverride.filterNot { it is FunctionDescriptor && it.modality == Modality.OPEN && Random.nextBoolean() }
            for (member in filteredMembers) {
                val rtv = member.toString().substringAfterLast(":").substringBefore(" defined")
                if (member is PropertyDescriptor) {
                    res.append(generatePropertyOverriding(member.isVar, member.name.asString(), rtv))
                } else if (member is FunctionDescriptor) {
                    val f = member.toString().substringAfter("fun").substringBefore(" defined").split(" = ...")
                        .joinToString(" ")
                    res.append("\noverride fun$f = TODO()\n")
                }
            }
        }
        return res.toString()
    }

    private fun generateEnumFields() =
        with(StringBuilder()) {
            val constructorTypes = gClass.constructor.map {
                it.split(":").let { randomTypeGenerator.generateType(it.last()) } ?: return ""
            }
            val isVar = Random.nextBoolean()
            val varOrVal = if (isVar) "var" else "val"
            val fieldToOverrideName = Random.getRandomVariableName()
            val fieldToOverride = if (Random.nextBoolean()) null else randomTypeGenerator.generateRandomTypeWithCtx()
            repeat(Random.nextInt(1, 4)) {
                val name = Random.getRandomVariableName(2).toUpperCase()
                val values = constructorTypes.map { randomInstancesGenerator.generateValueOfType(it) }
                append("$name(${values.joinToString()})")
                fieldToOverride?.let {
                    append('{')
                    append(generatePropertyOverriding(isVar, fieldToOverrideName, fieldToOverride.toString()))
                    append('}')
                }
                append(",\n")
            }
            replace(length - 2, length, ";\n")
            fieldToOverride?.let { append("abstract $varOrVal $fieldToOverrideName: $it") }
            this
        }.toString()


    private fun generatePropWithAnonObj(): String {
        val openKlass = randomTypeGenerator.generateOpenClassType(true) ?: return ""
        val instance =
            if (openKlass.constructors.isEmpty() || openKlass.modality != Modality.OPEN)
                null
            else
                randomInstancesGenerator.generateValueOfType(openKlass.defaultType)
        val genTypeParams =
            if (instance == null) {
                openKlass.declaredTypeParameters.map { randomTypeGenerator.generateRandomTypeWithCtx().toString() }
            } else {
                (Factory.psiFactory.createExpression(instance) as KtCallExpression).typeArguments.map { it.text }
            }
        val tp = if (genTypeParams.isEmpty()) "" else genTypeParams.joinToString(prefix = " <", postfix = ">")
        val c = instance?.let { "(${it.substringAfter('(')}" } ?: ""
        val def = "object: ${openKlass.name}$tp$c"
        val gClass = GClass()
        val overrides = generateOverrides(gClass, listOf(openKlass.defaultType))
        println("$def{\n$overrides}\n")
        exitProcess(0)
        return ""
    }

    private fun generateFields(): String {
        if (gClass.isAnnotation()) return ""
        if (gClass.isEnum()) return generateEnumFields()
        val res = StringBuilder()
        val randomFunGenerator = RandomFunctionGenerator(file, ctx)
        with(res) {
            append(generatePropWithAnonObj())
            //TODO Random.nextInt(0, 5)
            repeat(Random.nextInt(0, 2)) {
                append("\n")
                append(randomFunGenerator.generate()?.text)
                append("\n")
            }
            //TODO PROPERTY GENERATOR
            repeat(Random.nextInt(1, 2)) {
                append("\n")
                append("val ${Random.getRandomVariableName(5)}: ${randomTypeGenerator.generateRandomTypeWithCtx()} = TODO()")
                append("\n")
            }
        }
        return res.toString()
    }

    private fun generateInnerClass(): String {
        val klassGenerator = RandomClassGenerator(file, ctx, depth + 1)
        val kl = klassGenerator.generate()
        return kl?.text ?: ""
    }


    fun generate(specifiers: List<KotlinType>) =
        with(StringBuilder()) {
            //append(generateOverrides(gClass, specifiers))
            append(generateFields())
            append(if (depth <= MAX_DEPTH && Random.nextBoolean()) generateInnerClass() else "")
        }.toString()


    override fun generate(): PsiElement? {
        TODO("Not yet implemented")
    }

}

//TODO = 3
private const val MAX_DEPTH = 2