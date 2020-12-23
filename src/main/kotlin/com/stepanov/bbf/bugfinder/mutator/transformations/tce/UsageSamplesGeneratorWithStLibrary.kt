package com.stepanov.bbf.bugfinder.mutator.transformations.tce

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.util.*
import org.jetbrains.kotlin.resolve.scopes.getDescriptorsFiltered
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.typeUtil.supertypes
import com.stepanov.bbf.reduktor.parser.PSICreator
import org.jetbrains.kotlin.backend.common.serialization.findPackage
import org.jetbrains.kotlin.descriptors.*
import org.jetbrains.kotlin.descriptors.impl.EnumEntrySyntheticClassDescriptor
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.resolve.bindingContextUtil.getAbbreviatedTypeOrType
import org.jetbrains.kotlin.resolve.descriptorUtil.classId
import org.jetbrains.kotlin.resolve.descriptorUtil.getAllSuperClassifiers
import org.jetbrains.kotlin.resolve.descriptorUtil.module
import org.jetbrains.kotlin.resolve.scopes.computeAllNames
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedCallableMemberDescriptor
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedClassDescriptor
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedPropertyDescriptor
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedSimpleFunctionDescriptor
import org.jetbrains.kotlin.types.isNullable
import org.jetbrains.kotlin.types.typeUtil.isAnyOrNullableAny
import org.jetbrains.kotlin.types.typeUtil.isTypeParameter
import kotlin.system.exitProcess

object UsageSamplesGeneratorWithStLibrary {

    val descriptorDecl: List<DeclarationDescriptor>
    val klasses: List<ClassDescriptor>

    init {
        val (psi, ctx) = PSICreator("").let { it.getPSIForText("val a: StringBuilder = StringBuilder(\"\")") to it.ctx!! }
        val kType =
            psi.getAllPSIChildrenOfType<KtProperty>().map { it.typeReference?.getAbbreviatedTypeOrType(ctx) }[0]!!
        val module = kType.constructor.declarationDescriptor!!.module
        val stringPackages = module.getSubPackagesOf(FqName("kotlin")) { true } + listOf(FqName("kotlin"))
        val packages = stringPackages.map { module.getPackage(it) }
        descriptorDecl = packages.flatMap { it.memberScope.getDescriptorsFiltered { true } }
        klasses = descriptorDecl.filterIsInstance<DeserializedClassDescriptor>()
    }

    fun generateForStandardType(type: KotlinType, needType: String): List<List<CallableDescriptor>> {
        val resForType = gen(type, needType)
        val resForSuperTypes = type.supertypes().getAllWithoutLast().flatMap { gen(it, needType) }.toList()
        val res = resForType + resForSuperTypes
        return res.removeDuplicatesBy { it.joinToString { it.name.asString() } }
    }

    fun findPackageForType(type: String): PackageFragmentDescriptor? {
        val klassDescriptor = klasses.find { it.name.asString() == type.substringBefore('<').trim() }
        return klassDescriptor?.containingDeclaration?.findPackage()
    }

    fun generateOpenClassType(onlyInterfaces: Boolean): ClassDescriptor {
        val openKlasses =
            klasses
                .filter { it.visibility.isPublicAPI && it.modality != Modality.FINAL }
                .filterNot { it.containingDeclaration.findPackage().toString().contains("js") }
                .filter { it.constructors.isEmpty() || it.constructors.any { it.visibility.isPublicAPI } }
                .filter { it.constructors.isEmpty() || !onlyInterfaces }
        return openKlasses.random()
    }

    private fun checkVisibility(decl: DeclarationDescr): Boolean =
        decl.descriptor.containingDeclaration?.let { checkVisibility(it) } ?: false


    private fun checkVisibility(decl: DeclarationDescriptor): Boolean {
        return when (decl) {
            is SimpleFunctionDescriptor -> decl.visibility.isPublicAPI
            is PropertyDescriptor -> decl.visibility.isPublicAPI
            else -> false
        }
    }

    private fun checkVisibilityAndModality(decl: DeclarationDescriptor): Boolean {
        if (!checkVisibility(decl)) return false
        return when (decl) {
            is SimpleFunctionDescriptor -> decl.modality != Modality.FINAL
            is PropertyDescriptor -> decl.modality != Modality.FINAL
            else -> false
        }
    }

    //No hashcode()
    private fun gen(
        type: KotlinType,
        needType: String,
        prefix: List<CallableDescriptor> = listOf()
    ): List<List<CallableDescriptor>> {
        if (prefix.size == maxDepth) return listOf()
        val typeParamToArg = type.constructor.parameters.zip(type.arguments)
        val ext = getExtensionFuncsFromStdLibrary(type, needType)
        val funList: MutableList<List<CallableDescriptor>> = mutableListOf()
        for (decl in ext) {
            if (!checkVisibility(decl)) continue
            val typeParamNameToRealArg =
                decl.recType!!.arguments.map { it.toString() }.zip(typeParamToArg.map { it.second.toString() }).toMap()
            val retValueType = decl.retValueType!!
            val realType = retValueType.replaceTypeArgsToTypes(typeParamNameToRealArg)
            if (realType == needType) {
                funList.add(prefix + decl.descriptor.containingDeclaration as CallableDescriptor)
            }
        }
        val derivedTypes = mutableListOf<Pair<List<CallableDescriptor>, KotlinType>>()
        for (mem in getMemberFields(type)) {
            val descriptor =
                mem as? DeserializedCallableMemberDescriptor ?: mem as? CallableMemberDescriptor ?: continue
            if (descriptor.name.asString() in blockList) continue
            if (!descriptor.visibility.isPublicAPI) continue
            if (descriptor.returnType?.toString().let { it == needType || "$it?" == needType || it == "$needType?" }) {
                funList.add(prefix + descriptor)
            }
            val anotherType = descriptor.returnType ?: continue
            derivedTypes.add(prefix + descriptor to anotherType)
        }
        derivedTypes
            .filter { it.second.toString() != type.toString() }
            .removeDuplicatesBy { it.second.toString() }
            .flatMap { gen(it.second, needType, it.first) }
            .forEach { funList.add(prefix + it) }
        return funList
    }

    private fun List<PsiElement>.createTypeCallSeq(): String =
        this.joinToString(".") {
            when (it) {
                is KtProperty -> it.name ?: ""
                else -> (it as KtNamedFunction).name ?: ""
            }
        }


    private fun getMembersToOverride1(kl: KotlinType): List<DeclarationDescriptor> {
        kl.memberScope.computeAllNames()
        return kl.memberScope.getDescriptorsFiltered { true }.toList() +
                kl.supertypesWithoutAny().flatMap { getMembersToOverride1(it) }


    }

    fun getMembersToOverride(kl: KotlinType): List<DeclarationDescriptor> {
        val fields = getMembersToOverride1(kl)
            .removeDuplicatesBy {
                if (it is FunctionDescriptor) "${it.name}${it.valueParameters.map { it.name.asString() + it.returnType.toString() }}"
                else it.name.asString()
            }
            .filter {
            it.toString().let { !it.contains("equals") && !it.contains("hashCode") && !it.contains("toString") } &&
                    checkVisibilityAndModality(it)
        }
        return fields
            .filter { it is PropertyDescriptor || it is FunctionDescriptor }
    }

    private fun KotlinType.replaceTypeArgsToTypes(map: Map<String, String>): String {
        val realType =
            when {
                isTypeParameter() -> map[this.constructor.toString()] ?: map["${this.constructor}?"] ?: this.toString()
                this.isNullable() -> "${this.constructor}?"
                else -> this.constructor.toString()
            }
        val typeParams = this.arguments.map { it.type.replaceTypeArgsToTypes(map) }
        return if (typeParams.isNotEmpty()) "$realType<${typeParams.joinToString()}>" else realType
    }

    private fun getExtensionFuncsFromStdLibrary(type: KotlinType, needType: String): List<DeclarationDescr> {
        val res = mutableListOf<DeclarationDescr>()
        for (desc in descriptorDecl) {
            val rec = when (desc) {
                is DeserializedSimpleFunctionDescriptor -> desc.dispatchReceiverParameter
                    ?: desc.extensionReceiverParameter
                is DeserializedPropertyDescriptor -> desc.dispatchReceiverParameter ?: desc.extensionReceiverParameter
                else -> null
            }
            val retValueType = (desc as? DeserializedCallableMemberDescriptor)?.returnType
            if (rec?.value?.type?.toString()?.substringBefore('<') == type.toString().substringBefore('<'))
                res.add(DeclarationDescr(rec, rec.returnType, retValueType))
        }
        return res
    }

    private fun getMemberFields(type: KotlinType): List<DeclarationDescriptor> =
        type.memberScope.getDescriptorsFiltered { true }
            .filter { !it.toString().contains("private") }

    private fun createFunDefinitionFromDeclarationDescriptor(
        typeParamsToArgs: Map<String, String>,
        decl: DeclarationDescriptor
    ): KtNamedFunction? {
        val funDecl =
            decl as? CallableMemberDescriptor ?: return Factory.psiFactory.createFunction("fun a()")
        val typeParam =
            if (funDecl.typeParameters.isNotEmpty()) {
                val withUpperBounds = funDecl.typeParameters.map {
                    val type = it.defaultType.replaceTypeArgsToTypes(typeParamsToArgs)
                    val upperBounds = if (it.upperBounds.size == 1 && it.upperBounds[0].isAnyOrNullableAny())
                        ""
                    else
                        it.upperBounds
                            .map { it.replaceTypeArgsToTypes(typeParamsToArgs) }
                            .filter { it.isNotEmpty() }
                            .joinToString()
                    if (upperBounds.isEmpty()) type else "$type : $upperBounds"
                }.joinToString()
                "<$withUpperBounds>"
            } else ""
        val rec = funDecl.dispatchReceiverParameter ?: funDecl.extensionReceiverParameter
        val recWithoutTypeParam = rec?.value?.type?.replaceTypeArgsToTypes(typeParamsToArgs) ?: rec.toString()
        val params = handleParams(decl.valueParameters, typeParamsToArgs)
        val func = "fun $typeParam $recWithoutTypeParam.${decl.name}($params): ${decl.returnType}{}"
        return try {
            Factory.psiFactory.createFunction(func)
        } catch (e: Exception) {
            println("cant create fun ${func}")
            System.exit(1)
            null
        }
    }

    fun searchForFunWithRetType(type: KotlinType): List<SimpleFunctionDescriptor> {
        val implementations =
            findImplementationOf(type, false)
                .removeDuplicatesBy { it.name }
                .map { it.name.asString().substringBefore('<') }.toMutableList()
        implementations.add(type.toString().substringBefore('<'))
        val funDescriptors =
            descriptorDecl.filterIsInstance<DeserializedSimpleFunctionDescriptor>()
        return funDescriptors.asSequence()
            .filter { it.returnType?.toString()?.substringBefore('<') in implementations }
            .filter { it.returnType?.let { checkTypeParams(type, it) } ?: false }
            .filter {
                it.extensionReceiverParameter?.value?.type?.let { !it.compareStringRepOfTypesWithoutTypeParams(type) }
                    ?: true
            }
            .filter { it.visibility.isPublicAPI }
            .toList()
    }

    private fun checkTypeParams(toType: KotlinType, retValueType: KotlinType): Boolean {
        if (retValueType.isTypeParameter()) return true
        if (retValueType.arguments.size != toType.arguments.size) return false
        if (retValueType.arguments.isEmpty() && toType.arguments.isEmpty()) {
            return retValueType.constructor.toString() == toType.constructor.toString()
        }
        for (i in retValueType.arguments.indices) {
            val retValueTypeParam = retValueType.arguments[i]
            val typeParam = toType.arguments[i]
            if (retValueTypeParam.type.isTypeParameter()) continue
            if (typeParam.type.constructor.toString() != retValueTypeParam.type.constructor.toString()) return false
            val argCompRes = typeParam.type.arguments.zip(retValueTypeParam.type.arguments)
                .map { checkTypeParams(it.first.type, it.second.type) }
            if (argCompRes.any { !it }) return false
        }
        return true
    }

    fun findEnumMembers(type: KotlinType): List<KotlinType> {
        val klass = klasses
            .find {
                if (it.declaredTypeParameters.isEmpty()) {
                    it.getAllSuperClassifiers().any { it.name.asString() == type.getNameWithoutError() }
                } else {
                    it.getAllSuperClassifiers().any { it.name.asString() == type.toString().substringBefore('<') }
                }
            } ?: return listOf()
        return klass.unsubstitutedMemberScope.getContributedDescriptors { true }
            .filterIsInstance<EnumEntrySyntheticClassDescriptor>()
            .map { it.defaultType }
    }

    fun findImplementationOf(type: KotlinType, withoutInterfaces: Boolean = true): List<ClassDescriptor> =
        klasses
            .asSequence()
            .filter {
//                if (it.declaredTypeParameters.isEmpty()) {
//                    it.getAllSuperClassifiers().any { it.name.asString() == type.getNameWithoutError() }
//                } else {
//                    it.getAllSuperClassifiers().any { it.name.asString() == type.toString().substringBefore('<') }
//                }
                it.getAllSuperClassifiers().any { it.name.asString() == type.toString().substringBefore('<') }
            }
            .filter { ((it.constructors.isNotEmpty() /*&& it.modality != Modality.ABSTRACT*/) || !withoutInterfaces) && it.visibility.isPublicAPI }
            .filter {
                if (it.constructors.isNotEmpty() && !it.defaultType.isPrimitiveTypeOrNullablePrimitiveTypeOrString()) {
                    it.constructors.any { it.visibility.isPublicAPI && it.visibility.name != "protected" }
                } else true
            }
            .toList()

    fun calcImports(file: KtFile): List<String> {
        val klassesForImport =
            klasses.filter { it.classId?.packageFqName?.asString() != "kotlin" }
        val types = file.getAllPSIChildrenOfType<KtTypeReference>().map { it.text.trim() }.toSet()
        return types.mapNotNull { t ->
            klassesForImport.find { it.name.asString() == t.substringBefore('<') }.classId?.asString()
                ?.replace('/', '.')
        }
    }

    fun findImplementationFromFile(type: KotlinType, withoutInterfaces: Boolean = true): List<ClassDescriptor> =
        type.constructor.declarationDescriptor!!
            .findPackage().getMemberScope()
            .getDescriptorsFiltered { true }.filterIsInstance<ClassDescriptor>()
            .filter { it.name.asString() != type.toString().substringBefore('<') }
            .filter { it.getAllSuperClassifiers().any { it.name.asString() == type.toString().substringBefore('<') } }

    fun isImplementation(type: KotlinType, potImpl: KotlinType?): Boolean {
        if (potImpl == null) return true
        (findImplementationFromFile(type) + findImplementationOf(type))
            .find { it.name.asString() == potImpl.constructor.toString() }
            ?.let { return true } ?: return false
    }

    fun getDeclDescriptorOf(klassName: String): DeclarationDescriptor =
        descriptorDecl.mapNotNull { it as? DeserializedClassDescriptor }.first { it.name.asString() == klassName }

    private fun KotlinType.compareStringRepOfTypesWithoutTypeParams(other: KotlinType) =
        this.toString().substringBefore('<') == other.toString().substringBefore("<")

    private fun handleParams(valParamDesc: List<ValueParameterDescriptor>, typeParamsToArgs: Map<String, String>) =
        valParamDesc.map { "${it.name}: ${it.type.replaceTypeArgsToTypes(typeParamsToArgs)}" }.joinToString()

    private val maxDepth = 2
    private val blockList = listOf("hashCode", "toString")
}

data class DeclarationDescr(
    val descriptor: DeclarationDescriptor,
    val recType: KotlinType?,
    val retValueType: KotlinType?
)