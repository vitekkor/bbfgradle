package com.stepanov.bbf.bugfinder

import com.intellij.psi.PsiFile
import com.stepanov.bbf.bugfinder.executor.CommonCompiler
import com.stepanov.bbf.bugfinder.executor.checkers.MutationChecker
import com.stepanov.bbf.bugfinder.executor.compilers.JSCompiler
import com.stepanov.bbf.bugfinder.executor.compilers.JVMCompiler
import com.stepanov.bbf.bugfinder.executor.project.BBFFile
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.mutator.Mutator
import com.stepanov.bbf.bugfinder.mutator.transformations.Transformation
import com.stepanov.bbf.bugfinder.util.BBFProperties
import com.stepanov.bbf.reduktor.parser.PSICreator
import org.apache.log4j.Logger

open class BugFinder(protected val dir: String) {

    protected val compilers: MutableList<CommonCompiler> = mutableListOf()

    init {
        //Init compilers
        val compilersConf = BBFProperties.getStringGroupWithoutQuotes("BACKENDS")
        compilersConf.filter { it.key.contains("JVM") }.forEach {
            compilers.add(
                JVMCompiler(
                    it.value
                )
            )
        }
        compilersConf.filter { it.key.contains("JS") }.forEach {
            compilers.add(
                JSCompiler(
                    it.value
                )
            )
        }
    }

    fun mutate(
        project: Project,
        curFile: BBFFile,
        conditions: List<(PsiFile) -> Boolean> = listOf()
    ) {
        Transformation.checker = MutationChecker(
                compilers,
                project,
                curFile
            ).also { checker -> conditions.forEach { checker.additionalConditions.add(it) } }
        Mutator(project).startMutate()
    }

    protected val log = Logger.getLogger("bugFinderLogger")

}