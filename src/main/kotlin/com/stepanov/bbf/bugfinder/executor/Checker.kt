package com.stepanov.bbf.bugfinder.executor

import com.intellij.psi.PsiErrorElement
import com.intellij.psi.PsiFile
import com.stepanov.bbf.bugfinder.executor.checkers.CompilationChecker
import com.stepanov.bbf.bugfinder.executor.project.BBFFile
import com.stepanov.bbf.bugfinder.executor.project.LANGUAGE
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.manager.BugManager
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.util.StatisticCollector
import com.stepanov.bbf.bugfinder.util.getFileLanguageIfExist
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.apache.log4j.Logger

//Project adaptation
open class Checker(compilers: List<CommonCompiler>) : CompilationChecker(compilers) {

    constructor(compiler: CommonCompiler) : this(listOf(compiler))

    //Back compatibility
    fun checkTextCompiling(text: String): Boolean = checkCompilingWithBugSaving(Project.createFromCode(text), null)
    fun checkCompilingWithBugSaving(file: PsiFile): Boolean = checkTextCompiling(file.text)


    private fun createPsiAndCheckOnErrors(text: String, language: LANGUAGE): Boolean =
        when (language) {
            LANGUAGE.JAVA -> PSICreator("").getPsiForJava(text, Factory.file.project)
            else -> Factory.psiFactory.createFile(text)
        }.let { tree ->
            tree.getAllPSIChildrenOfType<PsiErrorElement>().isEmpty() && additionalConditions.all { it.invoke(tree) }
        }

    //FALSE IF ERROR
    private fun checkSyntaxCorrectnessAndAddCond(project: Project, curFile: BBFFile?) =
        curFile?.let {
            createPsiAndCheckOnErrors(curFile.text, curFile.text.getFileLanguageIfExist() ?: LANGUAGE.KOTLIN)
        } ?: project.files.any { createPsiAndCheckOnErrors(it.text, it.getLanguage()) }

//        try {
//            for (text in project.files.map { it.psiFile.text }) {
//                val tree =
//                    if (text.getFileLanguageIfExist() == LANGUAGE.JAVA) PSICreator("").getPsiForJava(text, Factory.file.project)
//                    else Factory.psiFactory.createFile(text)
//                if (tree.node.getAllChildrenNodes().any { it.psi is PsiErrorElement }) {
//                    log.debug("Wrong syntax")
//                    checkedConfigurations[allTexts] = false
//                    return false
//                }
//                additionalConditions.forEach {
//                    if (tree.text.getFileLanguageIfExist() == LANGUAGE.JAVA) return@forEach
//                    if (!it.invoke(tree)) {
//                        log.debug("Breaks condition")
//                        return false
//                    }
//                }
//            }
//        } catch (e: Error) {
//            //When PSI cannot be built
//            return false
//        }

    fun checkCompiling(project: Project): Boolean {
        val allTexts = project.files.map { it.psiFile.text }.joinToString()
        checkedConfigurations[allTexts]?.let { log.debug("Already checked"); return it }
        //Checking syntax correction
        if (!checkSyntaxCorrectnessAndAddCond(project, null)) {
            log.debug("Wrong syntax or breaks conditions")
            checkedConfigurations[allTexts] = false
            return false
        }
        val statuses = compileAndGetStatuses(project)
        return if (statuses.all { it == COMPILE_STATUS.OK }) {
            checkedConfigurations[allTexts] = true
            true
        } else {
            checkedConfigurations[allTexts] = false
            false
        }
    }

    fun checkCompilingWithBugSaving(project: Project, curFile: BBFFile? = null) =
        checkCompilingWithBugSaving1(project, curFile).let {
            if (it) StatisticCollector.incField("Correct programs")
            else StatisticCollector.incField("Incorrect programs")
            it
        }

    fun checkCompilingWithBugSaving1(project: Project, curFile: BBFFile? = null): Boolean {
        val allTexts = project.files.map { it.psiFile.text }.joinToString()
        checkedConfigurations[allTexts]?.let { log.debug("Already checked"); return it }
        //Checking syntax correction
        if (!checkSyntaxCorrectnessAndAddCond(project, curFile)) {
            log.debug("Wrong syntax or breaks conditions")
            checkedConfigurations[allTexts] = false
            return false
        }
        val statuses = compileAndGetStatuses(project)
        when {
            statuses.all { it == COMPILE_STATUS.OK } -> {
                checkedConfigurations[allTexts] = true
                return true
            }
            statuses.all { it == COMPILE_STATUS.ERROR } -> {
                checkedConfigurations[allTexts] = false
                return false
            }
        }
        checkAndGetCompilerBugs(project).forEach { BugManager.saveBug(it) }
        checkedConfigurations[allTexts] = false
        return false
    }

    val additionalConditions: MutableList<(PsiFile) -> Boolean> = mutableListOf()

    private val checkedConfigurations = hashMapOf<String, Boolean>()
    private val log = Logger.getLogger("mutatorLogger")
}