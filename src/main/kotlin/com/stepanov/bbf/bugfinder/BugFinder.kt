package com.stepanov.bbf.bugfinder

import com.intellij.psi.PsiFile
import com.stepanov.bbf.bugfinder.executor.CommonCompiler
import com.stepanov.bbf.bugfinder.executor.LANGUAGE
import com.stepanov.bbf.bugfinder.executor.Project
import com.stepanov.bbf.bugfinder.executor.compilers.JSCompiler
import com.stepanov.bbf.bugfinder.executor.compilers.JVMCompiler
import com.stepanov.bbf.bugfinder.executor.compilers.MutationChecker
import com.stepanov.bbf.bugfinder.mutator.Mutator
import com.stepanov.bbf.bugfinder.mutator.transformations.Transformation
import com.stepanov.bbf.bugfinder.util.BBFProperties
import com.stepanov.bbf.reduktor.parser.PSICreator
import org.apache.log4j.Logger
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.resolve.BindingContext

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

    fun makeMutant(
        psiFile: PsiFile,
        context: BindingContext?,
        otherFiles: Project? = null,
        conditions: List<(PsiFile) -> Boolean> = listOf()
    ): PsiFile {
        Transformation.checker = MutationChecker(
            compilers,
            otherFiles
        ).also { checker -> conditions.forEach { checker.additionalConditions.add(it) } }
        Mutator(psiFile, context).startMutate()
        return PSICreator("").getPSIForText(Transformation.file.text)
    }

    protected val log = Logger.getLogger("bugFinderLogger")

}