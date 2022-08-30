package com.stepanov.bbf.bugfinder.mutator

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiWhiteSpace
import com.intellij.psi.impl.source.tree.LeafPsiElement
import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.executor.project.LANGUAGE
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.RandomInstancesGenerator
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.typeGenerators.RandomTypeGenerator
import com.stepanov.bbf.bugfinder.mutator.metamorphicTransformations.*
import com.stepanov.bbf.bugfinder.mutator.metamorphicTransformations.MetamorphicTransformation.Companion.defaultMutations
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.mutator.transformations.util.ScopeCalculator
import com.stepanov.bbf.bugfinder.util.*
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.util.getAllChildren
import com.stepanov.bbf.reduktor.util.replaceThis
import org.apache.log4j.Logger
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.resolve.ImportPath
import kotlin.random.Random
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
        val fileBackup = checker.curFile.psiFile.copy() as PsiFile

        file.addToTheTop(Factory.psiFactory.createImportDirective(ImportPath.fromString("kotlin.collections.*")))

        file.addToTheTop(Factory.psiFactory.createImportDirective(ImportPath.fromString("java.util.*")))
        file.addToTheTop(Factory.psiFactory.createImportDirective(ImportPath.fromString("kotlin.random.*")))
        file.addToTheTop(Factory.psiFactory.createImportDirective(ImportPath.fromString("java.nio.*")))
        file.addToTheTop(Factory.psiFactory.createImportDirective(ImportPath.fromString("java.nio.file.*")))
        file.addToTheTop(Factory.psiFactory.createImportDirective(ImportPath.fromString("java.io.*")))

        val ctx = PSICreator.analyze(ktFile, project) ?: return
        rig = RandomInstancesGenerator(ktFile, ctx)
        project.addMainInCurrent()
        checker.trace(project)
        originalProject = project.copy()
        MetamorphicTransformation.originalProject = originalProject
        var mutationPoint = chooseMutationPoint() ?: return
//            file.getAllPSIChildrenOfType<KtProperty> { !it.isMember }
//                .randomOrNull() ?: return
        //(mutationPoint as? KtExpression)?.isUsedAsExpression(ctx)
        val mutationPointBackup = mutationPoint.copy()
        log.info("Mutation point: ${mutationPoint.text}")
        val scope: HashMap<Variable, MutableList<String>> = profileScope(mutationPoint)
        // checker.curFile.changePsiFile(originalProject.files[0].psiFile, true)
        mutationPoint = checkNotNull(file.find(mutationPoint))
        mutate(mutationPoint, scope)
        file.getAllChildren().find { it.text.take(mutationPointBackup.text.length) == mutationPointBackup.text }
            ?.replaceThis(mutationPoint)
            ?: log.info("Mutation point was changed by mutations ${mutationPoint.text}")
        CompilerArgs.isMetamorphicMode = true
        checker.checkCompilingWithBugSaving(project, checker.curFile, originalProject)
    }

    private fun String.removeNewLines() = this.replace(Regex("""[\r\n]"""), "")

    private fun profileScope(mutationPoint: PsiElement): HashMap<Variable, MutableList<String>> {
        val ktFile = file as KtFile
        val processedScope = ScopeCalculator(ktFile, project).run {
            ScopeCalculator.processScope(rig, calcScope(mutationPoint).shuffled(), generatedFunCalls)
        }

        val variables = processedScope
            .filter { it.psiElement is KtNameReferenceExpression }
            .map { Variable(it.psiElement.text, it.type, it.psiElement) }
        val block = Factory.psiFactory.createExpression("kotlin.run {\n ${
            variables.joinToString("\n") {
                val value = it.name; "println(\"${value}EQUALS_PROFILER\$$value\")"
            }
        }\n}")

        val profiling = checker.addAfter(mutationPoint, block, false)

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
        if (profiling != null && profiling.text.removeNewLines() != mutationPoint.text.removeNewLines())
            file.getAllChildren().filter { it.text.contains("kotlin.run") }.find { it.text == profiling.text }
                ?.replaceThis(Factory.psiFactory.createWhiteSpace("\n"))
        return variablesToValues
    }

    private fun chooseMutationPoint(): PsiElement? {
        return file.getAllPSIChildrenOfType<KtBlockExpression>()
            .flatMap { it.children.filterNot { it is PsiWhiteSpace || it is LeafPsiElement || it is KtReturnExpression } }
            .randomOrNull()
    }

    private fun mutate(mutationPoint: PsiElement, scope: HashMap<Variable, MutableList<String>>) {
        val expected = Random.nextBoolean()
        executeMutations(mutationPoint, scope, expected, defaultMutations)
        MetamorphicTransformation.restoreMutations()
    }
}