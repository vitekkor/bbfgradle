package com.stepanov.bbf.bugfinder.executor

import com.intellij.psi.PsiErrorElement
import com.intellij.psi.PsiFile
import com.stepanov.bbf.bugfinder.manager.BugManager
import com.stepanov.bbf.bugfinder.util.getFileLanguageIfExist
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.util.getAllChildrenNodes
import org.apache.log4j.Logger

//Project adaptation
open class Checker(compilers: List<CommonCompiler>) : CompilationChecker(compilers) {

    //Back compatibility
    fun checkTextCompiling(text: String): Boolean = checkCompiling(Project(listOf(text)))
    fun checkCompiling(file: PsiFile): Boolean = checkTextCompiling(file.text)


    fun checkCompiling(file: PsiFile, otherFiles: Project?): Boolean =
        otherFiles?.let { files ->
            checkCompiling(Project(files.texts.toMutableList() + file.text, null, files.language))
        } ?: checkCompiling(file)

    fun checkCompiling(project: Project): Boolean {
        val allTexts = project.texts.joinToString()
        checkedConfigurations[allTexts]?.let { log.debug("Already checked"); return it }
        //Checking syntax correction
        try {
            for (text in project.texts) {
                val tree =
                    if (text.getFileLanguageIfExist() == LANGUAGE.JAVA) PSICreator("").getPsiForJava(text, file.project)
                    else psiFactory.createFile(text)
                if (tree.node.getAllChildrenNodes().any { it.psi is PsiErrorElement }) {
                    log.debug("Wrong syntax")
                    checkedConfigurations[allTexts] = false
                    return false
                }
                additionalConditions.forEach {
                    if (tree.text.getFileLanguageIfExist() == LANGUAGE.JAVA) return@forEach
                    if (!it.invoke(tree)) {
                        log.debug("Breaks condition")
                        return false
                    }
                }
            }
        } catch (e: Error) {
            //When PSI cannot be built
            return false
        }
        checkAndGetCompilerBugs(project).forEach { BugManager.saveBug(it) }
        return isCompilationSuccessful(project)
    }

    val additionalConditions: MutableList<(PsiFile) -> Boolean> = mutableListOf()

    private val checkedConfigurations = hashMapOf<String, Boolean>()
    private val log = Logger.getLogger("mutatorLogger")
}