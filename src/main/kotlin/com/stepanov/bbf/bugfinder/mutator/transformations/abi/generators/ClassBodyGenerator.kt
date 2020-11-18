package com.stepanov.bbf.bugfinder.mutator.transformations.abi.generators

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GClass
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.UsageSamplesGeneratorWithStLibrary
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.bugfinder.util.getRandomVariableName
import com.stepanov.bbf.bugfinder.util.getTrue
import com.stepanov.bbf.bugfinder.util.removeDuplicatesBy
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.descriptors.PropertyDescriptor
import org.jetbrains.kotlin.psi.KtCallExpression
import org.jetbrains.kotlin.psi.KtClassOrObject
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

    //    private val randomInstancesGenerator = RandomInstancesGenerator(file)
    private val randomFunGenerator = RandomFunctionGenerator(file, ctx, gClass)

    private fun generatePropertyOverriding(isVar: Boolean, name: String, returnType: String): String {
        val varOrVal = if (isVar) "var" else "val"
        val res = StringBuilder()
        if (gClass.isInterface()) return "\noverride $varOrVal $name: $returnType"
        res.append(
            "\noverride $varOrVal $name: $returnType\n" +
                    "    get() = TODO()\n"
        )
        if (isVar) res.append("    set(value) {}\n")
        return res.toString()
    }

    private fun generateOverrides(gClass: GClass, specifiers: List<KotlinType>): String {
        val res = StringBuilder()
        val filteredMembers = specifiers.flatMap { specifier ->
            val membersToOverride = UsageSamplesGeneratorWithStLibrary.getMembersToOverride(specifier)
            if (gClass.let { it.isInterface() || it.isAbstract() })
                membersToOverride.filter { Random.getTrue(30) }
            else
                membersToOverride.filterNot { it is FunctionDescriptor && it.modality == Modality.OPEN && Random.nextBoolean() }
        }.removeDuplicatesBy {
            if (it is FunctionDescriptor) "${it.name}${it.valueParameters.map { it.name.asString() + it.returnType.toString() }}"
            else (it as PropertyDescriptor).name.asString()
        }
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
        return res.toString()
    }

    private fun generateEnumFields() =
        with(StringBuilder()) {
            val constructorTypes = gClass.constructorArgs.map {
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
            fieldToOverride?.let { append("abstract $varOrVal $fieldToOverrideName: $it\n") }
            this
        }.toString()


    private fun generatePropWithAnonObj(fromObject: Boolean): String {
        val res = StringBuilder()
        var withInheritance = false
        val lhv =
            if (Random.nextBoolean()) {
                (if (Random.nextBoolean()) "val" else "var") + " ${Random.getRandomVariableName()}"
            } else {
                randomFunGenerator.generate()?.text?.substringBeforeLast(':')
            }
        res.append(lhv)
        if (Random.nextBoolean()) {
            withInheritance = true
            val openKlass = randomTypeGenerator.generateOpenClassType(true) ?: return ""
            val instance =
                if (openKlass.modality == Modality.ABSTRACT)
                    ")"
                else if (openKlass.constructors.isEmpty() || openKlass.modality != Modality.OPEN)
                    null
                else
                    randomInstancesGenerator.generateValueOfType(openKlass.defaultType)
            val genTypeParams =
                if (instance == null || instance == ")" || instance.isEmpty()) {
                    openKlass.declaredTypeParameters.map { randomTypeGenerator.generateRandomTypeWithCtx().toString() }
                } else {
                    (Factory.psiFactory.createExpression(instance) as? KtCallExpression)?.typeArguments?.map { it.text }
                        ?: return ""
                }
            val tp = if (genTypeParams.isEmpty()) "" else genTypeParams.joinToString(prefix = " <", postfix = ">")
            val c = instance?.let { "(${it.substringAfter('(')}" } ?: ""
            val def = "object: ${openKlass.name}$tp$c"
            val typeWOTypeParams = randomTypeGenerator.generateType("${openKlass.name}$tp$c") ?: return ""
            val gClass = GClass()
            val overrides = generateOverrides(gClass, listOf(typeWOTypeParams))
            res.append(": ${openKlass.name}$tp")
            res.append(" = $def {")
            res.append(overrides)
        }
        if (!withInheritance) res.append(" = object {")
        val propsAndFuncs = generateFieldsForRegularClass(depth = Int.MAX_VALUE, true)
        res.append(propsAndFuncs)
        res.append("}")
        return res.toString()
    }

    private fun generateFieldsForRegularClass(depth: Int = 0, fromObject: Boolean = false): String {
        val res = StringBuilder()
        if (gClass.isFunInterface()) {
            val f = randomFunGenerator.generate()?.text?.substringBeforeLast('=') ?: ""
            return "\nabstract ${f}\n"
        }
        with(res) {
            if (depth < MAX_DEPTH && Random.getTrue(20) && !gClass.isInterface()) append(generatePropWithAnonObj(fromObject))
            //TODO Random.nextInt(0, 5)
            repeat(Random.nextInt(0, 2)) {
                append("\n")
                append(randomFunGenerator.generate()?.text)
                append("\n")
            }
            //TODO Random.nextInt(1, 5)
            repeat(Random.nextInt(0, 2)) {
                append("\n")
                append(RandomPropertyGenerator(file, gClass, ctx).generateRandomProperty(fromObject))
                //append("val ${Random.getRandomVariableName(5)}: ${randomTypeGenerator.generateRandomTypeWithCtx()} = TODO()")
                append("\n")
            }
        }
        return res.toString()
    }

    private fun generateFields(depth: Int = 0): String {
        if (gClass.isAnnotation()) return ""
        if (gClass.isEnum()) return generateEnumFields()
        return generateFieldsForRegularClass(depth)
    }

    private fun generateSecondaryConstructor(): String {
        if (Random.getTrue(50)) return ""
        if (gClass.constructorArgs.isEmpty()) return ""
        if (gClass.classWord != "class" || gClass.isAnnotation() || gClass.isAbstract()) return ""
        val newConstructor =
            RandomClassGenerator(file, ctx).also { it.gClass = gClass }.generateConstructor()
                .joinToString { it.substringAfter(' ') }
        val valueParams = gClass.constructorArgs
            .map { randomTypeGenerator.generateType(it.substringAfter(": ")) }
            .map { it ?: return "" }
            .map { randomInstancesGenerator.generateValueOfType(it).let { if (it.isEmpty()) return "" else it } }
            .joinToString(prefix = "(", postfix = ")")
        return "constructor($newConstructor):this$valueParams\n"
    }

    private fun generateCompanionObject(): String {
        if (gClass.isInner() || Random.getTrue(50)) return ""
        if (gClass.classWord != "class" || gClass.isAnnotation()) return ""
        val tp = gClass.typeParams
        gClass.typeParams = listOf()
        val body = generateFieldsForRegularClass(depth, true)
        gClass.typeParams = tp
        return "companion object {\n$body\n}\n"
    }

    private fun generateInnerClass(): String {
        if (gClass.isAnnotation()) return ""
        val klassGenerator = RandomClassGenerator(file, ctx, depth + 1, gClass)
        val kl = klassGenerator.generate()
        return kl?.text ?: ""
    }

    fun generateBodyAsString(): String {
        val kTypeSpecifiers = gClass.supertypes.map {
            randomTypeGenerator.generateType(it.substringBefore('(')) ?: return ""
        }
        return with(StringBuilder()) {
            append(generateOverrides(gClass, kTypeSpecifiers))
            append(generateFields())
            append(generateSecondaryConstructor())
            append(generateCompanionObject())
            append(
                if (depth <= MAX_DEPTH && !gClass.isInterface() && Random.nextBoolean())
                    generateInnerClass()
                else ""
            )
        }.toString()
    }

    override fun generate(): PsiElement? =
        try {
            Factory.psiFactory.createBlock(generateBodyAsString())
        } catch (e: Exception) {
            null
        }


}

//TODO = 3
private const val MAX_DEPTH = 2