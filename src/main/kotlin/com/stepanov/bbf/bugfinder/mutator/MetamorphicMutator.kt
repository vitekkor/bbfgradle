package com.stepanov.bbf.bugfinder.mutator

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.executor.project.LANGUAGE
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.RandomInstancesGenerator
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.typeGenerators.RandomTypeGenerator
import com.stepanov.bbf.bugfinder.mutator.metamorphicTransformations.AddCasts
import com.stepanov.bbf.bugfinder.mutator.metamorphicTransformations.MetamorphicTransformation
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.mutator.transformations.Transformation
import com.stepanov.bbf.bugfinder.mutator.transformations.getPath
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.UsagesSamplesGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.util.ScopeCalculator
import com.stepanov.bbf.bugfinder.util.*
import com.stepanov.bbf.reduktor.parser.PSICreator
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
    private lateinit var rig: RandomInstancesGenerator

    private val log = Logger.getLogger("bugFinderLogger")
    private val checker
        get() = Transformation.checker
    private val file
        get() = Transformation.file
    private val ctx
        get() = Transformation.ctx

    fun startMutate() {
        for (bbfFile in project.files) {
            log.debug("Metamorphic mutation of ${bbfFile.name} started")
            Transformation.checker.curFile = bbfFile
            when (bbfFile.getLanguage()) {
                LANGUAGE.KOTLIN -> startKotlinMutations()
                else -> TODO()
            }
            log.debug("End")
        }
    }

    private fun startKotlinMutations() {
        val ktx = PSICreator.analyze(checker.curFile.psiFile, checker.project) ?: return
        val ktFile = checker.curFile.psiFile as KtFile
        RandomTypeGenerator.setFileAndContext(ktFile, ktx)
        if (!checker.checkCompiling()) return

        resultOfExecution.clear()
        val fileBackup = file.copy() as KtFile
        val ctx = PSICreator.analyze(ktFile, project) ?: return
        rig = RandomInstancesGenerator(ktFile, ctx)
        val mutationPoint = file.getAllPSIChildrenOfType<PsiElement>()
            .find { it.text.trim() == "//INSERT_CODE_HERE" }!! //TODO GET RANDOM NODE

        val scope = profileScope(mutationPoint, ctx) //TODO Specify the type of variable
        mutate(mutationPoint, scope)


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
                .joinToString("\n") {
                    val value = it.psiElement.text; "println(\"${value}EQUALS_PROFILER\$$value\")"
                }
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
            variablesToValues.getOrPut(variableToValue[0]) { mutableListOf() }
                .add(variableToValue[1].removeSuffix("\r"))
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

    private fun mutate(mutationPoint: PsiElement, scope: HashMap<String, MutableList<String>>) {
        val expected = false//Random.nextBoolean()
        val predicate = synthesisPredicate(scope, expected, 2)
        val thenStatement = synthesisIfBody(mutationPoint, scope, expected)
        val ifStatement = with(Factory.psiFactory) {
            createExpression("if ($predicate) ${createBlock(thenStatement).text}")
            //createIf(createExpression(predicate.toString()), createBlock(thenStatement))
        }
        mutationPoint.replaceThis(ifStatement)
        // TODO val res = checker.compileAndGetResult().split("\n")
    }

    private fun executeMutation(
        t: MetamorphicTransformation,
        probPercentage: Int = 50,
        mutationPoint: PsiElement,
        scope: HashMap<String, MutableList<String>>,
        expected: Boolean
    ) {
        if (Random.nextInt(0, 100) < probPercentage) {
            //Update ctx
            MetamorphicTransformation.updateCtx()
            MetamorphicTransformation.ctx ?: return
            t.transform(mutationPoint, scope, expected)
        }
    }

    private fun synthesisIfBody(
        mutationPoint: PsiElement,
        scope: HashMap<String, MutableList<String>>,
        expected: Boolean
    ): String {
        val body = StringBuilder()
        val mut1 = listOf(
            AddCasts() to 100
        ).shuffled()
        //for (i in 0 until Random.nextInt(1, 3)) {
        mut1.forEach { executeMutation(it.first, it.second, mutationPoint, scope, expected) }
        //}
        /*val query = ArrayList<String>()
        if (expected) {
            //TODO
        } else {
            query.addAll(generatedFunCalls.entries.mapNotNull { it.value?.text })
            if (Random.nextBoolean())
                query.addAll(
                    file.getAllPSIChildrenOfType<KtNamedFunction>()
                        .mapNotNull {
                            (it.getDeclarationDescriptorIncludingConstructors(ctx!!) as? FunctionDescriptor)?.let { it1 -> //TODO refactor
                                rig.generateFunctionCall(
                                    it1
                                )?.text
                            }
                        })
            *//* if (Random.nextBoolean())
                 query.addAll(getCasts())*//*
            while (query.isNotEmpty()) {
                val expression = query.random()
                query.remove(expression)
                body.append(expression)
                body.appendLine()
            }
            for (i in 0..Random.nextInt(10)) {

                //rig.generate
            }
        }*/
        return body.toString()
    }

    private fun generateVariableName(length: Int): String {
        val result = StringBuilder()
        val characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        for (i in 0 until length)
            if (i == 0)
                result.append(characters[Random.nextInt(characters.length - 10)])
            else
                result.append(characters[Random.nextInt(characters.length)])
        return result.toString()
    }

    private fun synthesisPredicate(
        scope: HashMap<String, MutableList<String>>,
        expected: Boolean,
        depth: Int
    ): Expression {
        if (depth == 0)
            return synthesisAtomic(scope, expected)
        return when (Random.nextInt(4)) {
            0 -> synthesisNegation(scope, expected, depth)
            1 -> synthesisConjunctionDisjunction(scope, expected, depth, true)
            2 -> synthesisConjunctionDisjunction(scope, expected, depth, false)
            else -> synthesisAtomic(scope, expected)
        }
    }

    private fun synthesisAtomic(scope: HashMap<String, MutableList<String>>, expected: Boolean): Expression {
        //TODO Support more types
        if (expected) {
            val variable = scope.keys.randomOrNull() ?: return Expression("true")
            val values = scope[variable]!!
            val maxOrMin = Random.nextBoolean()
            val value = if (maxOrMin) values.maxOf { it.toInt() } else values.minOf { it.toInt() }
            val operator = if (maxOrMin) "<" else ">="
            return Expression("$variable $operator $value")
        } else {
            val variable1 = scope.keys.randomOrNull() ?: return Expression("false")
            val variable2 = scope.keys.randomOrNull() ?: return Expression("false")
            if (variable1 == variable2) return Expression("$variable1 == $variable2")
            val values1 = scope[variable1]!!
            val values2 = scope[variable2]!!
            val value1 = if (Random.nextBoolean()) values1.maxOf { it.toInt() } else values1.minOf { it.toInt() }
            val value2 = if (Random.nextBoolean()) values2.maxOf { it.toInt() } else values2.minOf { it.toInt() }
            val operator = when (value1.compareTo(value2)) {
                0 -> "!="
                1 -> "<"
                else -> ">"
            }
            return Expression("$variable1 $operator $variable2")
        }
    }

    private fun synthesisNegation(
        scope: HashMap<String, MutableList<String>>,
        expected: Boolean,
        depth: Int
    ): Expression = Expression("!", synthesisPredicate(scope, !expected, depth - 1))

    class Expression(val s: String, val left: Expression? = null, val right: Expression? = null) {
        override fun toString(): String {
            if (right == null && left == null)
                return s
            return if (right != null) "($left) $s ($right)" else "$s($left)"
        }
    }

    private fun synthesisConjunctionDisjunction(
        scope: HashMap<String, MutableList<String>>,
        expected: Boolean,
        depth: Int,
        conjunction: Boolean
    ): Expression {
        val left: Boolean
        val right: Boolean
        if (conjunction && expected || !conjunction && !expected) {
            left = true
            right = true
        } else if (Random.nextBoolean()) {
            left = true
            right = false
        } else {
            left = false
            right = Random.nextBoolean()
        }
        val leftPred = synthesisPredicate(scope, left, depth - 1)
        val rightPred = synthesisPredicate(scope, right, depth - 1)
        return Expression(if (conjunction) "&&" else "||", leftPred, rightPred)
    }
}