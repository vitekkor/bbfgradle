package com.stepanov.bbf.bugfinder.mutator

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiWhiteSpace
import com.intellij.psi.impl.source.tree.LeafPsiElement
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
import com.stepanov.bbf.bugfinder.util.addAfterThisWithWhitespace
import com.stepanov.bbf.bugfinder.util.addToTheTop
import com.stepanov.bbf.bugfinder.util.flatMap
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.util.getAllChildren
import com.stepanov.bbf.reduktor.util.replaceThis
import org.apache.log4j.Logger
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.ImportPath
import kotlin.system.exitProcess

class MetamorphicMutator(val project: Project) {
    private val generatedFunCalls = mutableMapOf<FunctionDescriptor, KtExpression?>()
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
                else -> {
                    exitProcess(0)
                }//TODO()
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

        file.addToTheTop(Factory.psiFactory.createImportDirective(ImportPath.fromString("kotlin.collections.*")))

        file.addToTheTop(Factory.psiFactory.createImportDirective(ImportPath.fromString("java.util.*")))
        file.addToTheTop(Factory.psiFactory.createImportDirective(ImportPath.fromString("kotlin.random.*")))

        project.addMainInCurrent()

        val ctx = PSICreator.analyze(ktFile, project) ?: return
        rig = RandomInstancesGenerator(ktFile, ctx)
        val mutationPoint =
            file.getAllPSIChildrenOfType<KtBlockExpression>()
                .flatMap { it.children.filterNot { it is PsiWhiteSpace || it is LeafPsiElement || it is KtReturnExpression } }
                .randomOrNull() ?: return
//            file.getAllPSIChildrenOfType<KtProperty> { !it.isMember }
//                .randomOrNull() ?: return

        val scope: HashMap<Variable, MutableList<String>> = profileScope(mutationPoint, ctx)
        val metamorphicMutations = mutate(mutationPoint, scope) ?: return
        CompilerArgs.isMetamorphicMode = true
        checker.checkCompilingWithBugSaving(
            project,
            checker.curFile,
            originalProject,
            metamorphicMutations.getAllChildren().toMutableList().apply { add(metamorphicMutations) }
        )

        //checker.curFile.changePsiFile(fileBackup, genCtx = false)
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

        originalProject = project.copy()

        val profiling = mutationPoint.addAfterThisWithWhitespace(block, "\n")

        val profiled = checker.compileAndGetResult().split("\n")
        val variablesToValues = hashMapOf<Variable, MutableList<String>>()
        for (out in profiled) {
            if (out.contains(Regex("""OUTPUTSTREAM|ERRORSTREAM"""))) continue
            val variableToValue = out.split("EQUALS_PROFILER")
            if (variableToValue.size != 2) {
                continue
            }
            val variable = variables.find { it.name == variableToValue[0] } ?: continue
            variablesToValues.getOrPut(variable) { mutableListOf() }
                .add(variableToValue[1].removeSuffix("\r"))
        }
        profiling.replaceThis(Factory.psiFactory.createWhiteSpace("\n"))
        return variablesToValues
    }

    private fun mutate(mutationPoint: PsiElement, scope: HashMap<Variable, MutableList<String>>): PsiElement? {
        val expected = false//Random.nextBoolean()
        val predicate = synthesisPredicate(scope, expected, 2)
        val thenStatement = synthesisIfBody(mutationPoint, scope, expected)
        restoreMutations()
        if (thenStatement.isEmpty()) {
            log.debug("Metamorphic mutation is empty.")
            return null
        }
        val ifStatement = with(Factory.psiFactory) {
            createExpression("if ($predicate) ${createBlock(thenStatement).text}")
        }
        val mutated = mutationPoint.addAfterThisWithWhitespace(ifStatement, "\n")
        return mutated
    }
}