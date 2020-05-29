package com.stepanov.bbf.bugfinder.executor

import com.intellij.lang.ASTNode
import com.intellij.lang.FileASTNode
import com.intellij.psi.PsiErrorElement
import com.intellij.psi.PsiFile
import com.intellij.psi.impl.source.tree.TreeElement
import com.stepanov.bbf.bugfinder.executor.project.BBFFile
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.reduktor.executor.CompilerTestChecker
import com.stepanov.bbf.reduktor.executor.error.Error
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.util.getAllChildrenNodes
import com.stepanov.bbf.reduktor.util.getAllParentsWithoutNode
import com.stepanov.bbf.reduktor.util.replaceThis
import org.apache.log4j.Logger
import org.jetbrains.kotlin.psi.KtPsiFactory

open class MultiCompilerCrashChecker(
    override val project: Project,
    override val curFile: BBFFile,
    private val compiler: CommonCompiler?
) : CompilerTestChecker {
    override fun removeNodeIfPossible(node: ASTNode): Boolean {
        val tmp = Factory.psiFactory.createWhiteSpace("\n")
        return replaceNodeIfPossible(node, tmp.node)
    }

    override fun removeNodesIfPossible(nodes: List<ASTNode>): Boolean {
        val copies = mutableListOf<ASTNode>()
        val whiteSpaces = mutableListOf<ASTNode>()
        nodes.forEach { copies.add(it.copyElement()); whiteSpaces.add(Factory.psiFactory.createWhiteSpace("\n").node) }
        for ((i, node) in nodes.withIndex()) {
            for (p in node.getAllParentsWithoutNode()) {
                try {
                    p.replaceChild(node, whiteSpaces[i])
                    break
                } catch (e: AssertionError) {
                }
            }
        }
        if (!checkTest()) {
            for ((i, node) in nodes.withIndex()) {
                for (p in whiteSpaces[i].getAllParentsWithoutNode()) {
                    try {
                        p.replaceChild(whiteSpaces[i], node)
                        break
                    } catch (e: AssertionError) {
                    }
                }
            }
            return false
        } else return true
    }

    override fun replaceNodeIfPossible(node: ASTNode, replacement: ASTNode): Boolean {
        if (node.text.isEmpty() || node == replacement) return checkTest()

        //If we trying to replace parent node to it child
        if (node.getAllChildrenNodes().contains(replacement)) {
            val backup = node.copyElement()
            node.replaceThis(replacement)
            if (!checkTest()) {
                log.debug("REPLACING BACK")
                replacement.replaceThis(backup)
            } else {
                log.debug("SUCCESSFUL DELETING")
                return true
            }
        }

        //Else
        for (p in node.getAllParentsWithoutNode()) {
            try {
                val oldText = curFile.text
                if ((node as TreeElement).treeParent !== p) continue
                p.replaceChild(node, replacement)
                if (oldText == curFile.text)
                    continue
                if (!checkTest()) {
                    log.debug("REPLACING BACK")
                    p.replaceChild(replacement, node)
                    return false
                } else {
                    log.debug("SUCCESSFUL DELETING")
                    return true
                }
            } catch (e: AssertionError) {
                log.debug("Exception while deleting ${node.text} from $p")
            }
        }
        return false
    }

    private fun isAlreadyCheckedOrWrong(): Pair<Boolean, Boolean> {
        val hash = project.toString().trim().hashCode()
        if (alreadyChecked.containsKey(hash)) {
            log.debug("ALREADY CHECKED!!!")
            return true to alreadyChecked[hash]!!
        }
        if (curFile.isPsiWrong()) return true to false
        return false to false
    }

    override fun checkTest(): Boolean {
        val firstCheck = isAlreadyCheckedOrWrong()
        if (firstCheck.first) return firstCheck.second
        return compiler!!.isCompilerBug(project)
    }

    override fun getErrorMessage(): String = compiler!!.getErrorMessage(project)


    override val alreadyChecked: HashMap<Int, Boolean> = HashMap()
    open val log = Logger.getLogger("reducerLogger")
}