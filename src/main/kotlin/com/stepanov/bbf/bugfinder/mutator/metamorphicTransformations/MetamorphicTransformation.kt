package com.stepanov.bbf.bugfinder.mutator.metamorphicTransformations

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.executor.checkers.MutationChecker
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.reduktor.parser.PSICreator
import org.apache.log4j.Logger
import org.jetbrains.kotlin.psi.KtPsiFactory
import org.jetbrains.kotlin.resolve.BindingContext
import kotlin.reflect.KClass

abstract class MetamorphicTransformation {

    protected val log: Logger = Logger.getLogger("bugFinderLogger")

    protected fun addAfterMutationPoint(mutationPoint: PsiElement, psiElement: PsiElement): PsiElement {
        val result = checker.addAfter(mutationPoint, psiElement)
        if (result == mutationPoint)
            log.info("Couldn't apply ${this::class.java} mutation: ${psiElement.text}")
        else
            log.info("Apply ${this::class.java} mutation: ${psiElement.text}")
        return result
    }

    protected inline fun addAfterMutationPoint(mutationPoint: PsiElement, create: (KtPsiFactory)-> PsiElement?): PsiElement {
        val psiElement = create(Factory.psiFactory) ?: return mutationPoint
        return addAfterMutationPoint(mutationPoint, psiElement)
    }

    abstract fun transform(
        mutationPoint: PsiElement,
        scope: HashMap<Variable, MutableList<String>>,
        expected: Boolean
    )

    companion object {
        lateinit var checker: MutationChecker
        val file: PsiFile
            get() = checker.curFile.psiFile
        val project: Project
            get() = checker.project
        lateinit var originalProject: Project
        var ctx: BindingContext? = null
        internal val log = Logger.getLogger("mutatorLogger")

        private val mutations = mutableListOf(
            AddCasts() to 75,
            AddLoop() to 75,
            AddDeadCodeTransformation() to 100,
            //AddRandomClass() to 100
            AddFunInvocations() to 50,
            AddIf() to 80,
            AddExpressionsWithVariables() to 75,
            AddTryExpression() to 50,
            AddVariablesToScope() to 60,
            RunLetTransformation() to 75,
            EquivalentTransformation() to 60
        )

        val defaultMutations get() = mutations.toMutableList()

        fun <T : MetamorphicTransformation> removeMutation(mutation: KClass<T>): MutableList<Pair<MetamorphicTransformation, Int>> {
            mutations.find { it.first::class == mutation }?.let { mutations.remove(it) }
            return defaultMutations
        }

        fun restoreMutations() {
            val restored = listOf(
                AddCasts() to 75,
                AddLoop() to 75,
                AddDeadCodeTransformation() to 100,
                //AddRandomClass() to 100
                AddFunInvocations() to 50,
                AddIf() to 80,
                AddExpressionsWithVariables() to 75,
                AddTryExpression() to 50,
                AddVariablesToScope() to 60
            )
            mutations.clear()
            mutations.addAll(restored)
        }

        fun updateCtx() {
            ctx = PSICreator.analyze(file, project)
        }
    }
}