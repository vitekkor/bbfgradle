package com.stepanov.bbf.bugfinder.util

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiWhiteSpace
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.util.getDiffChildOrNull
import com.stepanov.bbf.reduktor.util.isEqual
import org.jetbrains.kotlin.psi.KtFile


object MutationSaver {

    private data class ProgramState(var treeRepresentation: KtFile) {
        val textRepresentation: String
            get() = treeRepresentation.text
    }

    data class NodeReplacement(val from: ASTNode?, val to: ASTNode?)

    enum class OPERATION {
        INSERT,
        DELETE,
        REPLACE
    }

    fun changeState(newState: String, tree: KtFile? = null) {
        //If we adding space
        if (state.textRepresentation.filter { !it.isWhitespace() } == newState.filter { !it.isWhitespace() }) return
        val newTree = tree ?: PSICreator("").getPSIForText(newState, false)
        //Compare trees
        var level = 1
        var nodes = state.treeRepresentation.node.getAllChildrenOfTheLevel(level).filter { it.psi !is PsiWhiteSpace }
        while (nodes.isNotEmpty()) {
            val otherNodes = newTree.node.getAllChildrenOfTheLevel(level).filter { it.psi !is PsiWhiteSpace }
            //Removing
            if (nodes.size < otherNodes.size) {
                val node = nodes.first { astNode -> otherNodes.all { !it.isEqual(astNode) } }
                mutationList.add(OPERATION.DELETE to NodeReplacement(node, null))
                state = ProgramState(newTree)
                return
            }
            //Insertion
            if (nodes.size > otherNodes.size) {
                val node = otherNodes.first { astNode -> nodes.all { !it.isEqual(astNode) } }
                mutationList.add(OPERATION.INSERT to NodeReplacement(null, node))
                state = ProgramState(newTree)
                return
            }
            nodes.forEachIndexed { index, astNode ->
                if (!astNode.isEqual(otherNodes[index])) {
                    //Replacing
                    val diffNodes = astNode.getDiffChildOrNull(otherNodes[index])
                    if (diffNodes == null) {
                        mutationList.add(OPERATION.REPLACE to NodeReplacement(astNode, otherNodes[index]))
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

    fun init(tree: KtFile) {
        state = ProgramState(tree)
    }


    val mutationList = mutableListOf<Pair<OPERATION, NodeReplacement>>()
    private lateinit var state: ProgramState

}