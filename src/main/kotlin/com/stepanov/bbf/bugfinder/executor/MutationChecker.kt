package com.stepanov.bbf.bugfinder.executor

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiErrorElement
import com.intellij.psi.impl.source.tree.TreeElement
import com.stepanov.bbf.bugfinder.Reducer
import com.stepanov.bbf.bugfinder.executor.CompilerArgs.shouldFilterDuplicateCompilerBugs
import com.stepanov.bbf.bugfinder.executor.CompilerArgs.shouldSaveCompileDiff
import com.stepanov.bbf.bugfinder.manager.BugManager
import com.stepanov.bbf.bugfinder.manager.BugType
import com.stepanov.bbf.bugfinder.util.FilterDuplcatesCompilerErrors.simpleDuplicateFile
import com.stepanov.bbf.bugfinder.util.FilterDuplcatesCompilerErrors.simpleHaveDuplicatesErrors
import com.stepanov.bbf.bugfinder.util.MutationSaver
import com.stepanov.bbf.bugfinder.util.getAllParentsWithoutNode
import com.stepanov.bbf.bugfinder.util.getRandomVariableName
import com.stepanov.bbf.reduktor.util.getAllChildrenNodes
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.apache.log4j.Logger
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.KtPsiFactory
import java.io.File
import java.util.*

object MutationChecker {
    fun checkCompiling(file: KtFile): Boolean =
        checkTextCompiling(file.text)

    fun checkTextCompiling(text: String): Boolean {
        checkedConfigurations[text]?.let { log.debug("Already checked"); return it }
        //Checking syntax correction
        val tree = factory.createFile(text)
        if (tree.node.getAllChildrenNodes().any { it.psi is PsiErrorElement }) {
            checkedConfigurations[text] = false
            return false
        }
        //Checking if fun box() is on top level and didnt changed
        if (!tree.text.contains("fun box(): String")) return false
        val funBox = tree.getAllPSIChildrenOfType<KtNamedFunction>().first { it.name == "box" }
        if (!funBox.isTopLevel) return false

        val compilersToStatus = compilers.map { it to it.checkCompilingText(text) }
        var foundCompilerBug = false
        if (CompilerArgs.shouldSaveCompilerBugs) {
            //Saving text to tmp.kt
            val tmpPath = CompilerArgs.pathToTmpFile
            File(tmpPath).writeText(text)
            log.debug("Checking for bug text: ${File(tmpPath).readText()}")

            //Checking for compiler bug and if bug, then save
            compilers.forEach { compiler ->
                if (compiler.isCompilerBug(tmpPath)) {
                    foundCompilerBug = true
                    if (!gotBugFromCurrentFile) {
                        log.debug("Found ${compiler.compilerInfo} BUG:\n Text:\n ${File(tmpPath).readText()}")
                        saveCompilerBug(tmpPath, compiler)
                    }
                }
            }
            if (foundCompilerBug) gotBugFromCurrentFile = true
        }

        if (!gotCompDiffFromCurrentFile && !foundCompilerBug && shouldSaveCompileDiff) {
            val grouped = compilersToStatus.groupBy { it.first.compilerInfo.split(" ").first() }
            for (g in grouped) {
                if (g.value.map { it.second }.toSet().size != 1) {
                    gotCompDiffFromCurrentFile = true
                    val diffCompilers =
                        g.value.groupBy { it.second }.mapValues { it.value.first().first.compilerInfo }.values
                    log.debug("Found compile diff bug on compilers $diffCompilers")
                    val compilersForReducer =
                        g.value.groupBy { it.second }.mapValues { it.value.first().first }.values.toList()
                    File(CompilerArgs.pathToTmpFile).writeText(text)
                    val reduced =
                        if (CompilerArgs.shouldReduceDiffCompile)
                            Reducer.reduceDiffCompile(CompilerArgs.pathToTmpFile, compilersForReducer)
                        else
                            text
                    BugManager.saveBug(diffCompilers.joinToString(separator = ","), "", reduced, BugType.DIFFCOMPILE)
                }
            }
        }

        val isAccepted = compilersToStatus.all { it.second }
        if (isAccepted) {
            //MutationSaver.changeState(text)
            log.debug("Mutation accepted")
        } else {
            log.debug("Mutation didnt accepted")
        }
        checkedConfigurations[text] = isAccepted
        return isAccepted
    }

    private fun saveCompilerBug(path: String, compiler: CommonCompiler) {
        log.debug("Trying to save ${compiler.compilerInfo} compiler bug\n Text: ${File(path).readText()}")
        val compilerBugsDir = compiler.compilerInfo.filter { it != ' ' }

        val reduced = Reducer.reduce(path, compiler).first()
        log.debug("Reduced = ${reduced.text}")
        File(path).writeText(reduced.text)
        if (!compiler.isCompilerBug(path)) {
            log.debug("It's not a bug more =(")
            return
        }

        val oldText = reduced.text.trim()

        val resDir = CompilerArgs.resultsDir
        val dirWithPotentialDuplicates =
            if (resDir.endsWith("/")) "$resDir$compilerBugsDir/"
            else "$resDir/$compilerBugsDir/"

        if (foundBugs.contains(oldText to compiler.compilerInfo)) {
            log.debug("Bug already found")
            return
        }

        if (!File(dirWithPotentialDuplicates).exists()) File(dirWithPotentialDuplicates).mkdirs()
        if (shouldFilterDuplicateCompilerBugs) log.debug("TRYING TO FIND DUPLICATE in $dirWithPotentialDuplicates")
        if (shouldFilterDuplicateCompilerBugs) {
            val duplicateFile = simpleDuplicateFile(path, dirWithPotentialDuplicates, compiler)
            if (duplicateFile != null) {
                val randomName = Random().getRandomVariableName(5)
                File("tmp/results/DUPLICATES/$randomName.kt").writeText("//Duplicate of ${duplicateFile.absolutePath}\n\n$oldText")
                log.debug("Found duplicates")
            } else {
                //File(newPath).writeText(oldText)
                BugManager.saveBug(compiler.compilerInfo, compiler.getErrorMessageForText(oldText), oldText)
                foundBugs.add(oldText to compiler.compilerInfo)
            }
        }
    }

    fun replacePSINodeIfPossible(file: KtFile, node: PsiElement, replacement: PsiElement) =
        replaceNodeIfPossible(file, node.node, replacement.node)

    fun replaceNodeIfPossible(file: KtFile, node: ASTNode, replacement: ASTNode): Boolean {
        if (node.text.isEmpty() || node == replacement) return checkCompiling(file)
        for (p in node.getAllParentsWithoutNode()) {
            try {
                if (node.treeParent.elementType.index == DUMMY_HOLDER_INDEX) continue
                val oldText = file.text
                val replCopy = replacement.copyElement()
                if ((node as TreeElement).treeParent !== p) {
                    continue
                }
                p.replaceChild(node, replCopy)
                if (oldText == file.text)
                    continue
                if (!checkCompiling(file)) {
                    p.replaceChild(replCopy, node)
                    return false
                } else {
                    return true
                }
            } catch (e: Error) {
            }
        }
        return false
    }

    fun addNodeIfPossible(file: KtFile, anchor: PsiElement, node: PsiElement, before: Boolean = false): Boolean {
        if (node.text.isEmpty() || node == anchor) return checkCompiling(
            file
        )
        try {
            val addedNode =
                if (before) anchor.parent.addBefore(node, anchor)
                else anchor.parent.addAfter(node, anchor)
            if (checkCompiling(file)) return true
            file.node.removeChild(addedNode.node)
            return false
        } catch (e: Throwable) {
            return false
        }
    }

    fun addNodeIfPossible(file: KtFile, anchor: ASTNode, node: ASTNode, before: Boolean = false): Boolean =
        addNodeIfPossible(file, anchor.psi, node.psi, before)

    lateinit var factory: KtPsiFactory
    lateinit var compilers: List<CommonCompiler>
    private const val DUMMY_HOLDER_INDEX: Short = 86
    private val log = Logger.getLogger("mutatorLogger")
    private val foundBugs = hashSetOf<Pair<String, String>>()
    private val checkedConfigurations = hashMapOf<String, Boolean>()

    //    //TODO!!! REMOVE THIS
    private var gotBugFromCurrentFile = false
    private var gotCompDiffFromCurrentFile = false

}