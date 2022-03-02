package com.stepanov.bbf.bugfinder

import com.intellij.psi.PsiFile
import com.stepanov.bbf.bugfinder.executor.CommonCompiler
import com.stepanov.bbf.bugfinder.executor.checkers.MutationChecker
import com.stepanov.bbf.bugfinder.executor.project.BBFFile
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.mutator.Mutator
import com.stepanov.bbf.bugfinder.mutator.transformations.Transformation
import org.apache.logging.log4j.LogManager

open class BugFinder(protected val absolutePathToSeed: String) {

    fun mutate(
        project: Project,
        curFile: BBFFile,
        compilers: List<CommonCompiler>,
        conditions: List<(PsiFile) -> Boolean> = listOf()
    ) {
        Transformation.checker = MutationChecker(
            compilers,
            project,
            curFile
        ).also { checker -> conditions.forEach { checker.additionalConditions.add(it) } }
        Mutator(project).startMutate(absolutePathToSeed)
    }

    protected val log = LogManager.getLogger("bugFinderLogger")

}