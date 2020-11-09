package com.stepanov.bbf.bugfinder.executor

import com.intellij.psi.PsiErrorElement
import com.intellij.psi.PsiFile
import com.stepanov.bbf.bugfinder.executor.CompilerArgs.isABICheckMode
import com.stepanov.bbf.bugfinder.executor.checkers.CompilationChecker
import com.stepanov.bbf.bugfinder.executor.project.BBFFile
import com.stepanov.bbf.bugfinder.executor.project.LANGUAGE
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.manager.Bug
import com.stepanov.bbf.bugfinder.manager.BugManager
import com.stepanov.bbf.bugfinder.manager.BugType
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.util.StatisticCollector
import com.stepanov.bbf.bugfinder.util.getFileLanguageIfExist
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.apache.log4j.Logger

//Project adaptation
open class Checker(compilers: List<CommonCompiler>, private val withTracesCheck: Boolean = true) :
    CompilationChecker(compilers) {

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
            createPsiAndCheckOnErrors(curFile.text, curFile.getLanguage())
        } ?: project.files.any { createPsiAndCheckOnErrors(it.text, it.getLanguage()) }


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

    fun checkCompilingWithBugSaving(project: Project, curFile: BBFFile? = null): Boolean {
        log.debug("Compilation checking started")
        val allTexts = project.files.map { it.psiFile.text }.joinToString()
        checkedConfigurations[allTexts]?.let { log.debug("Already checked"); return it }
        //Checking syntax correction
        if (!checkSyntaxCorrectnessAndAddCond(project, curFile)) {
            log.debug("Wrong syntax or breaks conditions")
            StatisticCollector.incField("Incorrect programs")
            checkedConfigurations[allTexts] = false
            return false
        }
        val statuses = compileAndGetStatuses(project)
        when {
            statuses.all { it == COMPILE_STATUS.OK } -> {
                if (isABICheckMode) {
                    checkABI(project)?.let { res ->
                        BugManager.saveBug(
                            Bug(
                                CompilerArgs.getCompilersList(),
                                res.second.readText(),
                                project,
                                BugType.DIFFABI
                            )
                        )
                        return false
                    }
                }
//                if (withTracesCheck && CompilerArgs.isMiscompilationMode) {
//                    val checkRes = checkTraces(project)
//                    checkedConfigurations[allTexts] = checkRes
//                    return checkRes
//                }
                StatisticCollector.incField("Correct programs")
                checkedConfigurations[allTexts] = true
                return true
            }
            statuses.all { it == COMPILE_STATUS.ERROR } -> {
                StatisticCollector.incField("Incorrect programs")
                checkedConfigurations[allTexts] = false
                return false
            }
        }
        checkAndGetCompilerBugs(project).forEach { BugManager.saveBug(it) }
        checkedConfigurations[allTexts] = false
        StatisticCollector.incField("Correct programs")
        return false
    }

    val additionalConditions: MutableList<(PsiFile) -> Boolean> = mutableListOf()

    private val checkedConfigurations = hashMapOf<String, Boolean>()
    private val log = Logger.getLogger("mutatorLogger")
}