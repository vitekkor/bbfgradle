package com.stepanov.bbf.bugfinder.mutator

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.executor.project.LANGUAGE
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.RandomInstancesGenerator
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.typeGenerators.RandomTypeGenerator
import com.stepanov.bbf.bugfinder.mutator.metamorphicTransformations.*
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.mutator.transformations.getPath
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.UsagesSamplesGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.util.ScopeCalculator
import com.stepanov.bbf.bugfinder.util.*
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.util.getAllChildren
import com.stepanov.bbf.reduktor.util.replaceThis
import org.apache.log4j.Logger
import org.jetbrains.kotlin.cfg.getDeclarationDescriptorIncludingConstructors
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.getCallNameExpression
import org.jetbrains.kotlin.resolve.BindingContext
import kotlin.random.Random
import org.jetbrains.kotlin.resolve.calls.callUtil.getType as type

class MetamorphicMutator(val project: Project) {
    private val generatedFunCalls = mutableMapOf<FunctionDescriptor, KtExpression?>()
    private val resultOfExecution = mutableListOf<String>()
    private lateinit var originalProject: Project
    private lateinit var rig: RandomInstancesGenerator

    private val log = Logger.getLogger("bugFinderLogger")
    private val checker
        get() = MetamorphicTransformation.checker
    private val file
        get() = MetamorphicTransformation.file
    private val ctx
        get() = MetamorphicTransformation.ctx

    fun startMutate() {
        for (bbfFile in project.files) {
            log.debug("Metamorphic mutation of ${bbfFile.name} started")
            MetamorphicTransformation.checker.curFile = bbfFile
            MetamorphicTransformation.updateCtx()
            when (bbfFile.getLanguage()) {
                LANGUAGE.KOTLIN -> startKotlinMutations()
                else -> TODO()
            }
            log.debug("End")
        }
    }

    private fun startKotlinMutations() {
        CompilerArgs.isMetamorphicMode = false
        val ktx = PSICreator.analyze(checker.curFile.psiFile, checker.project) ?: return
        val ktFile = checker.curFile.psiFile as KtFile
        RandomTypeGenerator.setFileAndContext(ktFile, ktx)
        if (!checker.checkCompiling()) return

        resultOfExecution.clear()
        val fileBackup = file.copy() as KtFile
        val ctx = PSICreator.analyze(ktFile, project) ?: return
        rig = RandomInstancesGenerator(ktFile, ctx)
        val mutationPoint =
            file.getAllPSIChildrenOfType<KtProperty>().filter { it.text.contains("v4") }.randomOrNull() ?: return
        /*file.getAllPSIChildrenOfType<PsiElement>()
        .find { it.text.trim() == "\"//INSERT_CODE_HERE\"" }!! //TODO GET RANDOM NODE*/

        val scope = profileScope(mutationPoint, ctx)
        val metamorphicMutations = mutate(mutationPoint, scope)
        CompilerArgs.isMetamorphicMode = true
        checker.checkCompilingWithBugSaving(
            project,
            checker.curFile,
            originalProject,
            metamorphicMutations?.getAllChildren() ?: listOf()
        )

        checker.curFile.changePsiFile(fileBackup, genCtx = false)
    }

    private fun profileScope(mutationPoint: PsiElement, ctx: BindingContext): HashMap<Variable, MutableList<String>> {
        val ktFile = file as KtFile
        val processedScope = ScopeCalculator(ktFile, project).run {
            ScopeCalculator.processScope(rig, calcScope(mutationPoint).shuffled(), generatedFunCalls)
        }
        val usages = UsagesSamplesGenerator.generate(ktFile, ctx, project)

        val variables = processedScope
            .filter { it.psiElement is KtNameReferenceExpression }
            .map { Variable(it.psiElement.text, it.type, it.psiElement) }
        val block = Factory.psiFactory.createExpression("kotlin.run {\n ${
            variables.joinToString("\n") {
                val value = it.name; "println(\"${value}EQUALS_PROFILER\$$value\")"
            }
        }\n}")

        //TODO REFACTORING
        val main =
            usages.find { it.first.text.contains("main") }?.first as? KtNamedFunction ?: resolveMainFun(mutationPoint)
            ?: kotlin.run {
                log.debug("Couldn't resolve main function in ${file.getPath()}")
                return hashMapOf()
            }
        file.find(main) ?: kotlin.run { file.add(Factory.psiFactory.createWhiteSpace("\n\n")); file.add(main) }

        originalProject = project.copy()

        val profiling = mutationPoint.addAfterThisWithWhitespace(block, "\n")
        /*file.getAllPSIChildrenOfType<PsiElement>()
            .find { it.text.trim() == "//MUTATION_POINT" }!!.replaceThis(mutationPoint)*/

        val profiled = checker.compileAndGetResult().split("\n")
        val variablesToValues = hashMapOf<Variable, MutableList<String>>()
        /*variablesToValues.putAll(processedScope
            .filter { it.psiElement is KtNameReferenceExpression }
            .associate { it.psiElement.text to mutableListOf() })*/
        for (out in profiled) {
            if (out.contains(Regex("""OUTPUTSTREAM|ERRORSTREAM"""))) continue
            val variableToValue = out.split("EQUALS_PROFILER")
            if (variableToValue.size != 2) {
                resultOfExecution.add(out) //???
                continue
            }
            val variable = variables.find { it.name == variableToValue[0] } ?: continue
            variablesToValues.getOrPut(variable) { mutableListOf() }
                .add(variableToValue[1].removeSuffix("\r"))
        }
        profiling.replaceThis(Factory.psiFactory.createWhiteSpace("\n"))
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

    private fun mutate(mutationPoint: PsiElement, scope: HashMap<Variable, MutableList<String>>): PsiElement? {
        val expected = false//Random.nextBoolean()
        val predicate = synthesisPredicate(scope, expected, 2)
        val thenStatement = synthesisIfBody(mutationPoint, scope, expected)
        if (thenStatement.isEmpty()) {
            log.debug("Metamorphic mutation is empty.")
            return null
        }
        val ifStatement = with(Factory.psiFactory) {
            createExpression("if ($predicate) ${createBlock(thenStatement).text}")
            //createIf(createExpression(predicate.toString()), createBlock(thenStatement))
        }
        return mutationPoint.addAfterThisWithWhitespace(ifStatement, "\n")
        // TODO val res = checker.compileAndGetResult().split("\n")
    }

    private fun generateVariablesExpression(
        scope: HashMap<Variable, MutableList<String>>,
        variable1: Variable,
        variable2: Variable? = null,
        expected: Boolean
    ): String {
        //TODO blya pizdec
        if (variable2 == null) {
            return generateOneVariableExpression(scope, variable1, expected)
        } else {
            val values1 = scope[variable1]!!
            val values2 = scope[variable2]!!
            if (variable1.type.name == "Int" && variable2.type.name == "Int") {
                val value1 = if (Random.nextBoolean()) values1.maxOf { it.toInt() } else values1.minOf { it.toInt() }
                val value2 = if (Random.nextBoolean()) values2.maxOf { it.toInt() } else values2.minOf { it.toInt() }
                val operator = when (value1.compareTo(value2)) {
                    0 -> "!="
                    1 -> "<"
                    else -> ">"
                }
                return "$variable1 $operator $variable2"
            }
            if (variable1.type.name == "String" && variable2.type.name == "String") {
                return "$variable1 + $variable2 == $variable2 + $variable1" //TODO
            }
            if (variable1.type.name == "Int" && variable2.type.name == "String" || variable1.type.name == "String" && variable2.type.name == "Int") {
                return "$variable1.toString() + $variable2.toString() == $variable2.toString() + $variable1.toString()"
            }
        }
        return ""
    }
}