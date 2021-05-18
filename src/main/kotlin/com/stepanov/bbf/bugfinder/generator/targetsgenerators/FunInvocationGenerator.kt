package com.stepanov.bbf.bugfinder.generator.targetsgenerators

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.typeGenerators.RandomTypeGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.util.addToTheEnd
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.util.getAllChildren
import org.apache.log4j.Logger
import org.jetbrains.kotlin.cfg.getDeclarationDescriptorIncludingConstructors
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.types.TypeProjectionImpl
import org.jetbrains.kotlin.types.TypeSubstitutor

class FunInvocationGenerator(file: KtFile, private val ctx: BindingContext) :
    TypeAndValueParametersGenerator(file) {

    private val MAX_DEPTH = 20

    fun generateTopLevelFunInvocation(func: KtNamedFunction, depth: Int = 0): PsiElement? {
        if (depth > MAX_DEPTH) return null
        val funAsKtDeclaration = (func as? KtDeclaration) ?: return null
        val functionDescriptor =
            funAsKtDeclaration.getDeclarationDescriptorIncludingConstructors(ctx) as? FunctionDescriptor ?: return null
        val newTypeParameters = generateTypeParameters(functionDescriptor.typeParameters)
        val typeSubstitutor = TypeSubstitutor.create(
            functionDescriptor.typeParameters
                .withIndex()
                .associateBy({ it.value.typeConstructor }) {
                    TypeProjectionImpl(newTypeParameters[it.index])
                }
        )
        val newFD = functionDescriptor.substitute(typeSubstitutor) ?: return null
        val generatedValueParams = generateValueParameters(newFD.valueParameters, depth)
        val typeParamsAsString =
            if (newTypeParameters.isEmpty()) ""
            else newTypeParameters.joinToString(prefix = "<", postfix = ">") { "$it" }
        val invocationAsString = "${func.name}$typeParamsAsString(${generatedValueParams.joinToString()})"
        return Factory.psiFactory.createExpressionIfPossible(invocationAsString)
    }



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