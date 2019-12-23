package com.stepanov.bbf.bugfinder.duplicates.util

import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.duplicates.engine.Diff
import com.stepanov.bbf.bugfinder.duplicates.engine.conversion.DiffChunk
import com.stepanov.bbf.bugfinder.duplicates.engine.transforming.EditOperationType
import com.stepanov.bbf.bugfinder.duplicates.lang.kotlin.KotlinCfg
import com.stepanov.bbf.bugfinder.util.MutationSaver
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient


@Serializable
data class Mutation(val srcNode: PsiElement, val dstNode: PsiElement?, val type: EditOperationType)

@Serializable
class MutationSequence(val startTree: PsiElement) {

    fun stateChanged(newTree: PsiElement) {
        //If we adding space
        if (curTree.text.filter { !it.isWhitespace() } == newTree.text.filter { !it.isWhitespace() }) return
        val chunks = diff.diff(curTree, newTree) ?: return
        mutations.addAll(chunks.flatMap { it.myOperations.map { Mutation(it.srcNode.myPsi, it.srcNode.myPsi, it.type) } })
        curTree = newTree.copy()
    }

    var curTree = startTree.copy()

    val mutations = mutableListOf<Mutation>()

    @Transient
    private val diff = Diff(KotlinCfg())
}