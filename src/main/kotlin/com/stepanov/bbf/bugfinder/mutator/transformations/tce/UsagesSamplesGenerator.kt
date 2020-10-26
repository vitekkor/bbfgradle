package com.stepanov.bbf.bugfinder.mutator.transformations.tce

import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.util.findFunByName
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.bugfinder.util.replaceThis
import com.stepanov.bbf.reduktor.parser.PSICreator
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.isPrivate
import org.jetbrains.kotlin.psi.psiUtil.isPropertyParameter
import org.jetbrains.kotlin.psi.psiUtil.isProtected
import org.jetbrains.kotlin.resolve.calls.callUtil.getType
import org.jetbrains.kotlin.types.KotlinType
import kotlin.random.Random

object UsagesSamplesGenerator {

    fun generate(file: KtFile): List<Triple<KtExpression, String, KotlinType?>> {
        //val ctx = PSICreator.analyze(file)!!
        val instanceGenerator = RandomInstancesGenerator(file)
        val res = mutableListOf<KtExpression>()
        generateForKlasses(file, res, instanceGenerator)
        val withTypes = generateTypes(file, res.joinToString("\n") { it.text })
        generateForFuncs(file, res, instanceGenerator, withTypes)
        val resToStr = res.joinToString("\n") { it.text }
        return generateTypes(file, resToStr)
    }

    private fun generateForFuncs(
        file: KtFile,
        res: MutableList<KtExpression>,
        generator: RandomInstancesGenerator,
        klassInstances: List<Triple<KtExpression, String, KotlinType?>>
    ) {
        for (func in file.getAllPSIChildrenOfType<KtNamedFunction>().filter { it.isTopLevel }) {
            if (func.name?.startsWith("box") == true) continue
            val (instance, valueParams) = generator.generateTopLevelFunctionCall(func) ?: continue
            val valueArgs =
                if (instance is KtCallExpression) instance.valueArguments
                else null
            for ((valueArg, param) in valueArgs?.zip(valueParams) ?: listOf()) {
                if (param.typeReference == null) continue
                val anotherExpr = klassInstances.getValueOfType(param.typeReference!!.text)
                if (anotherExpr != null && Random.nextBoolean()) {
                    valueArg.replaceThis(anotherExpr.copy())
                }
            }
            res.add(instance)
        }
    }

    private fun generateForKlasses(
        file: KtFile,
        res: MutableList<KtExpression>,
        generator: RandomInstancesGenerator,
        funInstances: List<Triple<KtExpression, String, KotlinType?>> = listOf()
    ) {
        val classes = file.getAllPSIChildrenOfType<KtClassOrObject>()
        for (klass in classes) {
            val openFuncsAndProps = mutableListOf<String>()
            klass.primaryConstructor?.valueParameters
                ?.filter { it.isPropertyParameter() && !it.isPrivate() && it.name != null }
                ?.forEach { openFuncsAndProps.add(it.name!!) }
            filterOpenFuncsAndPropsFromDecl(klass.declarations).forEach { openFuncsAndProps.add(it) }
            val instanceOfKlass = generator.generateRandomInstanceOfClass(klass)
            openFuncsAndProps
                .mapNotNull {
                    if (it.startsWith("CoBj"))
                        Factory.psiFactory.createExpressionIfPossible("${klass.name}.${it.substringAfter("CoBj")}")
                    else
                        Factory.psiFactory.createExpressionIfPossible("${instanceOfKlass?.text}.$it")
                }
                .forEach { res.add(it) }
        }
    }

    private fun filterOpenFuncsAndPropsFromDecl(declarations: List<KtDeclaration>): List<String> {
        val openFuncsAndProps = mutableListOf<String>()
        for (decl in declarations) {
            if (decl.isPrivate() || decl.isProtected() || (decl.name == null && decl !is KtObjectDeclaration)) continue
            when (decl) {
                is KtProperty -> openFuncsAndProps.add(decl.name!!)
                is KtNamedFunction -> openFuncsAndProps.add("${decl.name!!}()")
                is KtObjectDeclaration ->
                    filterOpenFuncsAndPropsFromDecl(decl.declarations).forEach {
                        openFuncsAndProps.add("CoBj$it")
                    }
            }
        }
        return openFuncsAndProps
    }

    private fun generateTypes(file: KtFile, resToStr: String): List<Triple<KtExpression, String, KotlinType?>> {
        val func = Factory.psiFactory.createFunction("fun usageExamples(){\n$resToStr\n}")
        val newFile = Factory.psiFactory.createFile(file.text + "\n\n" + func.text)
        val ctx = PSICreator.analyze(newFile)!!
        val myFunc = newFile.findFunByName("usageExamples")!!
        return myFunc.getAllPSIChildrenOfType<KtExpression>()
            .filter { it.parent == myFunc.bodyBlockExpression }
            .map { Triple(it, it.text, it.getType(ctx)) }
            .filter { it.third != null }
    }

    fun List<Triple<KtExpression, String, KotlinType?>>.getValueOfType(type: String): KtExpression? =
        this.filter { it.third?.let { it.toString() == type || it.toString() == type.substringBefore('?') } ?: false }
            .randomOrNull()?.first
}