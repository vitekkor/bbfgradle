package com.stepanov.bbf.bugfinder.generator.targetsgenerators

import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.typeGenerators.RandomTypeGenerator
import com.stepanov.bbf.bugfinder.util.*
import org.jetbrains.kotlin.builtins.KotlinBuiltIns
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.resolve.descriptorUtil.getAllSuperClassifiers
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.types.TypeProjectionImpl
import org.jetbrains.kotlin.types.TypeSubstitutor

object TypeParamsReplacer {

    fun throwTypeParams(
        fromType: KotlinType,
        targetDescriptor: FunctionDescriptor,
        typeGenerator: RandomTypeGenerator
    ): Pair<FunctionDescriptor, Map<String, KotlinType?>>? {
        val retType = targetDescriptor.returnType!!
        val realTypeParams = fromType.arguments.map { it.type }
        val implSupertypeTypeParams = retType.arguments.map { it.type.toString() }
        val typeParams = targetDescriptor.typeParameters.map {
            val name = it.name.asString()
            val index = implSupertypeTypeParams
                .indexOf(name)
                .let { if (it == -1) implSupertypeTypeParams.indexOf("$name?") else it }
            if (index != -1) {
                realTypeParams[index]
            } else {
                typeGenerator.generateRandomTypeWithCtx(it.upperBounds.firstOrNull())
                //name
            }
        }
        val oldToRealTypeParams = targetDescriptor.typeParameters.map { it.name.asString() }.zip(typeParams).toMap()
        val substitutedDescriptor = substituteTypeParams(targetDescriptor, oldToRealTypeParams) ?: return null
        return substitutedDescriptor to oldToRealTypeParams


//        val substituted = TypeSubstitutor.create(
//            targetDescriptor.typeParameters
//                .withIndex()
//                .associateBy({ it.value.typeConstructor }) {
//                    TypeProjectionImpl(oldToRealTypeParams[it.value.name.asString()]!!)
//                }
//        ).let { targetDescriptor.substitute(it) }!!
//        val valueParams = substituted.valueParameters.joinToString { "${it.name}: ${it.type.getNameWithoutError()}" }
//        val extRecAsString =
//            substituted.extensionReceiverParameter?.let { " " + it.type.getNameWithoutError() + "." } ?: ""
//        val res = StringBuilder().apply {
//            append("fun")
//            if (targetDescriptor.typeParameters.isNotEmpty()) {
//                append(targetDescriptor.typeParameters.joinToString(prefix = " <", postfix = ">") {
//                    it.name.asString()
//                })
//            }
//            append(extRecAsString)
//            append(" ${targetDescriptor.name}")
//            append("($valueParams): ")
//            append(fromType.getNameWithoutError())
//            append(" = TODO()")
//        }
//        val func = Factory.psiFactory.createFunction(res.toString())
//        return func to oldToRealTypeParams
    }

    fun throwTypeParams(
        fromType: KotlinType,
        targetDescriptor: ClassDescriptor,
        typeGenerator: RandomTypeGenerator,
        withoutParams: Boolean = false
    ): Pair<FunctionDescriptor, Map<String, KotlinType?>>? {
        val implConstr =
            if (CompilerArgs.isABICheckMode)
                targetDescriptor.constructors
                    .filter { it.visibility.isPublicAPI }
                    .minByOrNull { it.valueParameters.size }!!
            else
                targetDescriptor.constructors
                    .filter { it.visibility.isPublicAPI }
                    .filter { it.valueParameters.all { !it.type.hasTypeParam() } || !withoutParams }
                    .random()
        val implSupertype =
            targetDescriptor.getAllSuperClassifiers()
                .find { it.name.asString() == fromType.constructor.toString() } as? ClassDescriptor
        val realTypeParams = fromType.arguments
        val implSupertypeTypeParams = implSupertype!!.declaredTypeParameters.map { it.name }
        val typeParams = targetDescriptor.declaredTypeParameters.map {
            val name = it.name
            val index = implSupertypeTypeParams
                .indexOf(name)
                .let { if (it == -1) implSupertypeTypeParams.map { it.asString() }.indexOf("$name?") else it }
            if (index != -1 && realTypeParams.size > index)
                realTypeParams[index].type
            else typeGenerator.generateRandomTypeWithCtx(it.upperBounds.firstOrNull())
        }
        val oldToRealTypeParams =
            targetDescriptor.declaredTypeParameters.map { it.name.asString() }.zip(typeParams).toMap()
        val substitutedDescriptor = substituteTypeParams(implConstr, oldToRealTypeParams) ?: return null
        return substitutedDescriptor to oldToRealTypeParams

//        val valueParams = produceValueParamsForConstructor(implConstr, oldToRealTypeParams)
//        val strTypeParams = implConstr.typeParameters.joinToString(prefix = " <", postfix = ">") { it.name.asString() }
//        val func = StringBuilder().apply {
//            append("fun")
//            if (strTypeParams != " <>") append(strTypeParams)
//            append(" ${targetDescriptor.name}")
//            append("($valueParams): ")
//            append(fromType.getNameWithoutError())
//            append(" = TODO()")
//        }.toString()
//        return Factory.psiFactory.createFunction(func) to oldToRealTypeParams
    }

    private fun substituteTypeParams(
        targetFunc: FunctionDescriptor,
        oldToRealTypeParams: Map<String, KotlinType?>
    ) = targetFunc.substitute(
        TypeSubstitutor.create(
            targetFunc.typeParameters
                .withIndex()
                .associateBy({ it.value.typeConstructor }) {
                    val newType =
                        oldToRealTypeParams[it.value.name.asString()] ?: RandomTypeGenerator.generateType("Any")!!
                    TypeProjectionImpl(newType)
                }
        )
    )


//    private fun produceValueParamsForConstructor(
//        targetFunc: FunctionDescriptor,
//        oldToRealTypeParams: Map<String, KotlinType?>
//    ): String {
//        val targetFuncWithReplacedTP = targetFunc.substitute(
//            TypeSubstitutor.create(
//                targetFunc.typeParameters
//                    .withIndex()
//                    .associateBy({ it.value.typeConstructor }) {
//                        TypeProjectionImpl(oldToRealTypeParams[it.value.name.asString()]!!)
//                    }
//            )
//        ) ?: return ""
//        return targetFuncWithReplacedTP.valueParameters.joinToString { "${it.name}: ${it.type.getNameWithoutError()}" }
//    }
//
//    private fun makeNotNullable(type: String) = type.substringBeforeLast('?')

}