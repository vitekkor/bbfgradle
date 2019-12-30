package com.stepanov.bbf.bugfinder.util

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiWhiteSpace
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.util.getDiffChild
import com.stepanov.bbf.reduktor.util.getDiffChildOrNull
import com.stepanov.bbf.reduktor.util.getLocation
import com.stepanov.bbf.reduktor.util.isEqual
import kotlinx.serialization.*
import kotlinx.serialization.internal.StringDescriptor
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtPsiFactory
import kotlin.math.max

@Serializable
class MutationSaver(val initTreeText: String) {

    private data class ProgramState(var treeRepresentation: KtFile) {
        val textRepresentation: String
            get() = treeRepresentation.text
    }

    @Serializable
    data class LineDiff(val oldLine: String, val newLine: String, val num: Int)

    @Serializable
    data class NodeReplacement(val from: Pair<String?, String>?, val to: Pair<String?, String>?) {
        companion object Factory {

            fun create(from: ASTNode?, to: ASTNode?): NodeReplacement {
                from?.let { fr ->
                    to?.let { to ->
                        return NodeReplacement(
                            fr.elementType.toString() to fr.text,
                            to.elementType.toString() to to.text
                        )
                    } ?: return NodeReplacement(fr.elementType.toString() to fr.text, null)
                } ?: return if (to != null) NodeReplacement(null, to.elementType.toString() to to.text)
                else NodeReplacement(null, null)
            }
        }
    }


    //TODO do we need moveline?
    @Serializable
    enum class OPERATION {
        INSERT,
        DELETE,
        REPLACE,
        SWAP,
        REMOVELINE,
        SWAPLINES
    }

    private fun handleLineTransformations(newTree: KtFile): Boolean {
        //Handle if line operation
        val oldSplitText = state.textRepresentation.split("\n")
        val newSplitText = newTree.text.split("\n")
        if (oldSplitText.size != newSplitText.size) return false

        val diff = mutableListOf<LineDiff>()
        val oldSplitTextMap = oldSplitText.withIndex().map { it.value to it.index }
        val newSplitTextMap = newSplitText.withIndex().map { it.value to it.index }
        for ((index, value) in oldSplitTextMap.withIndex()) {
            if (value.first != newSplitTextMap[index].first) {
                diff.add(LineDiff(value.first, newSplitTextMap[index].first, index))
            }
        }
        if (diff.size == 0) {
            return true
        }
        if (diff.size == 1 && diff.first().newLine.trim().isEmpty()) {
            mutationList.add(
                OPERATION.REMOVELINE to NodeReplacement(
                    null to diff.first().oldLine,
                    null to diff.first().newLine
                )
            )
            state = ProgramState(newTree)
            return true
        }
        if (diff.size == 2) {
            if (diff.first().oldLine == diff.last().newLine &&
                diff.first().newLine == diff.last().oldLine
            ) {
                mutationList.add(
                    OPERATION.SWAPLINES to NodeReplacement(
                        null to diff.first().oldLine,
                        null to diff.first().newLine
                    )
                )
                state = ProgramState(newTree)
                return true
            }
        }
        return false
    }

    fun changeState(newState: String, tree: KtFile? = null) {
        finalText = newState
        val newTree = tree ?: PSICreator("").getPSIForText(newState, false)
        if (handleLineTransformations(newTree)) return

        //Swap check for top-level nodes
        if (checkForSwap(state.treeRepresentation.node, newTree.node)) {
            state = ProgramState(newTree)
            return
        }

        //Compare trees
        var level = 1
        var nodes = state.treeRepresentation.node.getAllChildrenOfTheLevel(level).filter { it.psi !is PsiWhiteSpace }
        while (nodes.isNotEmpty()) {
            val otherNodes = newTree.node.getAllChildrenOfTheLevel(level).filter { it.psi !is PsiWhiteSpace }
            //Removing
            if (nodes.size < otherNodes.size) {
                val node = nodes.first { astNode -> otherNodes.all { !it.isEqual(astNode) } }
                mutationList.add(OPERATION.DELETE to NodeReplacement.create(node, null))
                state = ProgramState(newTree)
                return
            }
            //Insertion
            if (nodes.size > otherNodes.size) {
                val node = otherNodes.first { astNode -> nodes.all { !it.isEqual(astNode) } }
                mutationList.add(OPERATION.INSERT to NodeReplacement.create(null, node))
                state = ProgramState(newTree)
                return
            }
            nodes.forEachIndexed { index, astNode ->
                if (!astNode.isEqual(otherNodes[index])) {
                    //Replacing
                    val diffNodes = astNode.getDiffChildOrNull(otherNodes[index])
                    if (diffNodes?.size == 2) {
                        val first = diffNodes.first()
                        val sec = diffNodes.last()
                        val diffFirst = first.first.getDiffChild(first.second)
                        val diffSec = sec.first.getDiffChild(sec.second)
                        if (diffFirst.first.text == diffSec.second.text &&
                            diffFirst.second.text == diffSec.first.text
                        ) {
                            mutationList.add(OPERATION.SWAP to NodeReplacement.create(diffFirst.second, diffSec.second))
                            state = ProgramState(newTree)
                            return
                        }
                    }
                    if (diffNodes == null) {
                        mutationList.add(OPERATION.REPLACE to NodeReplacement.create(astNode, otherNodes[index]))
                        state = ProgramState(newTree)
                        return
                    }
                }
            }
            ++level
            nodes = state.treeRepresentation.node.getAllChildrenOfTheLevel(level).filter { it.psi !is PsiWhiteSpace }
        }
        state = ProgramState(newTree)
    }

    private fun checkForSwap(astNode: ASTNode, otherNode: ASTNode): Boolean {
        val diffNodes = astNode.getDiffChildOrNull(otherNode)
        if (diffNodes?.size == 2) {
            val first = diffNodes.first()
            val sec = diffNodes.last()
            val diffFirst = first.first.getDiffChild(first.second)
            val diffSec = sec.first.getDiffChild(sec.second)
            if (diffFirst.first.text == diffSec.second.text &&
                diffFirst.second.text == diffSec.first.text
            ) {
                mutationList.add(OPERATION.SWAP to NodeReplacement.create(diffFirst.second, diffSec.second))
                return true
            }
        }
        return false
    }

    fun init(tree: KtFile) {
        state = ProgramState(tree)
    }


    val mutationList = mutableListOf<Pair<OPERATION, NodeReplacement>>()
    var finalText: String = initTreeText

    @Transient
    val initTree = PSICreator("").getPSIForText(initTreeText, false)
    @Transient
    private var state: ProgramState = ProgramState(initTree)

}