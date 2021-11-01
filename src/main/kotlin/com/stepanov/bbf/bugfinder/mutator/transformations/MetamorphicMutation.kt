package com.stepanov.bbf.bugfinder.mutator.transformations

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.RandomInstancesGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.UsagesSamplesGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.util.ScopeCalculator
import com.stepanov.bbf.bugfinder.util.*
import org.jetbrains.kotlin.resolve.calls.callUtil.getType as type
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.util.replaceThis
import org.jetbrains.kotlin.cfg.getDeclarationDescriptorIncludingConstructors
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.getCallNameExpression
import org.jetbrains.kotlin.resolve.BindingContext

class MetamorphicMutation : Transformation() {
    private val generatedFunCalls = mutableMapOf<FunctionDescriptor, KtExpression?>()
    private lateinit var rig: RandomInstancesGenerator

    override fun transform() {
        val ktFile = file as KtFile
        val fileBackup = file.copy() as KtFile
        val ctx = PSICreator.analyze(ktFile, project) ?: return
        rig = RandomInstancesGenerator(ktFile, ctx)
        val mutationPoint = file.getAllPSIChildrenOfType<PsiElement>()
            .find { it.text.trim() == "//INSERT_CODE_HERE" }!! //TODO GET RANDOM NODE

        val scope = profileScope(mutationPoint, ctx)

        checker.curFile.changePsiFile(fileBackup, genCtx = false)
    }

    private fun profileScope(mutationPoint: PsiElement, ctx: BindingContext): HashMap<String, MutableList<String>> {
        val ktFile = file as KtFile
        val processedScope = ScopeCalculator(ktFile, project).run {
            ScopeCalculator.processScope(rig, calcScope(mutationPoint).shuffled(), generatedFunCalls)
        }
        val usages = UsagesSamplesGenerator.generate(ktFile, ctx, project)

        val block = Factory.psiFactory.createExpression("kotlin.run {\n ${
            processedScope
                .filter { it.psiElement is KtNameReferenceExpression }
                .joinToString("\n") { val value = it.psiElement.text; "println(\"$value=\$$value\")" }
        }\n}")

        mutationPoint.replaceThis(block) //TODO get back mutationPoint in file
        /*file.getAllPSIChildrenOfType<PsiElement>()
            .find { it.text.trim() == "//MUTATION_POINT" }!!.replaceThis(mutationPoint)*/

        //TODO REFACTORING
        val main = usages.find { it.first.text.contains("main") }?.first as? KtNamedFunction ?: resolveMainFun(block)
        ?: kotlin.run {
            log.debug("Couldn't resolve main function in ${file.getPath()}")
            return hashMapOf()
        }
        file.find(main) ?: kotlin.run { file.add(Factory.psiFactory.createWhiteSpace("\n\n")); file.add(main) }


        val profiled = checker.compileAndGetResult().split("\n")
        val variablesToValues = hashMapOf<String, MutableList<String>>()
        for (out in profiled) {
            if (out.contains(Regex("""OUTPUTSTREAM|ERRORSTREAM"""))) continue
            val variableToValue = out.split("=")
            variablesToValues.getOrDefault(variableToValue[0], mutableListOf()).add(variableToValue[1])
        }
        block.replaceThis(mutationPoint)
        return variablesToValues
    }

    private fun resolveMainFun(insertedBlock: PsiElement): KtNamedFunction? {
        var parent: PsiElement = insertedBlock
        while (parent !is KtNamedFunction) { //TODO
            parent = parent.parent
        }
        val usages = file.getAllPSIChildrenOfType<KtCallExpression>().filter {
            it.getCallNameExpression()?.text == parent.name && it.valueArguments.map { vA ->
                vA.getArgumentExpression()?.getType(ctx!!)
            }.containsAll(parent.valueParameters.map { vA -> vA.type(ctx!!) })
        }
        if (usages.isEmpty()) return null
        usages.random().also { //TODO REFACTORING
            return resolveMainFun(it) ?: kotlin.run {
                val call =
                    rig.generateFunctionCall(parent.getDeclarationDescriptorIncludingConstructors(ctx!!) as FunctionDescriptor)
                        ?: return null
                Factory.psiFactory.createFunction("fun main(args: Array<String>){println(\"Result of exec=\${${call.text}}\")})")
            }
        }
    }

}