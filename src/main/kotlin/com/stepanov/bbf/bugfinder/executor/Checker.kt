package com.stepanov.bbf.bugfinder.executor

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiErrorElement
import com.stepanov.bbf.bugfinder.manager.Bug
import com.stepanov.bbf.bugfinder.manager.BugManager
import com.stepanov.bbf.bugfinder.manager.BugType
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
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


    fun checkCompiling(project: Project): Boolean {
        val allTexts = project.texts.joinToString()
        checkedConfigurations[allTexts]?.let { log.debug("Already checked"); return it }
        //Checking syntax correction
        for (text in project.texts) {
            val tree = psiFactory.createFile(text)
            if (tree.node.getAllChildrenNodes().any { it.psi is PsiErrorElement }) {
                checkedConfigurations[allTexts] = false
                return false
            }
            additionalConditions.forEach {
                if (!it.invoke(tree)) return false
            }
        }
        isCompilerBug(project).forEach { BugManager.saveBug(it) }
        return isCompilationSuccessful(project)

//        //Checking if fun box() is on top level and didnt changed
//        if (!tree.text.contains(Regex("""fun\s*box\s*\(\)\s*:\s*String"""))) return false
//        val funBox = tree.getAllPSIChildrenOfType<KtNamedFunction>().first { it.name == "box" }
//        if (!funBox.isTopLevel) return false

//        val compilersToStatus = compilers.map { it to it.checkCompilingText(text) }
//        var foundCompilerBug = false
//        if (CompilerArgs.shouldSaveCompilerBugs) {
//            //Saving text to tmp.kt
//            val tmpPath = CompilerArgs.pathToTmpFile
//            File(tmpPath).writeText(text)
//            log.debug("Checking for bug text: ${File(tmpPath).readText()}")
//
//            //Checking for compiler bug and if bug, then save
//            compilers.forEach { compiler ->
//                if (compiler.isCompilerBug(tmpPath)) {
//                    foundCompilerBug = true
//                    if (!gotBugFromCurrentFile) {
//                        log.debug("Found ${compiler.compilerInfo} BUG:\n Text:\n ${File(tmpPath).readText()}")
//                        saveCompilerBug(tmpPath, compiler)
//                    }
//                }
//            }
//            if (foundCompilerBug) {
//                gotBugFromCurrentFile = true
//                return false
//            }
//        }
        return false
    }

    abstract fun isCompilationSuccessful(project: Project): Boolean
    abstract fun isCompilerBug(project: Project): List<Bug>


//    fun replaceNodeIfPossible(file: KtFile, node: ASTNode, replacement: ASTNode): Boolean {
//        return false
//    }
//
//    fun replacePSINodeIfPossible(file: KtFile, node: PsiElement, replacement: PsiElement) =
//        replaceNodeIfPossible(file, node.node, replacement.node)
//
//    fun addNodeIfPossible(file: KtFile, anchor: PsiElement, node: PsiElement, before: Boolean = false): Boolean {
//        if (node.text.isEmpty() || node == anchor) return checkCompiling(
//            file
//        )
//        try {
//            val addedNode =
//                if (before) anchor.parent.addBefore(node, anchor)
//                else anchor.parent.addAfter(node, anchor)
//            if (checkCompiling(file)) return true
//            addedNode.parent.node.removeChild(addedNode.node)
//            return false
//        } catch (e: Throwable) {
//            println("e = $e")
//            return false
//        }
//    }

    abstract val additionalConditions: List<(KtFile) -> Boolean>
    private val checkedConfigurations = hashMapOf<String, Boolean>()
    private val log = Logger.getLogger("mutatorLogger")
}