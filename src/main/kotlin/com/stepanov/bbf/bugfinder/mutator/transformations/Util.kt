package com.stepanov.bbf.bugfinder.mutator.transformations

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.util.getAllPSIDFSChildrenOfType
import com.stepanov.bbf.reduktor.util.getAllChildren
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.KtNamedFunction
import com.stepanov.bbf.bugfinder.util.flatMap

internal fun getSlice(node: PsiElement): Set<KtExpression> {
    val res = mutableSetOf<KtExpression>()
    for (prop in getPropsUntil(node.parent, node)) {
        res.addAll(prop.getAllPSIDFSChildrenOfType())
    }
    //getPropsUntil(node.parent, node).forEach { res.addAll(it.getAllPSIDFSChildrenOfType()) }
    node.getAllParentsWithoutThis().zipWithNext().forEach {
        for (prop in getPropsUntil(it.second, it.first)) res.add(prop)
        //getPropsUntil(it.second, it.first).forEach { res.add(it) }
    }
    return res
}

internal fun PsiElement.getAllParentsWithoutThis(): List<PsiElement> {
    val result = arrayListOf<ASTNode>()
    var node = this.node.treeParent ?: return arrayListOf<PsiElement>()
    while (true) {
        result.add(node)
        if (node.treeParent == null)
            break
        node = node.treeParent
    }
    return result.map { it.psi }
}

fun getPropsUntil(node: PsiElement, until: PsiElement) =
    node.getAllChildren()
        .takeWhile { it != until }
        .filter { it !is KtNamedFunction && it !is KtClassOrObject && it is KtExpression }
        .flatMap { it.getAllPSIDFSChildrenOfType<KtExpression>() }

internal fun <T> List<T>.removeDuplicates(comparator: Comparator<T>): List<T> {
    val res = mutableListOf<T>()
    this.forEach { el -> if (res.all { comparator.compare(it, el) != 0 }) res.add(el) }
    return res
}