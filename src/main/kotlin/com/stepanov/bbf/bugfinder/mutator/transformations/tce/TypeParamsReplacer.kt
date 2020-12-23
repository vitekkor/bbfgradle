package com.stepanov.bbf.bugfinder.mutator.transformations.tce

import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.util.typeGenerators.RandomTypeGenerator
import com.stepanov.bbf.bugfinder.util.splitWithoutRemoving
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.SimpleFunctionDescriptor
import org.jetbrains.kotlin.descriptors.ValueParameterDescriptor
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.resolve.descriptorUtil.getAllSuperClassifiers
import org.jetbrains.kotlin.types.KotlinType
import com.stepanov.bbf.bugfinder.util.flatMap
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.resolve.calls.components.isVararg
import kotlin.system.exitProcess

object TypeParamsReplacer {

    fun throwTypeParams(
        fromType: KotlinType,
        toType: ClassDescriptor,
        typeGenerator: RandomTypeGenerator
    ): Pair<KtNamedFunction, Map<String, String>> {
        val implConstr =
            if (CompilerArgs.isABICheckMode)
                toType.constructors
                    .filter { it.visibility.isPublicAPI }
                    .minByOrNull { it.valueParameters.size }!!
            else
                toType.constructors.filter { it.visibility.isPublicAPI }.random()
        val implSupertype =
            toType.getAllSuperClassifiers()
                .find { it.name.asString() == fromType.constructor.toString() } as? ClassDescriptor
        val realTypeParams = fromType.arguments
        val implSupertypeTypeParams = implSupertype!!.declaredTypeParameters.map { it.name }
        val typeParams = toType.declaredTypeParameters.map {
            val name = it.name
            val index = implSupertypeTypeParams
                .indexOf(name)
                .let { if (it == -1) implSupertypeTypeParams.indexOf("$name?") else it }
            if (index != -1)
                realTypeParams[index].toString()
            else typeGenerator.generateRandomTypeWithCtx(it.upperBounds.firstOrNull()).toString()
        }
        val oldToRealTypeParams = toType.declaredTypeParameters.map { it.name.asString() }.zip(typeParams).toMap()
        val valueParams = produceValueParams(implConstr.valueParameters, oldToRealTypeParams)
        val strTypeParams = implConstr.typeParameters.joinToString(prefix = " <", postfix = ">") { it.name.asString() }
        val func = StringBuilder().apply {
            append("fun")
            if (strTypeParams != " <>") append(strTypeParams)
            append(" ${toType.name}")
            append("($valueParams): ")
            append(fromType.toString())
            append(" = TODO()")
        }.toString()
        return Factory.psiFactory.createFunction(func) to oldToRealTypeParams
    }

    fun throwTypeParams(
        fromType: KotlinType,
        toType: FunctionDescriptor,
        typeGenerator: RandomTypeGenerator
    ): Pair<KtNamedFunction, Map<String, String>> {
        val retType = toType.returnType!!
        val realTypeParams = fromType.arguments.map { it.type }
        val implSupertypeTypeParams = retType.arguments.map { it.type.toString() }
        val typeParams = toType.typeParameters.map {
            val name = it.name.asString()
            val index = implSupertypeTypeParams
                .indexOf(name)
                .let { if (it == -1) implSupertypeTypeParams.indexOf("$name?") else it }
            if (index != -1) {
                realTypeParams[index].toString()
            } else {
                typeGenerator.generateRandomTypeWithCtx(it.upperBounds.firstOrNull()).toString()
                //name
            }
        }
        val oldToRealTypeParams = toType.typeParameters.map { it.name.asString() }.zip(typeParams).toMap()
        val valueParams = produceValueParams(toType.valueParameters, oldToRealTypeParams)
        val res = StringBuilder().apply {
            append("fun")
            if (toType.typeParameters.isNotEmpty()) {
                append(toType.typeParameters.joinToString(prefix = " <", postfix = ">") {
                    it.name.asString()
                })
            }
            append(" ${toType.name}")
            append("($valueParams): ")
            append(fromType.toString().split(Regex("""in\s*|out\s*""")).joinToString(""))
            append(" = TODO()")
        }
        val func = Factory.psiFactory.createFunction(res.toString())
        return func to oldToRealTypeParams
    }

    private fun produceValueParams(
        valueParameters: List<ValueParameterDescriptor>,
        oldToRealTypeParams: Map<String, String>
    ) =
        valueParameters.joinToString { param ->
            val paramType =
                if (param.isVararg)
                    param.varargElementType.toString()
                else
                    param.toString()
                        .substringAfter(':')
                        .substringBefore("defined")
                        .substringBefore('=')
                        .substringAfter(']')
            param.name.asString() + ": " +
                    paramType
                        .splitWithoutRemoving(Regex("""[<>,()]|in |out """))
                        .flatMap { it.splitWithoutRemoving(Regex("""\[.*\]""")) }
                        .map { it.trim() }
                        .filter { it.isNotEmpty() && !it.startsWith('[') }
                        .joinToString(separator = "") {
                            if (it == ",") "$it "
                            else if (it == "in" || it == "out") ""
                            else oldToRealTypeParams[it] ?: oldToRealTypeParams[makeNotNullable(it)] ?: it
                        }
        }


    private fun makeNotNullable(type: String) = type.substringBeforeLast('?')

}