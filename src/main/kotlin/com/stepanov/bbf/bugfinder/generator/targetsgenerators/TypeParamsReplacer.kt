package com.stepanov.bbf.bugfinder.generator.targetsgenerators

import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.typeGenerators.RandomTypeGenerator
import com.stepanov.bbf.bugfinder.util.*
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.ValueParameterDescriptor
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.resolve.descriptorUtil.getAllSuperClassifiers
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.descriptors.ClassConstructorDescriptor
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.resolve.calls.components.isVararg
import org.jetbrains.kotlin.types.TypeProjectionImpl
import org.jetbrains.kotlin.types.TypeSubstitutor

object TypeParamsReplacer {

    fun throwTypeParams(
        fromType: KotlinType,
        toType: ClassDescriptor,
        typeGenerator: RandomTypeGenerator,
        withoutParams: Boolean = false
    ): Pair<KtNamedFunction, Map<String, KotlinType?>> {
        val implConstr =
            if (CompilerArgs.isABICheckMode)
                toType.constructors
                    .filter { it.visibility.isPublicAPI }
                    .minByOrNull { it.valueParameters.size }!!
            else
                toType.constructors
                    .filter { it.visibility.isPublicAPI }
                    .filter { it.valueParameters.all { !it.type.hasTypeParam() } || !withoutParams }
                    .random()
        val implSupertype =
            toType.getAllSuperClassifiers()
                .find { it.name.asString() == fromType.constructor.toString() } as? ClassDescriptor
        val realTypeParams = fromType.arguments
        val implSupertypeTypeParams = implSupertype!!.declaredTypeParameters.map { it.name }
        val typeParams = toType.declaredTypeParameters.map {
            val name = it.name
            val index = implSupertypeTypeParams
                .indexOf(name)
                .let { if (it == -1) implSupertypeTypeParams.map { it.asString() }.indexOf("$name?") else it }
            if (index != -1 && realTypeParams.size > index)
                realTypeParams[index].type
            else typeGenerator.generateRandomTypeWithCtx(it.upperBounds.firstOrNull())
        }
        val oldToRealTypeParams = toType.declaredTypeParameters.map { it.name.asString() }.zip(typeParams).toMap()
        val valueParams = produceValueParams(implConstr, oldToRealTypeParams)
        val strTypeParams = implConstr.typeParameters.joinToString(prefix = " <", postfix = ">") { it.name.asString() }
        val func = StringBuilder().apply {
            append("fun")
            if (strTypeParams != " <>") append(strTypeParams)
            append(" ${toType.name}")
            append("($valueParams): ")
            append(fromType.getNameWithoutError())
            append(" = TODO()")
        }.toString()
        return Factory.psiFactory.createFunction(func) to oldToRealTypeParams
    }

    fun throwTypeParams(
        fromType: KotlinType,
        toType: FunctionDescriptor,
        typeGenerator: RandomTypeGenerator
    ): Pair<KtNamedFunction, Map<String, KotlinType?>> {
        val retType = toType.returnType!!
        val realTypeParams = fromType.arguments.map { it.type }
        val implSupertypeTypeParams = retType.arguments.map { it.type.toString() }
        val typeParams = toType.typeParameters.map {
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
        val oldToRealTypeParams = toType.typeParameters.map { it.name.asString() }.zip(typeParams).toMap()
        val valueParams = produceValueParams(toType, oldToRealTypeParams)
        val res = StringBuilder().apply {
            append("fun")
            if (toType.typeParameters.isNotEmpty()) {
                append(toType.typeParameters.joinToString(prefix = " <", postfix = ">") {
                    it.name.asString()
                })
            }
            append(" ${toType.name}")
            append("($valueParams): ")
            append(fromType.getNameWithoutError())
            append(" = TODO()")
        }
        val func = Factory.psiFactory.createFunction(res.toString())
        return func to oldToRealTypeParams
    }

    private fun produceValueParams(
        targetFunc: FunctionDescriptor,
        oldToRealTypeParams: Map<String, KotlinType?>
    ): String {
        val targetFuncWithReplacedTP = targetFunc.substitute(
            TypeSubstitutor.create(
                targetFunc.typeParameters
                    .withIndex()
                    .associateBy({ it.value.typeConstructor }) {
                        TypeProjectionImpl(oldToRealTypeParams[it.value.name.asString()]!!)
                    }
            )
        ) ?: return ""
        return targetFuncWithReplacedTP.valueParameters.joinToString { "${it.name}: ${it.type.getNameWithoutError()}" }
    }
//        valueParameters.joinToString { param ->
//            val paramType =
//                if (param.isVararg)
//                    param.varargElementType.toString()
//                else
//                    param.returnType.toString()
//                        .substringAfter(':')
//                        .substringBefore("defined")
//                        .substringBefore('=')
//                        .substringAfter(']')
//            param.name.asString() + ": " +
//                    paramType
//                        .splitWithoutRemoving(Regex("""[<>,()]|in |out """))
//                        .flatMap { it.splitWithoutRemoving(Regex("""\[.*\]""")) }
//                        .map { it.trim() }
//                        .filter { it.isNotEmpty() && !it.startsWith('[') }
//                        .joinToString(separator = "") {
//                            if (it == ",") "$it "
//                            else if (it == "in" || it == "out") ""
//                            else oldToRealTypeParams[it]?.toString()
//                                ?: oldToRealTypeParams[makeNotNullable(it)]?.toString() ?: it
//                        }
//        }


    private fun makeNotNullable(type: String) = type.substringBeforeLast('?')

}