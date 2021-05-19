package com.stepanov.bbf.bugfinder.mutator.transformations.tce

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.RandomInstancesGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.util.findFunByName
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.bugfinder.util.replaceThis
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.typeGenerators.RandomTypeGenerator
import com.stepanov.bbf.bugfinder.util.getBoxFuncs
import com.stepanov.bbf.reduktor.parser.PSICreator
import org.jetbrains.kotlin.cfg.getDeclarationDescriptorIncludingConstructors
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.descriptors.PropertyDescriptor
import org.jetbrains.kotlin.descriptors.SimpleFunctionDescriptor
import org.jetbrains.kotlin.load.java.descriptors.JavaClassDescriptor
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.allChildren
import org.jetbrains.kotlin.psi.psiUtil.parents
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.bindingContextUtil.getAbbreviatedTypeOrType
import org.jetbrains.kotlin.resolve.calls.callUtil.getType
import org.jetbrains.kotlin.resolve.descriptorUtil.module
import org.jetbrains.kotlin.resolve.scopes.getDescriptorsFiltered
import org.jetbrains.kotlin.types.KotlinType
import kotlin.random.Random
import kotlin.system.exitProcess

object UsagesSamplesGenerator {

    private lateinit var instanceGenerator: RandomInstancesGenerator
    private val randomTypeGenerator = RandomTypeGenerator
    lateinit var file: KtFile
    lateinit var ctx: BindingContext

    //No more not private methods!
    fun generate(file_: KtFile, ctx_: BindingContext): List<Triple<KtExpression, String, KotlinType?>> {
        file = file_
        ctx = ctx_
        instanceGenerator = RandomInstancesGenerator(file)
        randomTypeGenerator.setFileAndContext(file, ctx)
        val res = mutableListOf<KtExpression>()
        generateForKlasses(file, res)
        val withTypes = generateTypes(file, res.joinToString("\n") { it.text })
        generateForFuncs(file, res, withTypes)
        generateForProps(file, res, withTypes)
        val resToStr = res.joinToString("\n") { it.text }
        return generateTypes(file, resToStr)
    }

    private fun generateForProps(
        file: KtFile,
        res: MutableList<KtExpression>,
        klassInstances: List<Triple<KtExpression, String, KotlinType?>>
    ) {
        val props = file.getAllPSIChildrenOfType<KtProperty>()
            .filter { it.isTopLevel }
            .filter { it.name != null }
        for (p in props) {
            if (p.receiverTypeReference == null) {
                res.add(Factory.psiFactory.createExpression(p.name!!))
            }
            val receiverTypeReference = p.receiverTypeReference?.getAbbreviatedTypeOrType(ctx) ?: continue
            val instance = instanceGenerator.generateValueOfType(receiverTypeReference)
            if (instance.isNotEmpty()) {
                res.add(Factory.psiFactory.createExpression("$instance.${p.name}"))
            }
        }
    }


    private fun generateForFuncs(
        file: KtFile,
        res: MutableList<KtExpression>,
        klassInstances: List<Triple<KtExpression, String, KotlinType?>>
    ) {
        for (func in file.getAllPSIChildrenOfType<KtNamedFunction>().filter { it.isTopLevel }) {
            if (func.name?.startsWith("box") == true) continue
            val (instance, valueParams) = instanceGenerator.generateTopLevelFunctionCall(func) ?: continue
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
        res: MutableList<KtExpression>
    ): List<KtExpression> {
        val currentModule = file.getBoxFuncs()?.first().getDeclarationDescriptorIncludingConstructors(ctx)?.module
        val javaClassesFromCurrentModule = file.getAllPSIChildrenOfType<KtExpression>()
            .mapNotNull { it.getType(ctx)?.constructor?.declarationDescriptor }
            .toSet()
            .filter { it is JavaClassDescriptor && it.module == currentModule }
        val classes = file.getAllPSIChildrenOfType<KtClassOrObject>()
            .filter { it.name != null }
            .filterNot { it.parents.any { it is KtNamedFunction } }//.filter { it.isTopLevel() }
        val classesDescriptors =
            classes.mapNotNull { it.getDeclarationDescriptorIncludingConstructors(ctx) as? ClassDescriptor }
        for (klassDescriptor in classesDescriptors + javaClassesFromCurrentModule) {
            val openFuncsAndProps = mutableListOf<String>()
            val genRes =
                instanceGenerator.classInstanceGenerator.generateRandomInstanceOfUserClass(klassDescriptor.defaultType)
                    ?: null to null
            val instanceOfKlass = genRes.first ?: continue
            res.add(instanceOfKlass as KtExpression)
            var klassType = genRes.second
            if (klassType == null) {
                klassType = randomTypeGenerator.generateType(instanceOfKlass.text.substringBefore('(')) ?: continue
            }
            filterOpenFuncsAndPropsFromDecl(instanceOfKlass, klassType).forEach { openFuncsAndProps.add(it) }
            openFuncsAndProps
                .mapNotNull {
                    if (it.startsWith("CoBj"))
                        Factory.psiFactory.createExpressionIfPossible("${klassDescriptor.name}.${it.substringAfter("CoBj")}")
                    else
                        Factory.psiFactory.createExpressionIfPossible(it)
                }
                .forEach { res.add(it) }
        }
        return res
    }

    private fun filterOpenFuncsAndPropsFromDecl(
        parentInstance: PsiElement,
        classType: KotlinType
    ): List<String> {
        val openFuncsAndProps = mutableListOf<String>()
        for (desc in classType.memberScope.getDescriptorsFiltered { true }) {
            if (desc is SimpleFunctionDescriptor && desc.visibility.isPublicAPI && desc.extensionReceiverParameter == null) {
                if (desc.name.asString() !in noNeedFunctions)
                    instanceGenerator.generateFunctionCall(desc)?.let {
                        openFuncsAndProps.add(
                            "${parentInstance.text}.${it.text}"
                        )
                    }
            } else if (desc is PropertyDescriptor && desc.visibility.isPublicAPI) {
                openFuncsAndProps.add("${parentInstance.text}.${desc.name}")
            }
        }
        return openFuncsAndProps
    }

    private fun generateTypes(file: KtFile, resToStr: String): List<Triple<KtExpression, String, KotlinType?>> {
        val func = Factory.psiFactory.createFunction("fun usageExamples(){\n$resToStr\n}")
        val newFile = Factory.psiFactory.createFile(file.text + "\n\n" + func.text)
        val ctx = PSICreator.analyze(newFile) ?: return listOf()
        val myFunc = newFile.findFunByName("usageExamples")!!
        return myFunc.getAllPSIChildrenOfType<KtExpression>()
            .filter { it.parent == myFunc.bodyBlockExpression }
            .map { Triple(it, it.text, it.getType(ctx)) }
            .filter { it.third != null }
    }

    private fun List<Triple<KtExpression, String, KotlinType?>>.getValueOfType(type: String): KtExpression? =
        this.filter { it.third?.let { it.toString() == type || it.toString() == type.substringBefore('?') } ?: false }
            .randomOrNull()?.first

    private val noNeedFunctions = listOf("equals", "hashCode", "toString")
}