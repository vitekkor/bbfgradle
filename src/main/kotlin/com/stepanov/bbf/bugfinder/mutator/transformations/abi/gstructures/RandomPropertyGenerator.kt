package com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.DSGenerator
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.bugfinder.util.getRandomVariableName
import org.jetbrains.kotlin.backend.common.serialization.findPackage
import org.jetbrains.kotlin.descriptors.*
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.DescriptorUtils
import org.jetbrains.kotlin.resolve.bindingContextUtil.getAbbreviatedTypeOrType
import org.jetbrains.kotlin.resolve.descriptorUtil.getAllSuperClassifiers
import org.jetbrains.kotlin.resolve.descriptorUtil.getAllSuperclassesWithoutAny
import org.jetbrains.kotlin.resolve.scopes.getDescriptorsFiltered
import kotlin.random.Random
import kotlin.system.exitProcess

class RandomPropertyGenerator(
    private val file: KtFile,
    private val gClass: GClass?,
    private val ctx: BindingContext
) : DSGenerator(file, ctx) {

    fun generateInterestingProperty(): String {
        //Get classes which are supertypes of us
        if (gClass == null) return ""
        val klasses = file.getAllPSIChildrenOfType<KtTypeReference>()
            .mapNotNull { it.getAbbreviatedTypeOrType(ctx)?.constructor?.declarationDescriptor?.findPackage() }
            .first { it.fqName == file.packageFqName }
            .getMemberScope().getDescriptorsFiltered { true }
            .filterIsInstance<ClassDescriptor>()
        val children = klasses.filter { it.getAllSuperClassifiers().any { it.name.asString() == gClass.name } }
        val child = children.last()
        val overridings =
            child.defaultType.memberScope.getDescriptorsFiltered { true }
                .filterIsInstance<CallableMemberDescriptor>()
                .filter { DescriptorUtils.isOverride(it) }
        val randomOverride = overridings.random()
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
                val varOrVal = if (randomOverride.isVar) "var" else "val"
                if (gClass.isInterface() || gClass.isAbstract() && Random.nextBoolean())
                    "public abstract $varOrVal ${
                        randomOverride.toString().substringAfter(varOrVal).substringBefore("defined ")
                    }"
                else "public open $varOrVal ${
                    randomOverride.toString().substringAfter(varOrVal).substringBefore("defined ")
                } = TODO()"
            } else ""
        return overrideToStr
    }

    fun generateRandomProperty(): String {
        generateInterestingProperty()
        val randomType = randomTypeGenerator.generateRandomTypeWithCtx() ?: return ""
        val modifier = if (Random.nextBoolean()) "val" else "var"
        val defaultValue =
            if (Random.nextBoolean()) ""
            else {
                val v = randomInstancesGenerator.generateValueOfType(randomType)
                if (v.isEmpty()) "" else "= $v"
            }
        println("$modifier ${Random.getRandomVariableName(4)}: $randomType $defaultValue")
        exitProcess(0)
        return "$modifier ${Random.getRandomVariableName(4)}: $randomType $defaultValue"
    }
}