package com.stepanov.bbf.bugfinder.generator.targetsgenerators

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory.tryToCreateExpression
import com.stepanov.bbf.bugfinder.util.getTrue
import org.apache.log4j.Logger
import org.jetbrains.kotlin.cfg.getDeclarationDescriptorIncludingConstructors
import org.jetbrains.kotlin.descriptors.ClassConstructorDescriptor
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.ConstructorDescriptor
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.load.java.descriptors.JavaClassConstructorDescriptor
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.calls.components.hasDefaultValue
import org.jetbrains.kotlin.resolve.calls.components.isVararg
import org.jetbrains.kotlin.types.KotlinType
import kotlin.random.Random

internal class FunInvocationGenerator(file: KtFile) :
    TypeAndValueParametersGenerator(file) {

    private val MAX_DEPTH = 20
    private val log = Logger.getLogger("mutatorLogger")

    fun generateFunctionCallWithoutTypeParameters(
        functionDescriptor: FunctionDescriptor,
        typeParameters: Map<String, KotlinType?>,
        depth: Int
    ): PsiElement? {
        val randomInstanceGenerator = RandomInstancesGenerator(file, rtg.ctx)
        log.debug("Generating call of $functionDescriptor")
        val funcName =
            when (functionDescriptor) {
                is JavaClassConstructorDescriptor -> functionDescriptor.constructedClass.name
                is ClassConstructorDescriptor -> functionDescriptor.constructedClass.name
                else -> functionDescriptor.name
            }
        val typeParamsAsString =
            functionDescriptor.typeParameters.map {
                typeParameters[it.name.asString()] ?: "Any"
            }.let { if (it.isEmpty()) "" else "<${it.joinToString()}>" }
        val extRecValue =
            if (functionDescriptor.containingDeclaration is ClassDescriptor && functionDescriptor !is ConstructorDescriptor) {
                ((functionDescriptor.containingDeclaration) as ClassDescriptor).name.asString() + "."
            } else {
                functionDescriptor.extensionReceiverParameter?.type?.let {
                    randomInstanceGenerator.generateValueOfType(it, depth) + "."
                } ?: ""
            }
        val valueParams = functionDescriptor.valueParameters.map {
            if (it.isVararg) {
                val randomSize = Random.nextInt(1, 4)
                val elType = it.varargElementType!!
                List(randomSize) { "" }.map {
                    randomInstanceGenerator.generateValueOfType(elType, depth).let { it.ifEmpty { return null } }
                }.joinToString()
            } else {
                randomInstanceGenerator.generateValueOfType(it.type, depth).let { it.ifEmpty { return null } }
            }
        }.joinToString()
//        val valueParams = functionDescriptor.valueParameters.joinToString {
//            if (it.isVararg) {
//                val randomSize = Random.nextInt(1, 4)
//                val elType = it.varargElementType!!
//                List(randomSize) {
//                    RandomInstancesGenerator(file).generateValueOfType(elType)
//                }.joinToString()
//            } else {
//                RandomInstancesGenerator(file).generateValueOfType(it.type)
//            }
//        }
        val callExpression = """$extRecValue$funcName$typeParamsAsString($valueParams)"""
        return try {
            Factory.psiFactory.createExpression(callExpression)
        } catch (e: Exception) {
            null
        }
    }

    fun generateTopLevelFunInvocation(func: KtNamedFunction, ctx: BindingContext, depth: Int = 0): PsiElement? {
        val descriptor = func.getDeclarationDescriptorIncludingConstructors(ctx) as? FunctionDescriptor ?: return null
        return generateTopLevelFunInvocation(descriptor, depth)
    }

    fun generateTopLevelFunInvocation(func: FunctionDescriptor, depth: Int = 0): PsiElement? {
        val randomInstanceGenerator = RandomInstancesGenerator(file, rtg.ctx)
        val (descriptorWithoutTypeParams, realTypeParams) = TypeParamsReplacer.throwTypeParams(null, func)
            ?: return null
        val extensionReceiverType = descriptorWithoutTypeParams.extensionReceiverParameter?.value?.type
        val realTypeParamsAsString =
            if (func.typeParameters.isEmpty()) ""
            else func.typeParameters.map { realTypeParams[it.name.asString()] }
                .joinToString(prefix = "<", postfix = ">")
        val generatedExtensionReceiver =
            if (extensionReceiverType == null) {
                ""
            } else {
                randomInstanceGenerator.tryToGenerateValueOfType(extensionReceiverType, 2).ifEmpty { return null } + "."
            }
        val valueParamsAsString =
            if (descriptorWithoutTypeParams.valueParameters.isEmpty()) {
                "()"
            } else {
                descriptorWithoutTypeParams.valueParameters
                    .map { valueParam ->
                        val valueParamType =
                            if (valueParam.isVararg) {
                                valueParam.varargElementType ?: return null
                            } else {
                                valueParam.type
                            }
//                        if (valueParam.hasDefaultValue() && Random.getTrue(30)) {
//                            ""
//                        }
//                         else {
                             randomInstanceGenerator.tryToGenerateValueOfType(valueParamType, 2).ifEmpty { return null }
//                        }
                    }.filter { it.isNotEmpty() }.joinToString(prefix = "(", postfix = ")")
            }
        val callExpressionAsString =
            "$generatedExtensionReceiver${func.name}$realTypeParamsAsString$valueParamsAsString"
        return Factory.psiFactory.tryToCreateExpression(callExpressionAsString)
    }

//    fun generateTopLevelFunInvocation(func: KtNamedFunction, depth: Int = 0): PsiElement? {
//        if (depth > MAX_DEPTH) return null
//        val funAsKtDeclaration = (func as? KtDeclaration) ?: return null
//        val functionDescriptor =
//            funAsKtDeclaration.getDeclarationDescriptorIncludingConstructors(ctx) as? FunctionDescriptor ?: return null
//        val newTypeParameters = generateTypeParameters(functionDescriptor.typeParameters)
//        val typeSubstitutor = TypeSubstitutor.create(
//            functionDescriptor.typeParameters
//                .withIndex()
//                .associateBy({ it.value.typeConstructor }) {
//                    TypeProjectionImpl(newTypeParameters[it.index])
//                }
//        )
//        val newFD = functionDescriptor.substitute(typeSubstitutor) ?: return null
//        val generatedValueParams = generateValueParameters(newFD.valueParameters, depth)
//        val typeParamsAsString =
//            if (newTypeParameters.isEmpty()) ""
//            else newTypeParameters.joinToString(prefix = "<", postfix = ">") { "$it" }
//        val invocationAsString = "${func.name}$typeParamsAsString(${generatedValueParams.joinToString()})"
//        return Factory.psiFactory.createExpressionIfPossible(invocationAsString)
//    }


}

//fun generateTopLevelFunctionCall(
//    function: KtNamedFunction,
//    m: Map<String, String> = mapOf(),
//    withTypeParams: Boolean = true,
//    depth: Int = 0
//): Pair<KtExpression, List<KtParameter>>? {
//    var typeParamsToRealTypeParams = m
//    if (function.name == null) return null
//    if (function.receiverTypeReference == null && function.typeParameters.isEmpty() && function.valueParameters.isEmpty()) {
//        return Factory.psiFactory.createExpression("${function.name}()") to listOf()
//    }
//    ctx = PSICreator.analyze(file)!!
//    log.debug("GENERATING CALL OF ${function.text}")
//    val addedFun =
//        if (!file.getAllChildren().contains(function)) {
//            file.addToTheEnd(function) as KtNamedFunction
//        } else {
//            function
//        }
//    var func = function.copy() as KtNamedFunction
//    if (withTypeParams && func.typeParameterList != null) {
//        if (m.isEmpty())
//            typeParamsToRealTypeParams = generateRandomTypeParams(
//                function.typeParameters,
//                func.valueParameters,
//                func.bodyExpression?.let { func.text.substringBefore(it.text) } ?: func.text,
//                ctx,
//                func = func
//            )?.second ?: mapOf()
//    }
//    log.debug("WITHOUT TYPE PARAMS = ${func.text}")
//    addedFun.replaceThis(func)
//    val creator = PSICreator
//    val newFile = creator.getPSIForText(file.text)
//    val ctx = creator.ctx!!
//    func.replaceThis(addedFun)
//    if (addedFun != function) addedFun.delete()
//    func = newFile.getAllChildren().find { it.text.trim() == func.text.trim() } as? KtNamedFunction ?: return null
//    val generatedParams = func.valueParameters
//        .map { param ->
//            if (param.typeReference == null) return null
//            param.typeReference?.getAbbreviatedTypeOrType(ctx)?.let {
//                if ("$it" != param.typeReference!!.text.trim())
//                    randomTypeGenerator.generateType(param.typeReference!!.text)
//                else it
//            }
//        }
//        .map {
//            it?.let {
//                generateValueOfType(it, depth + 1).let {
//                    if (it.trim().isEmpty()) return null else it
//                }
//            } ?: return null
//        }
//        .joinToString(", ")
//    val generatedReciever =
//        func.receiverTypeReference?.getAbbreviatedTypeOrType(ctx)?.let { generateValueOfType(it, depth + 1) + "." }
//            ?: ""
//    val typeParams =
//        func.typeParameters.map { typeParamsToRealTypeParams.getOrDefault(it.name!!, it.name!!) }
//            .let { if (it.isNotEmpty()) it.joinToString(prefix = "<", postfix = ">") else "" }
//    val expr =
//        Factory.psiFactory.createExpressionIfPossible("$generatedReciever${func.name}$typeParams($generatedParams)")
//            ?: return null
//    log.debug("GENERATED = ${expr.text}")
//    return expr to func.valueParameters
//}