package com.stepanov.bbf.bugfinder.util.typeGenerators

import com.stepanov.bbf.bugfinder.generator.constructor.util.StandardLibraryInheritanceTree
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.UsageSamplesGeneratorWithStLibrary
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.UsageSamplesGeneratorWithStLibrary.getDeclDescriptorOf
import com.stepanov.bbf.bugfinder.util.*
import com.stepanov.bbf.bugfinder.util.KotlinTypeCreator.createType
import org.jetbrains.kotlin.backend.common.serialization.findPackage
import org.jetbrains.kotlin.builtins.PrimitiveType
import org.jetbrains.kotlin.builtins.UnsignedType
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.modalityModifierType
import org.jetbrains.kotlin.psi.psiUtil.parents
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.bindingContextUtil.getAbbreviatedTypeOrType
import org.jetbrains.kotlin.resolve.calls.callUtil.getType
import org.jetbrains.kotlin.resolve.lazy.descriptors.LazyPackageDescriptor
import org.jetbrains.kotlin.resolve.scopes.getDescriptorsFiltered
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedClassDescriptor
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.isNullable
import org.jetbrains.kotlin.types.replace
import org.jetbrains.kotlin.types.typeUtil.isAnyOrNullableAny
import org.jetbrains.kotlin.types.typeUtil.substitute
import kotlin.random.Random

object RandomTypeGenerator {

    lateinit var file: KtFile
    lateinit var ctx: BindingContext
    var minVisibility = "public"

    fun setFileAndContext(file: KtFile, ctx: BindingContext) {
        RandomTypeGenerator.file = file
        RandomTypeGenerator.ctx = ctx
    }

    fun generateRandomTypeWithCtx(upperBounds: KotlinType? = null, depth: Int = 0): KotlinType? {
        val resRandomType: KotlinType?
        when {
            upperBounds != null && !upperBounds.isAnyOrNullableAny() -> {
                resRandomType = generateWithUpperBounds(upperBounds)
            }
            Random.getTrue(5) -> {
                resRandomType = generateType(generateFunType())
            }
            else -> {
                val type = when (Random.nextInt(0, 10)) {
                    in 0..(3 + depth) -> generateType(primitives.random())
                    in (4 + depth)..(6 + depth) -> getTypeFromFile(depth = depth)
                    else -> generateType(generateContainer1(depth))
                }
                resRandomType =
                    if (upperBounds?.isNullable() == false)
                        type
                    else if (Random.getTrue(20) && type != null)
                        generateType("$type?")
                    else
                        type
            }
        }
        minVisibility = resRandomType?.getMinModifier()?.let {
            if (compareDescriptorVisibilitiesAsStrings(minVisibility, it) == -1) it
            else minVisibility
        } ?: minVisibility
        return resRandomType
    }

    private fun generateFunType(): String {
        val default = "() -> ${generatePrimitive()}"
        val valueParams =
            List(Random.nextInt(1, 3)) {
                generateRandomTypeWithCtx() ?: return default
            }.joinToString()
        val rtv = generateRandomTypeWithCtx()
        return "($valueParams) -> $rtv"
    }

    private fun generateWithUpperBounds(upperBounds: KotlinType, depth: Int = 0): KotlinType? {
        var fromFile = false
        val impls =
            if (file.getAllPSIChildrenOfType<KtClassOrObject>().any { it.name == "$upperBounds".substringBefore('<') }) {
                fromFile = true
                UsageSamplesGeneratorWithStLibrary.findImplementationFromFile(upperBounds, false)
            } else {
                UsageSamplesGeneratorWithStLibrary.findImplementationOf(upperBounds, false)
            }
        if (impls.isEmpty() || Random.getTrue(50)) return upperBounds
        val implsTypes = impls.map { it.defaultType }
        val randomImpl =
            implsTypes.shuffled().find { it.isPrimitiveTypeOrNullablePrimitiveTypeOrString() } ?: implsTypes.random()
        val withNewTypeParams =
            if (randomImpl.arguments.isNotEmpty()) randomImpl.replace(upperBounds.arguments)
            else randomImpl
        return when {
            fromFile -> getTypeFromFile(
                withNewTypeParams.constructor.declarationDescriptor!!.name.asString(),
                depth + 1
            )
            else -> withNewTypeParams
        }
    }

    fun getTypeFromFile(name: String = "", depth: Int = 0): KotlinType? {
        val randomClass =
            if (name.isEmpty())
                file.getAllPSIChildrenOfType<KtClassOrObject>()
                    .filter { it.name != null }
                    .filter { !it.isLocal }
                    .filter { it.parents.all { !(it is KtClassOrObject) } }
                    .randomOrNull() ?: return generateType(primitives.random())
            else
                file.getAllPSIChildrenOfType<KtClassOrObject>().find { it.name == name } ?: return generateType(
                    primitives.random()
                )
        val typeParamToType = mutableMapOf<String, KotlinType>()
        val sortedTypeParams =
            randomClass.typeParameters
                .asSequence()
                .sortedWith(typeParamComparator)
                .sortedWith(typeParamComparator)
//                .map { it to generateType(it.text) }
//                .filter { it.second?.isTypeParameter() ?: true }
//                .map { it.first }
                .toList()
        sortedTypeParams.forEach {
            val upperBounds =
                if (it.extendsBound != null) {
                    val upperBounds = it.extendsBound?.getAbbreviatedTypeOrType(ctx) ?: return null
                    typeParamToType.getOrElse(it.extendsBound!!.text) {
                        val substitutedArgs = upperBounds.arguments.map { arg ->
                            arg.substitute {
                                typeParamToType[it.getNameWithoutError()] ?: arg.type
                            }
                        }
                        upperBounds.replace(substitutedArgs)
                    }
                } else null
            val generatedType = generateRandomTypeWithCtx(upperBounds, depth + 1) ?: return null
            //require(generatedType != null) { println(it.text + "\n" + randomClass.text) }
            typeParamToType[it.name!!] = generatedType
        }
//        val finalTypeParams =
//            randomClass.typeParameters.map { typeParamToType[it.name]!! }
        val klassNames = file.getAllPSIChildrenOfType<KtClassOrObject>().map { it.name }.filterNotNull()
        val klassList =
            randomClass.typeParameters
                .map { typeParamToType.getOrDefault(it.name, it.name) }
                .joinToString()
                .plus(",$name")
                .split(Regex("""[,<>]"""))
                .map { it.trim() }
                .filter { it in klassNames }
        val finalTypeParams = if (klassList.size == klassList.toSet().size)
            randomClass.typeParameters.map { typeParamToType.getOrDefault(it.name, it.name) }
        else
            randomClass.typeParameters.map { generateType(generatePrimitive()) }
        val strTypeParams =
            if (finalTypeParams.isEmpty()) "" else finalTypeParams.joinToString(prefix = "<", postfix = ">")
        val klassToStr = randomClass.name + strTypeParams
        return generateType(klassToStr)
    }

    fun generateType(name: String): KotlinType? {
        if (!RandomTypeGenerator::file.isInitialized || !RandomTypeGenerator::ctx.isInitialized) return null
        generateType(file, ctx, name.trim()).let {
            if (it == null) {
                println("CANT GENERATE TYPE $name")
//                println(file.text)
//                exitProcess(0)
            }
            return it
        }
    }

    fun generateType(file: KtFile, ctx: BindingContext, name: String): KotlinType? {
        val exprs = file.getAllPSIChildrenOfType<KtExpression>().mapNotNull { it.getType(ctx) }
        val references =
            file.getAllPSIChildrenOfType<KtTypeReference>().mapNotNull { it.getAbbreviatedTypeOrType(ctx) }
        val res = exprs + references
        res.firstOrNull { it.toString() == name }?.let { return it }
        return createType(file, name)?.let { if (it.isErrorType()) null else it }
    }


    fun generateRandomType(upperBounds: KotlinType? = null): String {
        if (upperBounds == null) return if (Random.nextBoolean()) generateContainer() else generatePrimitive()
        val implementations = StandardLibraryInheritanceTree.getSubtypesOf(upperBounds.toString().substringBefore("<"))
        return primitives.intersect(implementations).randomOrNull()
            ?: containers.intersect(implementations).randomOrNull()
            ?: upperBounds.toString()
    }


    private fun generateContainer1(depth: Int = 0): String {
        val container = containers.random()
        val descr = getDeclDescriptorOf(container) as DeserializedClassDescriptor
        val typeParams =
            descr.typeConstructor.parameters.map {
                generateRandomTypeWithCtx(it.upperBounds.firstOrNull(), depth + 1)
            }
        val strTypeParams = if (typeParams.isEmpty()) "" else typeParams.joinToString(prefix = "<", postfix = ">")
        return "$container$strTypeParams"
    }

    private fun generateContainer(): String {
        val container = containers.random()
        val descr = getDeclDescriptorOf(container) as DeserializedClassDescriptor
        val typeParams = descr.typeConstructor.parameters.map {
            if (Random.nextBoolean()) {
                generatePrimitive()
            } else {
                generateContainer()
            }
        }.joinToString(prefix = "<", postfix = ">")
        return "$container$typeParams"
    }

    fun generateOpenClassType(onlyFromFile: Boolean = false, onlyInterfaces: Boolean = false): ClassDescriptor? {
        val fromSrc = run {
            val randomClass =
                file.getAllPSIChildrenOfType<KtClass>()
                    .filter { it.isInterface() || it.modalityModifierType()?.value?.trim() == "open" }
                    .filter { it.isInterface() || !onlyInterfaces }
                    .filter { !it.hasModifier(KtTokens.PRIVATE_KEYWORD) }
                    .randomOrNull() ?: return@run null
            val typeParams =
                randomClass.typeParameters.let { if (it.isEmpty()) "" else randomClass.typeParameterList?.text }
            val fromFile = generateType("${randomClass.name}$typeParams")!!
            val p = fromFile.constructor.declarationDescriptor!!.findPackage() as LazyPackageDescriptor
            val scope = p.getMemberScope().getDescriptorsFiltered { true }.filterIsInstance<ClassDescriptor>().toList()
            scope.find { it.name == randomClass.nameAsName }
        }
        if (onlyFromFile) return fromSrc
        return fromSrc
        //TODO!!!
        //if (Random.nextBoolean() && fromSrc != null) return fromSrc
        //return UsageSamplesGeneratorWithStLibrary.generateOpenClassType(onlyInterfaces)
    }


    fun generatePrimitive(): String = primitives.random()

    val primitives: List<String> =
        enumValues<PrimitiveType>().map { it.typeName.asString() } +
                enumValues<UnsignedType>().map { it.typeName.asString() } +
                listOf("String")

    val signedPrimitives: List<String> =
        enumValues<PrimitiveType>().map { it.typeName.asString() } +
                listOf("String")

    val containers = listOf(
        "ArrayList", "List", "Set", "Map", "Array", "HashMap", "MutableMap",
        "HashSet", "LinkedHashMap", "LinkedHashSet", "Collection", "ArrayDeque",
        "Pair", "Triple", "Sequence"
    )

    private val typeParamComparator = Comparator { t1: KtTypeParameter, t2: KtTypeParameter ->
        if (t1.extendsBound == null && t2.extendsBound == null) 0
        else if (t1.getAllPSIChildrenOfType<KtTypeReference>().any { it.text == t2.name }) 1 else -1
    }
}