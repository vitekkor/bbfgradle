package com.stepanov.bbf.bugfinder

import com.intellij.psi.PsiFile
import com.stepanov.bbf.bugfinder.executor.CommonCompiler
import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.executor.checkers.MetamorphicMutationChecker
import com.stepanov.bbf.bugfinder.executor.checkers.MutationChecker
import com.stepanov.bbf.bugfinder.executor.project.BBFFile
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.mutator.MetamorphicMutator
import com.stepanov.bbf.bugfinder.mutator.Mutator
import com.stepanov.bbf.bugfinder.mutator.metamorphicTransformations.MetamorphicTransformation
import com.stepanov.bbf.bugfinder.mutator.transformations.Transformation
import org.apache.log4j.Logger

open class BugFinder(protected val dir: String) {

    fun mutate(
        project: Project,
        curFile: BBFFile,
        compilers: List<CommonCompiler>,
        conditions: List<(PsiFile) -> Boolean> = listOf()
    ) {
        if (!CompilerArgs.isMetamorphicMode)
            Transformation.checker = MutationChecker(
                compilers,
                project,
                curFile
            ).also { checker -> conditions.forEach { checker.additionalConditions.add(it) } }
        else MetamorphicTransformation.checker = MetamorphicMutationChecker(
            compilers,
            project,
            curFile
        ).also { checker -> conditions.forEach { checker.additionalConditions.add(it) } }
        if (CompilerArgs.isMetamorphicMode)
            MetamorphicMutator(project).startMutate()
        else
            Mutator(project).startMutate()
    }

    protected val log = Logger.getLogger("bugFinderLogger")

}