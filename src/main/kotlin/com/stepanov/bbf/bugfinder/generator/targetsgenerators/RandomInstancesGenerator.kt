package com.stepanov.bbf.bugfinder.generator.targetsgenerators

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.util.*
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.typeGenerators.KotlinTypeCreator.recreateType
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.typeGenerators.RandomTypeGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.StdLibraryGenerator
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.util.getAllChildren
import org.apache.log4j.Logger
import org.jetbrains.kotlin.builtins.isExtensionFunctionType
import org.jetbrains.kotlin.builtins.isFunctionOrSuspendFunctionType
import org.jetbrains.kotlin.cfg.getDeclarationDescriptorIncludingConstructors
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.descriptors.SimpleFunctionDescriptor
import org.jetbrains.kotlin.descriptors.impl.EnumEntrySyntheticClassDescriptor
import org.jetbrains.kotlin.ir.expressions.typeParametersCount
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.bindingContextUtil.getAbbreviatedTypeOrType
import org.jetbrains.kotlin.resolve.descriptorUtil.parentsWithSelf
import org.jetbrains.kotlin.resolve.scopes.getDescriptorsFiltered
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedClassDescriptor
import org.jetbrains.kotlin.types.*
import org.jetbrains.kotlin.types.typeUtil.*
import kotlin.random.Random
import kotlin.system.exitProcess

//TODO add project
open class RandomInstancesGenerator(private val file: KtFile, private var ctx: BindingContext?) {

    constructor(file: KtFile) : this(file, PSICreator.analyze(file))

    //TODO rewrite this someday
    fun generateTopLevelFunctionCall(
        function: KtNamedFunction,
        typeParamsToRealTypeParams: Map<String, String> = mapOf(),
        withTypeParams: Boolean = true,
        depth: Int = 0
    ): Pair<KtExpression, List<KtParameter>>? {
        var lTypeParamsToRealTypeParams = typeParamsToRealTypeParams
        if (function.name == null) return null
        if (function.receiverTypeReference == null && function.typeParameters.isEmpty() && function.valueParameters.isEmpty()) {
            return Factory.psiFactory.createExpression("${function.name}()") to listOf()
        }
        ctx = PSICreator.analyze(file) ?: return null
        log.debug("GENERATING CALL OF ${function.text}")
        val addedFun =
            if (!file.getAllChildren().contains(function)) {
                file.addAtTheEnd(function) as KtNamedFunction
            } else {
                function
            }
        var func = function.copy() as KtNamedFunction
        if (withTypeParams && func.typeParameterList != null) {
            if (typeParamsToRealTypeParams.isEmpty())
                lTypeParamsToRealTypeParams = generateRandomTypeParams(
                    function.typeParameters,
                    func.valueParameters,
                    func.bodyExpression?.let { func.text.substringBefore(it.text) } ?: func.text,
                    ctx!!,
                    func = func
                )?.second ?: mapOf()
        }
        log.debug("WITHOUT TYPE PARAMS = ${func.text}")
        addedFun.replaceThis(func)
        val creator = PSICreator
        val newFile = creator.getPSIForText(file.text)
        val ctx = creator.analyze(newFile) ?: return null
        func.replaceThis(addedFun)
        if (addedFun != function) addedFun.delete()
        func = newFile.getAllChildren().find { it.text.trim() == func.text.trim() } as? KtNamedFunction ?: return null
        val generatedParams = func.valueParameters
            .map { param ->
                if (param.typeReference == null) return null
                param.typeReference?.getAbbreviatedTypeOrType(ctx)?.makeNotNullable()?.let {
                    if (it.isError) return null
                    if ("$it" != param.typeReference!!.text.trim())
                        randomTypeGenerator.generateType(param.typeReference!!.text)
                    else it
                }
            }
            .map {
                it?.let {
                    generateValueOfType(it, depth + 1).let {
                        if (it.trim().isEmpty()) return null else it
                    }
                } ?: return null
            }
            .joinToString(", ")
        val generatedReciever =
            func.receiverTypeReference?.getAbbreviatedTypeOrType(ctx)?.let {
                val valueOfType = generateValueOfType(it, depth + 1)
                if (valueOfType.isNotEmpty())
                    "$valueOfType."
                else ""
            } ?: ""
        val typeParams =
            func.typeParameters.map { lTypeParamsToRealTypeParams.getOrDefault(it.name!!, it.name!!) }
                .let { if (it.isNotEmpty()) it.joinToString(prefix = "<", postfix = ">") else "" }
        log.debug("Trying to generate $generatedReciever${func.name}$typeParams($generatedParams)")
        try {
            val expr =
                Factory.psiFactory.createExpressionIfPossible("$generatedReciever${func.name}$typeParams($generatedParams)")
                    ?: return null
            log.debug("GENERATED = ${expr.text}")
            return expr to func.valueParameters
        } catch (e: Exception) {
            return null
        }
    }

    //Type parameters will be replaced by randomly generated types
    fun generateFunctionCall(desc: FunctionDescriptor): PsiElement? {
        //val funDef = desc.toString().substringAfter("fun").substringBefore(" defined").split(" = ...").joinToString(" ")
        val name = desc.name.asString()
        val tp =
            if (desc.typeParameters.isEmpty()) ""
            else desc.typeParameters.joinToString(", ", prefix = "<", postfix = ">") { it.name.asString() }
        val valueParams =
            if (desc.valueParameters.isEmpty()) ""
            else desc.valueParameters.joinToString(
                ", ",
                "(",
                ")"
            ) { "${it.name.asString()}: ${it.returnType?.getNameWithoutError() ?: "Any"}" }
        val returnType = desc.returnType?.getNameWithoutError() ?: "Any"
        val funDef = "$tp $name$valueParams: $returnType"
        val ktFun = Factory.psiFactory.createFunction("fun $funDef")
        val res = generateTopLevelFunctionCall(ktFun)
        return res?.first
    }

    private fun generateRandomTypeParams(
        typeParameters: List<KtTypeParameter>,
        valueParameters: List<KtParameter>,
        name: String,
        ctx: BindingContext,
        typeProjections: List<TypeProjection> = listOf(),
        func: KtNamedFunction? = null
    ): Pair<List<KtTypeReference>, Map<String, String>>? {
        randomTypeGenerator.setFileAndContext(file, ctx)
        if (typeParameters.isEmpty()) return null
        val generatedTypeParams =
            (if (func != null) typeParameters.map {
                randomTypeGenerator.generateRandomTypeWithCtx(
                    it.extendsBound?.getAbbreviatedTypeOrType(
                        ctx
                    )
                )
            }.map { it?.asTypeProjection() ?: return null }
            else randomTypeGenerator.getTypeFromFile(name)?.arguments)
                ?: return null
        //require(generatedTypeParams != null) { println("cant generate type params") }
        val generatedTypeParamsPsi =
            if (typeProjections.isEmpty())
                generatedTypeParams.map { Factory.psiFactory.createType(it.type.toString()) }
            else
                generatedTypeParams.zip(typeProjections)
                    .map { if (it.second.type.isTypeParameter()) it.first else it.second }
                    .map { Factory.psiFactory.createType(it.type.toString()) }
//        val generatedTypeParamsPsi =
//            generatedTypeParams.arguments.map { Factory.psiFactory.createType(it.type.toString()) }
        val strTypeParamToRandomType = mutableMapOf<String, String>()
        typeParameters.zip(generatedTypeParamsPsi)
            .forEach { strTypeParamToRandomType[it.first.name!!] = it.second.text }
        valueParameters.forEach { param ->
            param.typeReference?.getAllPSIChildrenOfType<KtReferenceExpression>()?.map { typeRef ->
                strTypeParamToRandomType[typeRef.text]?.let { typeRef.replaceThis(Factory.psiFactory.createType(it)) }
            }
        }
        if (func != null) {
            listOfNotNull(func.typeReference, func.receiverTypeReference).forEach { param ->
                param.getAllPSIChildrenOfType<KtReferenceExpression>().map { typeRef ->
                    strTypeParamToRandomType[typeRef.text]?.let {
                        typeRef.replaceThis(
                            Factory.psiFactory.createType(
                                it
                            )
                        )
                    }
                }
            }
        }
        return generatedTypeParamsPsi to strTypeParamToRandomType
    }

    fun generateRandomInstanceOfClass(ktClass: KtClassOrObject) =
        classInstanceGenerator.generateRandomInstanceOfUserClass(ktClass)

    fun generateValueOfTypeAsExpression(
        t: KotlinType,
        depth: Int = 0,
        onlyImpl: Boolean = false,
        withoutParams: Boolean = false,
        nullIsPossible: Boolean = true
    ): KtExpression? =
        try {
            Factory.psiFactory.createExpression(generateValueOfType(t, depth, onlyImpl, withoutParams, nullIsPossible))
        } catch (e: Error) {
            null
        } catch (e: Exception) {
            null
        }

    fun tryToGenerateValueOfType(
        t: KotlinType,
        numberOfTimes: Int,
        depth: Int = 0,
        onlyImpl: Boolean = false,
        withoutParams: Boolean = false,
        nullIsPossible: Boolean = true
    ): String {
        repeat(numberOfTimes) {
            generateValueOfType(t, depth, onlyImpl, withoutParams, nullIsPossible).let {
                if (it.isNotEmpty()) return it
            }
        }
        return ""
    }

    fun generateValueOfType(
        t: KotlinType,
        depth: Int = 0,
        onlyImpl: Boolean = false,
        withoutParams: Boolean = false,
        nullIsPossible: Boolean = true
    ): String {
        if (t.isUnit()) return "{}"
        if (t.isNullable() && nullIsPossible && Random.getTrue(5)) return "null"
        val type =
            if (t is FlexibleType) {
                t.upperBound.makeNotNullable()
            } else {
                t.makeNotNullable()
            }
        log.debug("generating value of type = $type ${type.isPrimitiveTypeOrNullablePrimitiveTypeOrString()} depth = $depth")
        if (depth > MAGIC_CONST) return ""
        //TODO deal with Any KReflection
        //if (type.isAnyOrNullableAny()) return generateDefValuesAsString(generateRandomType())
        if (type.isAnyOrNullableAny()) return generateDefValuesAsString("String")
        if (type.isError || type.arguments.flatten<TypeProjection>().any { it.type.isError }) {
            val recreatedType = recreateType(fileCopy, type)
            log.debug("RECREATED ERROR TYPE = $recreatedType")
            if (recreatedType == null || recreatedType.isError) {
                val name = (type as? UnresolvedType)?.presentableName ?: return ""
                return generateDefValuesAsString(name)
            }
            if (recreatedType.arguments.flatten<TypeProjection>().any { it.type.isError }) return ""
            return generateValueOfType(recreatedType, depth)
        }
        if (type.constructor.declarationDescriptor is ClassDescriptor) {
            val classDescriptor = type.constructor.declarationDescriptor as ClassDescriptor
            val className = classDescriptor.parentsWithSelf.toList()
                .filterIsInstance<ClassDescriptor>()
                .reversed()
                .joinToString(".") { it.name.asString().trim() }
            file.getClassWithName(className)?.let {
                val res = classInstanceGenerator.generateRandomInstanceOfUserClass(type, depth + 1)
                return res?.first?.text ?: ""
            }
        }
        if (type.constructor.declarationDescriptor.let { it is ClassDescriptor && it.name.asString() == "Enum" }) {
            val enumClassesFromFile =
                file.getAllPSIChildrenOfType<KtClass>()
                    .filter { it.isEnum() }
                    .mapNotNull { ctx?.let { ctx -> it.getDeclarationDescriptorIncludingConstructors(ctx) as? ClassDescriptor } }
            return if (enumClassesFromFile.isNotEmpty() && Random.getTrue(60)) {
                classInstanceGenerator
                    .generateRandomInstanceOfUserClass(enumClassesFromFile.random().defaultType)
                    ?.first
                    ?.text
                    ?: ""
            } else {
                val enumFromStdLibrary = StdLibraryGenerator.findEnumClasses().random()
                val randomEntry = enumFromStdLibrary.unsubstitutedMemberScope
                    .getDescriptorsFiltered { true }
                    .filterIsInstance<EnumEntrySyntheticClassDescriptor>().random()
                    .name.asString()
                enumFromStdLibrary.name.asString() + "." + randomEntry
            }
        }
        if (type.isEnum()) {
            return StdLibraryGenerator.findEnumMembers(type).randomOrNull()?.toString()
                ?: ""
        }
        if (type.isPrimitiveTypeOrNullablePrimitiveTypeOrString())
            generateDefValuesAsString(type.toString()).let { if (it.isNotEmpty()) return it }
        if (type.isKType()) return RandomReflectionInstanceGenerator(file, type).generateReflection()
        if (type.isFunctionOrSuspendFunctionType) {
            if (type.arguments.isEmpty()) return ""
            val args =
                if (type.isExtensionFunctionType) type.arguments.getAllWithout(0).getAllWithoutLast()
                else type.arguments.getAllWithoutLast()
            val prefix = if (args.isEmpty()) ""
            else args
                .mapIndexed { i, t -> "${'a' + i}: ${t.type.toString().substringAfter("] ")}" }
                .joinToString() + " ->"
            val generatedValueOfLastTypeArg = generateValueOfType(type.arguments.last().type, depth + 1)
            if (generatedValueOfLastTypeArg.isEmpty()) return ""
            return "{$prefix ${generatedValueOfLastTypeArg}}"
        }
        return searchForImplementation(type, depth + 1, onlyImpl, withoutParams)
    }

    fun searchForImplementation(
        type: KotlinType,
        depth: Int,
        onlyImpl: Boolean = false,
        withoutParams: Boolean = false
    ): String {
        var funcs = StdLibraryGenerator.searchForFunWithRetType(type.makeNotNullable())
        if (type.getAllTypeParams().any { it.type.isInterface() })
            funcs = funcs.filter { it.valueParameters.all { !it.type.isTypeParameter() } }
        funcs =
            funcs
                .filterNot { it.annotations.any { it.fqName?.asString()?.contains("Deprecated") ?: false } }
                .sortedBy { it.valueParameters.size }
        //.takeLast(1) //TODO!!!
        val implementations = StdLibraryGenerator.findImplementationOf(type.makeNotNullable())
        if (funcs.isEmpty() && implementations.isEmpty()) return ""
        //TODO fix this
        if (type.toString().startsWith("Sequence")) funcs = listOf(funcs[1], funcs.last())
        val prob = if (funcs.size > implementations.size) 75 else 25
        val randomFunc =
            if (depth > 2 && Random.getTrue(70) && funcs.isNotEmpty()) {
                funcs.filter { it.valueParameters.size - funcs[0].valueParameters.size <= 1 }.randomOrNull()
            } else if (depth > 5 && funcs.isNotEmpty()) {
                //Choosing simplest implementation
                val filteredFuncs = funcs
                    .filter { it.extensionReceiverParameter == null }
                    .filter { it.valueParameters.size == funcs[0].valueParameters.size }
                    .sortedBy { it.typeParametersCount }
                    .randomOrNull()
                filteredFuncs
//                filteredFuncs
//                    .filter { it.typeParametersCount == filteredFuncs.first().typeParametersCount }
//                    .randomOrNull()
            } else {
                funcs.randomOrNull()
            }
        val el =
            if (CompilerArgs.isABICheckMode) {
                val sortedFuncs = funcs.sortedBy { it.valueParameters.size }
                when {
                    onlyImpl -> implementations.randomOrNull()
                    Random.getTrue(prob) -> sortedFuncs.firstOrNull() ?: implementations.randomOrNull()
                    else -> implementations.randomOrNull() ?: sortedFuncs.firstOrNull()
                }
            } else {
                when {
                    onlyImpl -> implementations.randomOrNull()
                    Random.getTrue(prob) -> randomFunc ?: implementations.randomOrNull()
                    else -> implementations.randomOrNull() ?: randomFunc
                }
            }
        if (el is ClassDescriptor && el.defaultType.isPrimitiveTypeOrNullablePrimitiveTypeOrString())
            return generateDefValuesAsString(el.name.asString())
        ctx?.let { randomTypeGenerator.setFileAndContext(file, it) } ?: return ""
        val (resFunDescriptor, typeParams) = when (el) {
            is SimpleFunctionDescriptor -> TypeParamsReplacer.throwTypeParams(type, el)
            is ClassDescriptor -> TypeParamsReplacer.throwTypeParams(type, el, withoutParams)
            else -> return ""
        } ?: return ""
        val invocation =
            funInvocationGenerator.generateFunctionCallWithoutTypeParameters(resFunDescriptor, typeParams, depth)
        return invocation?.text ?: ""
    }

    private val fileCopy = file.copy() as KtFile

    //private var ctx: BindingContext? = PSICreator.analyze(file)
    internal val classInstanceGenerator = ClassInstanceGenerator(file)
    internal val funInvocationGenerator = FunInvocationGenerator(file)
    val randomTypeGenerator = RandomTypeGenerator
    private val MAGIC_CONST = 15
    private val log = Logger.getLogger("mutatorLogger")

}