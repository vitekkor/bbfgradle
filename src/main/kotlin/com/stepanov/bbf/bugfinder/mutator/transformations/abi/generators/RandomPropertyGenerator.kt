package com.stepanov.bbf.bugfinder.mutator.transformations.abi.generators

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GClass
import com.stepanov.bbf.bugfinder.util.*
import org.jetbrains.kotlin.backend.common.serialization.findPackage
import org.jetbrains.kotlin.descriptors.*
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.DescriptorUtils
import org.jetbrains.kotlin.resolve.bindingContextUtil.getAbbreviatedTypeOrType
import org.jetbrains.kotlin.resolve.descriptorUtil.getAllSuperClassifiers
import org.jetbrains.kotlin.resolve.scopes.getDescriptorsFiltered
import org.jetbrains.kotlin.types.isNullable
import org.jetbrains.kotlin.types.typeUtil.makeNotNullable
import kotlin.random.Random
import kotlin.system.exitProcess

class RandomPropertyGenerator(
    private val file: KtFile,
    private val gClass: GClass = GClass(),
    private val ctx: BindingContext
) : DSGenerator(file, ctx) {


    fun generateInterestingProperty(): PsiElement? {
        //Get classes which are supertypes of us
        val klasses = file.getAllPSIChildrenOfType<KtTypeReference>()
            .mapNotNull { it.getAbbreviatedTypeOrType(ctx)?.constructor?.declarationDescriptor?.findPackage() }
            .firstOrNull { it.fqName == file.packageFqName }
            ?.getMemberScope()?.getDescriptorsFiltered { true }
            ?.filterIsInstance<ClassDescriptor>() ?: return null
        val children =
            klasses.filter {
                it.name.asString() != gClass.name &&
                        it.getAllSuperClassifiers().any { it.name.asString() == gClass.name }
            }
        val child = children.randomOrNull() ?: return null
        val overrides =
            child.defaultType.memberScope.getDescriptorsFiltered { true }
                .filterIsInstance<CallableMemberDescriptor>()
                .filter { DescriptorUtils.isOverride(it) }
                .filter { it.name.asString().let { it != "equals" && it != "hashCode" && it != "toString" } }
        val randomOverride = overrides.randomOrNull() ?: return null
        var isFun = true
        val overrideToStr =
            if (randomOverride is SimpleFunctionDescriptor) {
                if (gClass.isInterface() || gClass.isAbstract() && Random.nextBoolean())
                    "public abstract fun ${
                        randomOverride.toString().substringAfter("fun").substringBefore("defined ")
                    }"
                else
                    "public open fun ${
                        randomOverride.toString().substringAfter("fun").substringBefore("defined ")
                    } = TODO()"
            } else if (randomOverride is PropertyDescriptor) {
                isFun = false
                val varOrVal = if (randomOverride.isVar) "var" else "val"
                if (gClass.isInterface() || gClass.isAbstract() && Random.nextBoolean())
                    "public abstract $varOrVal ${
                        randomOverride.toString().substringAfter(varOrVal).substringBefore("defined ")
                    }"
                else "public open $varOrVal ${
                    randomOverride.toString().substringAfter(varOrVal).substringBefore("defined ")
                } = TODO()"
            } else ""
        return try {
            if (isFun) Factory.psiFactory.createFunction(overrideToStr)
            else Factory.psiFactory.createProperty(overrideToStr)
        } catch (e: Exception) {
            null
        }
    }

    private fun makePropExtension(prop: String, isVar: Boolean): String {
        val rec = randomTypeGenerator.generateRandomTypeWithCtx()
        val body =
            if (!prop.contains('=')) "TODO()"
            else prop.substringAfter('=').let { if (it.trim().isEmpty()) "TODO()" else it }
        val getter = "get() = $body"
        val valOrVar = if (isVar) "var" else "val"
        return "$valOrVar $rec.${prop.substringBefore('=')}\n$getter\n"
    }

    fun generateRandomProperty(fromObject: Boolean): String {
        val randomType = randomTypeGenerator.generateRandomTypeWithCtx() ?: return ""
        val withTypeParams = randomType.replaceTypeOrRandomSubtypeOnTypeParam(gClass.typeParams)
        if (Random.getTrue(20) && !randomType.isPrimitiveTypeOrNullablePrimitiveTypeOrString() && !randomType.isNullable()
            && !gClass.isInterface())
            return "lateinit var ${Random.getRandomVariableName(4)}: $withTypeParams"
        var modifier = if (gClass.isAbstract() && !fromObject && Random.getTrue(60)) "abstract " else ""
        modifier += if (Random.nextBoolean()) "val" else "var"
        val isVar = modifier.contains("var")
        if (modifier.contains("abstract")) return "$modifier ${Random.getRandomVariableName(4)}: $withTypeParams"
        val defaultValue =
            if (gClass.isInterface()) ""
            else if (Random.nextBoolean() || withTypeParams != randomType.toString()) "= TODO()"
            else {
                val v = randomInstancesGenerator.generateValueOfType(randomType.makeNotNullable())
                if (v.isEmpty()) "= TODO()" else "= $v"
            }
        val definition = "${Random.getRandomVariableName(4)}: $withTypeParams "
        val p = "$definition $defaultValue"
        if (Random.getTrue(70) || gClass.isInline()) {
            val getter =
                if (gClass.isInterface()) ""
                else "get() $defaultValue"
            val setter =
                if (isVar && !gClass.isInterface()) {
                    if (fromObject || defaultValue.contains("TODO()") || Random.getTrue(30)) {
                        "set(value) = TODO()\n"
                    } else {
                        "private set\n"
                    }
                } else ""
            val d =
                if (setter.contains("private set")) {
                    defaultValue
                } else ""
            return "$modifier $definition $d\n$getter\n$setter"
        }
        return if (Random.getTrue(30) && !isVar) makePropExtension(p, modifier.contains("var"))
        else "$modifier $p"
    }

    override fun generate(): PsiElement? =
        try {
            Factory.psiFactory.createProperty(generateRandomProperty(false))
        } catch (e: Exception) {
            null
        }
}