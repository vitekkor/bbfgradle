package com.stepanov.bbf.bugfinder.mutator.metamorphicTransformations

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.stepanov.bbf.bugfinder.executor.checkers.MutationChecker
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.mutator.MetamorphicMutator
import com.stepanov.bbf.reduktor.parser.PSICreator
import org.apache.log4j.Logger
import org.jetbrains.kotlin.resolve.BindingContext
import kotlin.reflect.KClass

abstract class MetamorphicTransformation {

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
        var ctx: BindingContext? = null
        internal val log = Logger.getLogger("mutatorLogger")

        private val mutations = mutableListOf(
            //AddCasts() to 75,
            AddLoop() to 75,
            //AddRandomClass() to 100
            AddFunInvocations() to 50,
            AddIf() to 80,
            AddExpressionsWithVariables() to 75,
            AddTryExpression() to 50,
            AddVariablesToScope() to 60
        )

        val defaultMutations get() = mutations.toMutableList()

        fun <T : MetamorphicTransformation> removeMutation(mutation: KClass<T>): MutableList<Pair<MetamorphicTransformation, Int>> {
            mutations.find { it.first::class == mutation }?.let { mutations.remove(it) }
            return defaultMutations
        }

        fun restoreMutations() {
            val restored = listOf(
                //AddCasts() to 75,
                AddLoop() to 75,
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