package com.stepanov.bbf.bugfinder.mutator.transformations.constructor

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.util.*
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.util.getAllChildren
import org.jetbrains.kotlin.builtins.KotlinBuiltIns
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.SimpleFunctionDescriptor
import org.jetbrains.kotlin.js.descriptorUtils.nameIfStandardType
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.getValueParameters
import org.jetbrains.kotlin.psi.psiUtil.parents
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.bindingContextUtil.getAbbreviatedTypeOrType
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.UnresolvedType
import org.jetbrains.kotlin.types.isCustomTypeVariable
import org.jetbrains.kotlin.types.isError
import org.jetbrains.kotlin.types.typeUtil.isAnyOrNullableAny
import org.jetbrains.kotlin.types.typeUtil.isInterface
import org.jetbrains.kotlin.types.typeUtil.isUnit
import org.jetbrains.kotlin.types.typeUtil.supertypes
import kotlin.random.Random

class RandomInstancesGenerator(private val file: KtFile) {

    fun generateTopLevelFunctionCall(function: KtNamedFunction): Pair<KtCallExpression, List<KtParameter>>? {
        val addedFun =
            if (!file.getAllChildren().contains(function)) {
                file.addToTheEnd(function) as KtNamedFunction
            } else {
                function
            }
        var func = function.copy() as KtNamedFunction
        if (func.typeParameterList != null) {
            generateRandomTypeParams(func.typeParameters, func.valueParameters)
        }
        println("WITHOUT TYPE PARAMS = ${func.text}")
        addedFun.replaceThis(func)
        val creator = PSICreator("")
        val newFile = creator.getPSIForText(file.text)
        val ctx = creator.ctx!!
        func.replaceThis(addedFun)
        if (addedFun != function) addedFun.delete()
        func = newFile.getAllChildren().find { it.text == func.text }!! as KtNamedFunction
        val generatedParams = func.valueParameters
            .map { it.typeReference?.getAbbreviatedTypeOrType(ctx) }
            .map { it?.let { generateValueOfType(it) } ?: return null }
            .joinToString(", ")
        val expr = Factory.psiFactory.createExpressionIfPossible("${func.name}($generatedParams)") as? KtCallExpression
            ?: return null
        return expr to func.valueParameters
    }

    fun generateRandomInstanceOfClass(kl: KtClassOrObject): PsiElement? {
        if (kl.name == null || kl.isAnnotation()) return null
        if (kl is KtObjectDeclaration) {
            return Factory.psiFactory.createExpression(kl.prefix + kl.name)
        }
        //IF INTERFACE FIND IMPLEMENTATION CLASS
        if (kl is KtClass && kl.isInterface()) {
            return findImplementationAndGenerateInstance(kl)
        }
        if (kl.primaryConstructor == null) return Factory.psiFactory.createExpression("${kl.name}()")
        //Case of generating instance of children klass if it exists
        if (Random.nextBoolean()) {
            findImplementationAndGenerateInstance(kl)?.let { return it }
        }
        val klass = kl.copy() as KtClassOrObject
        kl.replaceThis(klass)
        ctx = PSICreator.analyze(file)!!
        val generatedTypeParams =
            generateRandomTypeParams(klass.typeParameters, klass.getValueParameters()) ?: return null
        val listOfParams = klass.allConstructors.map { it.valueParameters }
        val res = mutableListOf<String>()
        for (param in /*listOfParams.random()*/ listOfParams[0]) {
            ctx = PSICreator.analyze(file)!!
            require(param.typeReference != null)
            val realType = param.typeReference!!.getAbbreviatedTypeOrType(ctx) ?: return null
            if (realType.toString().contains("${klass.name}")) return null
            val instance = generateValueOfType(realType)
            res.add(instance)
        }
        val constructor =
            if (klass.typeParameters.size == 0)
                klass.name
            else
                "${klass.name}<${generatedTypeParams.joinToString { it.text }}>"
        val r = Factory.psiFactory.createExpression("${klass.prefix + constructor}(${res.joinToString()})")
        klass.replaceThis(kl)
        return r
    }

    private fun generateRandomTypeParams(
        typeParameters: List<KtTypeParameter>,
        valueParameters: List<KtParameter>
    ): List<KtTypeReference>? {
        val generatedTypeParams = typeParameters.map {
            val paramBoundType = it.extendsBound?.getAbbreviatedTypeOrType(ctx)
            if (paramBoundType == null) {
                val generated = generateRandomType()
                typeParamToRandomType[it.name!!] = generated
                generated
            } else {
                val generated = generateTypeWithBounds(it.name!!, paramBoundType)
                typeParamToRandomType[it.name!!] = generated
                generated
            }
//            //TODO HANDLE UPPER BOUNDS FOR USER TYPES
//            if (Random.nextBoolean())
//                file.getAllPSIChildrenOfType<KtClassOrObject>()
//                    .filter { it.name != null }
//                    .randomOrNull()
//                    ?.name ?: generateRandomType(paramBoundType)
//            else generateRandomType(paramBoundType)
        }.map {
            var finalType = it
            typeParamToRandomType.entries.sortedByDescending { it.key.length }
                .forEach { (s, s2) ->
                    finalType = finalType.splitWithoutRemoving(Regex("""[<>]"""))
                        .filter { it.isNotEmpty() }
                        .map { if (it == s) s2 else it }
                        .joinToString(separator = "")
                }
            if (finalType.contains("ERROR")) return null
            Factory.psiFactory.createType(finalType)
        }
        typeParamToRandomType.clear()
        typeParameters.zip(generatedTypeParams).forEach { typeParamToRandomType[it.first.name!!] = it.second.text }
        valueParameters.forEach { param ->
            param.typeReference?.getAllPSIChildrenOfType<KtReferenceExpression>()?.map { typeRef ->
                typeParamToRandomType[typeRef.text]?.let { typeRef.replaceThis(Factory.psiFactory.createExpression(it)) }
            }
        }
        return generatedTypeParams
    }

    private val KtClassOrObject.prefix: String
        get() = this.parents
            .filter { it is KtClassOrObject && it.name != null }
            .joinToString(".", postfix = ".") { (it as KtClassOrObject).name!! }
            .let { if (it == ".") "" else it }

    private fun generateTypeWithBounds(typeParamName: String, type: KotlinType): String =
        StringBuilder().apply {
            if (type.toString() == typeParamName) append(generateRandomType())
            else {
                val firstGenerated = generateRandomType(type)
                append(firstGenerated)
                if (containers.contains(firstGenerated)) {
                    type.arguments.let { args ->
                        if (args.isNotEmpty()) {
                            append("<")
                            append(args.map {
                                generateTypeWithBounds(typeParamName, it.type)
                            }.joinToString(separator = " "))
                            append(">")
                        }
                    }
                }
            }
        }.toString()


    private fun findImplementationAndGenerateInstance(kl: KtClassOrObject): PsiElement? =
        file.getAllPSIChildrenOfType<KtClassOrObject>
        { it.superTypeListEntries.any { it.typeReference?.text?.substringBefore('<') == kl.name } }
            .randomOrNull()
            ?.let {
                if (it.primaryConstructor?.valueParameters?.any { it.typeReference?.text == kl.name } == true) return null
                generateRandomInstanceOfClass(it)
            }


    fun generateValueOfType(type: String): String {
        try {
            val propWithType =
                Factory.psiFactory.createProperty("lateinit var ${Random.getRandomVariableName()}: $type")
            val addedProp = file.addToTheEnd(propWithType) as KtProperty
            val ctx = PSICreator.analyze(file)!!
            val kotlinType = addedProp.typeReference!!.getAbbreviatedTypeOrType(ctx)
            addedProp.delete()
            kotlinType?.let { return generateValueOfType(kotlinType) } ?: return ""
        } catch (e: Exception) {
            return ""
        }
    }

    fun generateValueOfType(type: KotlinType): String {
        println("generating value of type = $type ${type.isPrimitiveTypeOrNullablePrimitiveTypeOrString()}")
        //TODO deal with Any
        //if (type.isAnyOrNullableAny()) return generateDefValuesAsString(generateRandomType())
        if (type.isAnyOrNullableAny()) return generateDefValuesAsString("String")
        if (type.isError) {
            val recreatedType = recreateType(type)
            if (recreatedType == null || recreatedType.isError) {
                val name = (type as? UnresolvedType)?.presentableName ?: return ""
                return generateDefValuesAsString(name)
            }
            return generateValueOfType(recreatedType)
        }
        file.getClassOfName(type.constructor.toString())?.let {
            val newKlWithGenTypeParams = recreateKlass(it, type) ?: return ""
            it.replaceThis(newKlWithGenTypeParams)
            val res = generateRandomInstanceOfClass(newKlWithGenTypeParams)
            newKlWithGenTypeParams.replaceThis(it)
            return res?.text ?: ""
        }
        if (type.isPrimitiveTypeOrNullablePrimitiveTypeOrString())
            generateDefValuesAsString(type.toString()).let { if (it.isNotEmpty()) return it }
        if (type.constructor.toString().let { it.startsWith("Function") || it.startsWith("KFunction") }) {
            if (type.arguments.isEmpty()) return ""
            return "{${generateValueOfType(type.arguments.last().type)}}"
        }
        return searchForImplementation(type)
        System.exit(0)
        val constructor = "${type.constructor.toString().decapitalize()}Of("
        if (type.supertypes().any { it.toString().contains("Iterable") } && type.arguments.isNotEmpty()) {
            val res = mutableListOf<String>()
            repeat(amount) {
                res.add(generateValueOfType(type.arguments.first().type))
            }
            return "$constructor${res.joinToString()})"
        }
        if (type.constructor.toString().let { it == "Map" || it == "Pair" }) {
            val res = mutableListOf<Pair<String, String>>()
            repeat(amount) {
                res.add(generateValueOfType(type.arguments.first().type) to generateValueOfType(type.arguments.last().type))
            }
            return "${constructor}${res.joinToString { "${it.first} to ${it.second}" }})"
        }
        if (type.constructor.toString().let { it.startsWith("Function") || it.startsWith("KFunction") }) {
            if (type.arguments.isEmpty()) return ""
            return "{${generateValueOfType(type.arguments.last().type)}}"
        }
        if (type.constructor.toString().let { it.startsWith("KProperty") || it.startsWith("KMutableProperty") }) {
            //TODO
            return type.arguments.joinToString("::") { it.type.toString() }
        }
        if (type.toString().startsWith("Iterable")) {
            //TODO make more diverse
//            val classDescriptors =
//                UsageSamplesGeneratorWithStLibrary.descriptorDecl.filter { it is DeserializedClassDescriptor }
//            val a = classDescriptors
//                .map { it as DeserializedClassDescriptor }
//                .asSequence()
//                .filter { it.getAllSuperClassifiers().any { it.name.asString() == "Iterable" } }
//                .filter { it.hasPrimaryConstructor() }
//                .filter {
//                    it.constructors.first().let { it.visibility.isPublicAPI && it.visibility.name != "protected" }
//                }
//                .toList()
//            val constructor = a[9].constructors.toList()[2]
//            val typeParams = constructor.typeParameters.joinToString { it.name.asString() }
//            val name = constructor.constructedClass.name
//            val valueParams = constructor.valueParameters.joinToString { "${it.name}: ${it.type.toString()}" }
//            println("fun <$typeParams> $name($valueParams) = TODO()")
//            println(constructor)
//            System.exit(0)
            return "arrayListOf(${generateValueOfType(type.arguments.first().type)})"
        }
        if (type.toString().startsWith("Sequence")) {
            if (type.arguments.isEmpty()) return ""
            return "sequenceOf(${generateValueOfType(type.arguments.first().type)})"
        }
        if (type.toString().let { it.startsWith("Throwable") || it.startsWith("Exception") }) {
            return "Exception(\"\")"
        }
        if (type.toString().startsWith("Iterator")) {
            return ""
        }
        if (type.toString().startsWith("Unit")) {
            return "{}"
        }
        if (type.toString().startsWith("StringBuilder")) {
            return "StringBuilder(${generateDefValuesAsString("String")})"
        }
        if (type.toString().startsWith("CharSequence")) {
            return generateDefValuesAsString("String")
        }
        if (type.toString().startsWith("Regex")) {
            return "Regex(${generateDefValuesAsString("String")})"
        }
        if (type.toString().startsWith("Comparable")) {
            if (type.arguments.isEmpty()) return ""
            return generateValueOfType(type.arguments.first().type)
        }
        if (type.toString().let { it.startsWith("Suspend") || it.startsWith("Enum") }) {
            return ""
        }
        generateDefValuesAsString(type.toString()).let { if (it.isNotEmpty()) return it }
        return ""
    }

    private fun searchForImplementation(type: KotlinType): String {
        println("TYPE = $type")
        val funcs = UsageSamplesGeneratorWithStLibrary.searchForFunWithRetType(type)
        val implementations = UsageSamplesGeneratorWithStLibrary.findImplementationOf(type)
        val prob = if (funcs.size > implementations.size) 75 else 25
        val el =
            if (Random.getTrue(prob)) {
                funcs.randomOrNull() ?: implementations.randomOrNull()
            } else {
                implementations.randomOrNull() ?: funcs.randomOrNull()
            }
        if (el is ClassDescriptor && el.defaultType.isPrimitiveTypeOrNullablePrimitiveTypeOrString())
            return generateDefValuesAsString(el.name.asString())
        val psi = when (el) {
            is SimpleFunctionDescriptor -> TypeParamsReplacer.throwTypeParams(type, el)
            is ClassDescriptor -> TypeParamsReplacer.throwTypeParams(type, el)
            else -> return ""
        }
        val extTypeRec = (el as? SimpleFunctionDescriptor)?.extensionReceiverParameter?.value?.type
        val generatedExtension = extTypeRec?.let { generateValueOfType(it) + "." } ?: ""
        generateTopLevelFunctionCall(psi)?.let { return "$generatedExtension${it.first.text}" } ?: return ""
    }

    private fun recreateType(type: KotlinType): KotlinType? {
        val stringType = (type as? UnresolvedType)?.presentableName ?: return null
        val newFile = Factory.psiFactory.createFile("val abcq: $stringType\n\n${fileCopy.text}")
        val ctx = PSICreator.analyze(newFile) ?: return null
        return newFile.getAllPSIChildrenOfType<KtProperty>().first().typeReference?.getAbbreviatedTypeOrType(ctx)
    }


    private fun recreateKlass(klass: KtClassOrObject, type: KotlinType): KtClassOrObject? {
        if (klass.typeParameters.size != type.arguments.size) return null
        val copyOfKlass = klass.copy() as KtClassOrObject
        copyOfKlass.typeParameters.zip(type.arguments.map { it.type.getNameWithoutError() })
            .forEach { (typeArg, genTypeArg) ->
                typeArg.replaceThis(Factory.psiFactory.createType(genTypeArg))
                copyOfKlass.getAllPSIChildrenOfType<KtTypeReference>().forEach { typeRef ->
                    if (typeRef.text == typeArg.text) typeRef.replaceThis(Factory.psiFactory.createType(genTypeArg))
                }
            }
        return copyOfKlass
    }

    private fun getTypeNameWithoutError(type: KotlinType): String? =
        (type as? UnresolvedType)?.presentableName


    private val typeParamToRandomType: MutableMap<String, String> = mutableMapOf()
    val amount: Int
        get() = Random.nextInt(1, 3)
    private lateinit var ctx: BindingContext
    private val fileCopy = file.copy() as KtFile

}