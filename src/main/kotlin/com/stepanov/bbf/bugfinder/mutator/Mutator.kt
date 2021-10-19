package com.stepanov.bbf.bugfinder.mutator

import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.executor.project.LANGUAGE
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.typeGenerators.RandomTypeGenerator
import com.stepanov.bbf.bugfinder.mutator.javaTransformations.*
import com.stepanov.bbf.bugfinder.mutator.transformations.*
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.LocalTCE
import com.stepanov.bbf.bugfinder.mutator.transformations.util.ExpressionReplacer
import com.stepanov.bbf.bugfinder.util.getTrue
import com.stepanov.bbf.reduktor.parser.PSICreator
import org.apache.log4j.Logger
import org.jetbrains.kotlin.psi.KtFile
import kotlin.math.abs
import kotlin.math.ln
import kotlin.math.sqrt
import kotlin.random.Random
import kotlin.system.exitProcess

class Mutator(val project: Project) {

    fun executeMutation(t: Transformation, probPercentage: Int = 50) {
        if (Random.nextInt(0, 100) < probPercentage) {
            //Update ctx
            Transformation.updateCtx()
            Transformation.ctx ?: return
            log.debug("Cur transformation ${t::class.simpleName}")
//            try {
            t.transform()
            checker.curFile.changePsiFile(checker.curFile.text)
//            } catch (e: Exception) {
//                log.debug("Exception ${e.localizedMessage}\n${e.stackTrace.toList().joinToString("\n") { "$it" }}")
//                exitProcess(1)
//            } catch (e: Error) {
//                log.debug("Error ${e.localizedMessage}\n${e.stackTrace.toList().joinToString("\n") { "$it" }}")
//                exitProcess(1)
//            }
        }
    }


    fun startMutate() {
        for (bbfFile in project.files) {
            log.debug("Mutation of ${bbfFile.name} started")
            Transformation.checker.curFile = bbfFile
            when (bbfFile.getLanguage()) {
                //LANGUAGE.JAVA -> startJavaMutations()
                LANGUAGE.KOTLIN ->
                    if (CompilerArgs.isGuidedByCoverage) {
                        startGuidedKotlinMutations()
                    } else {
                        startKotlinMutations()
                    }
            }
            log.debug("End")
        }
    }


    //TODO!! Implement java mutations
    private fun startJavaMutations() {
        println("STARTING JAVA MUTATIONS")
        executeMutation(ChangeRandomJavaASTNodesFromAnotherTrees(), 100)
        println("END JAVA MUTATIONS")
        log.debug("Verify = ${verify()}")
        return
    }

    private fun startGuidedKotlinMutations() {
        val mutationList = mutableListOf(
            ChangeRandomASTNodesFromAnotherTrees,
            AddTryExpression,
            AddRandomControlStatements,
            AddLoop,
            AddLabels,
            ChangeRandomASTNodes,
            ChangeTypes,
            //ChangeRandomASTNodes() to 25,
            LocalTCE,
            //TCETransformation to 25, //TODO ???
            AddFunInvocations,
            AddCasts,
            AddDefaultValueToArg,
            AddCallableReference,
            AddRandomComponent(),
            ExpressionReplacer(),
            ChangeArgToAnotherValue(),
            ExpressionObfuscator,
            //ShuffleFunctionArguments(),
            AddPossibleModifiers(),
            AddInheritance(),
            ReplaceDotExpression(),
            AddExpressionToLoop()
        )
        val KOEF = 10.0
        val EPSILON = 80
        val k = 100.0 / mutationList.size
        val directedMutations =
            mutationList.mapIndexed { i, m -> DirectedMutation(m, mutableListOf(), k) }.toMutableList()
        checker.currentScore = 0
        repeat(Random.nextInt(200, 300)) { iteration ->
            val transformationToExecute =
                if (Random.getTrue(EPSILON)) {
                    val transformationsToMetrics = directedMutations.map { tr ->
                        val averageScore = if (tr.scores.isEmpty()) 0.0 else tr.scores.average()
                        val div = if (tr.scores.isEmpty()) 1 else tr.scores.size
                        val metric = averageScore + sqrt(2 * ln(iteration.toDouble()) / div)
                        tr to metric
                    }
//                    transformationsToMetrics.map {
//                        "${it.first.transformation}".substringAfterLast('.').substringBefore('@') to it.second
//                    }.forEach(::println)
                    val maxMetric = transformationsToMetrics.maxOf { it.second }
                    transformationsToMetrics.filter { abs(it.second - maxMetric) <= 0.01 }.randomOrNull()?.first ?: return@repeat
                } else {
                    directedMutations.randomOrNull() ?: return@repeat
                }
            println("Mutation ${transformationToExecute.transformation} started")
            executeMutation(transformationToExecute.transformation, 100)
            val score = checker.currentScore / KOEF
            transformationToExecute.scores.add(score)
            println("SCORE = $score")
            println("------------------------------")
//            directedMutations.map {
//                Triple(
//                    "${it.transformation}".substringAfterLast('.').substringBefore('@'),
//                    it.probability,
//                    it.scores
//                )
//            }.forEach(::println)
            println("------------------------------")
            //Reset scores
            checker.currentScore = 0
        }
        println("LOL---------------------------")
        directedMutations.sortedBy { it.scores.size }.map {
            Pair(
                "${it.transformation}".substringAfterLast('.').substringBefore('@'),
                it.scores
            )
        }.forEach(::println)
        println("LOL---------------------------")
        exitProcess(0)
    }

    private fun startKotlinMutations() {
        val ktx = PSICreator.analyze(checker.curFile.psiFile, checker.project) ?: return
        val ktFile = checker.curFile.psiFile as KtFile
        RandomTypeGenerator.setFileAndContext(ktFile, ktx)
        if (!checker.checkCompiling()) return
        val mut1 = listOf(
            ChangeRandomASTNodesFromAnotherTrees to 75,
            AddTryExpression to 50,
            AddRandomControlStatements to 50,
            AddLoop to 75,
            AddLabels to 50,
            ChangeRandomASTNodes to 75,
            ChangeTypes to 75,
            //ChangeRandomASTNodes() to 25,
            LocalTCE to 100,
            //TCETransformation to 25, //TODO ???
            AddFunInvocations to 50,
            AddCasts to 75,
            AddDefaultValueToArg to 50,
            AddCallableReference to 25,
            AddRandomComponent() to 50,
            ExpressionReplacer() to 100,
            ChangeArgToAnotherValue() to 50,
            ExpressionObfuscator to 50,
            //ShuffleFunctionArguments() to 50,
            AddPossibleModifiers() to 50,
            AddInheritance() to 25,
            ReplaceDotExpression() to 50,
            AddExpressionToLoop() to 50
            //AddRandomClass() to 50
        ).shuffled()
        println("BEFORE = $project")
        repeat(100) {
            val randomMutation =
                mut1.random()
            println("EXECUTING ${randomMutation.first}")
            randomMutation.first.transform()
        }
        println("AFTER = $project")
        exitProcess(0)
//        for (i in 0 until Random.nextInt(1, 3)) {
//            mut1.forEach { executeMutation(it.first, it.second) }
//        }
//        BugManager.saveBug(
//            Bug(
//                CompilerArgs.getCompilersList(),
//                "",
//                checker.project,
//                BugType.PERFORMANCE
//            )
//        )
        exitProcess(0)
//        executeMutation(AddTryExpression(), 100)
//        executeMutation(AddRandomControlStatements(), 100)
//        executeMutation(AddLoop(), 100)
//        println("AFTER = ${checker.curFile.psiFile.text}")
//        exitProcess(0)
//        val mut = listOf(
//            ShuffleFunctionArguments() to 50,
//            ExpressionObfuscator() to 75,
//            AddRandomComponent() to 75,
//            AddDefaultValueToArg() to 75,
//            LocalTCE() to 75,
//            AddReificationToTypeParam() to 75,
//            TCETransformation() to 75,
//            ChangeRandomASTNodesFromAnotherTrees() to 75,
//            AddPossibleModifiers() to 50,
//            AddArgumentToFunction() to 75,
//            //AddCallableReference() to 50,
//            AddDefaultValueToArg() to 50,
//            ChangeTypes() to 75,
//            ChangeModifiers() to 50,
//            ChangeRandomASTNodesFromAnotherTrees() to 80,
//            AddTryExpression() to 5,
//            ChangeArgToAnotherValue() to 50,
//            AddInheritance() to 75
//        ).shuffled()
//        for (i in 0 until Random.nextInt(1, 3)) {
//            mut.forEach { executeMutation(it.first, it.second) }
//        }
//        executeMutation(AddRandomNode(), 100)
//        executeMutation(ChangeRandomASTNodesFromAnotherTrees(), 100)
//        executeMutation(AddPossibleModifiers())
//        executeMutation(AddDefaultValueToArg())
//        executeMutation(AddSameFunctions())
//        executeMutation(ChangeModifiers())
//        executeMutation(ChangeTypes(), 75)
//        exitProcess(0)
//        executeMutation(TCETransformation(), 100)
//        executeMutation(ChangeRandomASTNodesFromAnotherTrees(), 100)
        //executeMutation(AddRandomNode(), 100)
        //executeMutation(AddRandomDS(), 100)
//        executeMutation(ChangeRandomASTNodes(), 50)
//        executeMutation(AddPropertiesToClass(), 100)
//        exitProcess(0)
//        executeMutation(AddRandomAnnotation(), 100)
//        exitProcess(0)
//        for (i in 0 until Random.nextInt(1, 3)) {
//            transformations.shuffled().forEach { executeMutation(it.first, it.second) }
//        }
//        //Set of transformations over PSI
//        executeMutation(AddNullabilityTransformer())
//        executeMutation(AddPossibleModifiers())
//        executeMutation(AddReifiedToType())
//        executeMutation(ChangeSmthToExtension())
//        executeMutation(AddDefaultValueToArg())
//        executeMutation(ChangeArgToAnotherValue())
//        executeMutation(ReinitProperties())
//        executeMutation(AddNotNullAssertions())
//        executeMutation(AddBlockToExpression())
//        executeMutation(ChangeOperators())
//        executeMutation(ChangeConstants())
//        executeMutation(ChangeTypes(), 75)
//        executeMutation(ChangeReturnValueToConstant())
//        //executeMutation(RemoveRandomLines())
//        executeMutation(AddBracketsToExpression())
//        executeMutation(ChangeModifiers())
//        executeMutation(AddSameFunctions())
//        executeMutation(ChangeOperatorsToFunInvocations())
//        if (project.files.size > 1) {
//            executeMutation(ShuffleNodes(), 75)
//        } else {
//            executeMutation(ChangeRandomASTNodes(), 75)
//        }
//        executeMutation(AddNodesFromAnotherFiles(), 75)
//        executeMutation(AddFunInvocations(), 75)
//        executeMutation(ChangeRandomLines())
//        executeMutation(ChangeRandomASTNodesFromAnotherTrees(), 75)
//        executeMutation(AddTryExpression())
    }


    private fun isProject() = project.files.size > 1

    private fun verify(): Boolean {
        val res = Transformation.checker.checkCompiling(project)
        if (!res) {
            log.debug("Cant compile project ${project}")
            System.exit(1)
        }
        return res
    }

    //    private val mutations = listOf(
//        AddNullabilityTransformer() to 50,
//        AddPossibleModifiers() to 50,
//        AddReifiedToType() to 50,
//        ChangeSmthToExtension() to 50,
//        AddDefaultValueToArg() to 50,
//        ChangeArgToAnotherValue() to 50,
//        ReinitProperties() to 50,
//        //AddNotNullAssertions() to 50,
//        //AddBlockToExpression() to 50,
//        //ChangeOperators() to 50,
//        //ChangeConstants() to 50,
//        //ChangeTypes() to 75,
//        //ChangeReturnValueToConstant() to 50,
//        //AddBracketsToExpression() to 50,
//        ChangeModifiers() to 50,
//        AddSameFunctions() to 50,
//        //ChangeOperatorsToFunInvocations() to 50,
//        ChangeRandomASTNodes() to 75,
//        //AddFunInvocations() to 75,
//        ChangeRandomLines() to 50,
//        ChangeRandomASTNodesFromAnotherTrees() to 100,
//        //AddTryExpression() to 50,
//        //AddNodesFromAnotherFiles() to 50
//    )
    private val log = Logger.getLogger("bugFinderLogger")
    private val checker
        get() = Transformation.checker

}