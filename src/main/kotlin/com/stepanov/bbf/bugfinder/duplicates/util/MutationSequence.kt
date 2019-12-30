package com.stepanov.bbf.bugfinder.duplicates.util

import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.duplicates.engine.Diff
import com.stepanov.bbf.bugfinder.duplicates.engine.conversion.DiffChunk
import com.stepanov.bbf.bugfinder.duplicates.engine.transforming.EditOperationType
import com.stepanov.bbf.bugfinder.duplicates.lang.kotlin.KotlinCfg
import com.stepanov.bbf.bugfinder.util.MutationSaver
import com.stepanov.bbf.reduktor.parser.PSICreator
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtPsiFactory


@Serializable
data class Mutation(val srcNode: Pair<String, String>, val dstNode: Pair<String?, String?>, val type: EditOperationType)

@Serializable
class MutationSequence(val startTreeText: String) {

    fun stateChanged(newTree: PsiElement) {
        //If we adding space
        if (curTree.text.filter { !it.isWhitespace() } == newTree.text.filter { !it.isWhitespace() }) return
        val chunks = diff.diff(curTree, newTree) ?: return
        mutations.addAll(chunks.flatMap {
            it.myOperations.map {
                Mutation(
                    it.srcNode.myPsi.node.elementType.toString() to it.srcNode.text,
                    it.dstNode?.myPsi?.node?.elementType.toString() to it.dstNode?.text,
                    it.type
                )
            }
        })
        curTree = newTree.copy()
        curTreeText = newTree.text
    }

    var curTreeText = startTreeText

    val mutations = mutableListOf<Mutation>()

    @Transient
    var curTree = PSICreator("").getPSIForText(startTreeText) as PsiElement

    @Transient
    private val diff = Diff(KotlinCfg())
}