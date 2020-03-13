package com.stepanov.bbf.bugfinder.executor

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiErrorElement
import com.stepanov.bbf.bugfinder.manager.Bug
import com.stepanov.bbf.bugfinder.manager.BugManager
import com.stepanov.bbf.bugfinder.manager.BugType
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.util.saveOrRemoveToTmp
import com.stepanov.bbf.reduktor.util.getAllChildrenNodes
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.apache.log4j.Logger
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.KtPsiFactory
import java.io.File

//Project adaptation
abstract class Checker() : Factory() {

    //Back compatibility
    fun checkTextCompiling(text: String): Boolean = checkCompiling(Project(listOf(text)))
    fun checkCompiling(file: KtFile): Boolean = checkTextCompiling(file.text)


    fun checkCompiling(file: KtFile, otherFiles: Project?): Boolean =
        otherFiles?.let { files ->
            checkCompiling(Project(files.texts.toMutableList() + file.text))
        } ?: checkCompiling(file)

    fun saveAndCheckCompiling(project: Project): Boolean {
        project.saveOrRemoveToTmp(true)
        return checkCompiling(project)
    }

    fun checkCompiling(project: Project): Boolean {
        //TODO!!!! Java
        val allTexts = project.texts.joinToString()
        checkedConfigurations[allTexts]?.let { log.debug("Already checked"); return it }
        //Checking syntax correction
        for (text in project.texts) {
            val tree = psiFactory.createFile(text)
            if (tree.node.getAllChildrenNodes().any { it.psi is PsiErrorElement }) {
                log.debug("Wrong syntax")
                checkedConfigurations[allTexts] = false
                return false
            }
            additionalConditions.forEach {
                if (!it.invoke(tree)) {
                    log.debug("Breaks condition")
                    return false
                }
            }
        }
        isCompilerBug(project).forEach { BugManager.saveBug(it) }
        return isCompilationSuccessful(project)
    }

    abstract fun isCompilationSuccessful(project: Project): Boolean
    abstract fun isCompilerBug(project: Project): List<Bug>


    abstract val additionalConditions: List<(KtFile) -> Boolean>
    private val checkedConfigurations = hashMapOf<String, Boolean>()
    private val log = Logger.getLogger("mutatorLogger")
}