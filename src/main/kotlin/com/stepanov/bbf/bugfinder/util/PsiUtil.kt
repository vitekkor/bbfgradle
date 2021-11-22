package com.stepanov.bbf.bugfinder.util

import com.intellij.lang.ASTNode
import com.intellij.psi.*
import com.intellij.util.IncorrectOperationException
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.mutator.transformations.filterDuplicates
import com.stepanov.bbf.reduktor.util.getAllParents
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.*
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.bindingContextUtil.getAbbreviatedTypeOrType
import org.jetbrains.kotlin.resolve.calls.callUtil.getType
import org.jetbrains.kotlin.types.KotlinType
import kotlin.system.exitProcess

//fun PsiFile.getNodesBetweenLines(begin: Int, end: Int): List<PsiElement> {
//    val resList = mutableListOf<PsiElement>()
//    var whiteSpacesCounter = 0
//    for (node in getAllPSIDFSChildrenOfType<PsiElement>()) {
//        if (node is PsiWhiteSpace || node is PsiComment) whiteSpacesCounter += node.text.count { it == '\n' }
//        if (this is KtFile && node is KtStringTemplateEntry) whiteSpacesCounter += node.text.count { it == '\n' }
//        if (whiteSpacesCounter in begin..end) resList.add(node)
//        if (whiteSpacesCounter > end) break
//    }
//    return resList
//}

fun PsiFile.getNodesBetweenLines(begin: Int, end: Int): List<PsiElement> {
    val resList = mutableListOf<PsiElement>()
    var whiteSpacesCounter = 0
    val nodes = getAllPSIDFSChildrenOfType<PsiElement>()
    for (node in nodes) {
        if (whiteSpacesCounter in begin..end) resList.add(node)
        if (node.children.isEmpty()) {
            whiteSpacesCounter += node.text.count { it == '\n' }
        }
        if (whiteSpacesCounter >= end) break
    }
    return resList
}

fun KtNamedFunction.isUnit() = this.typeReference == null && this.hasBlockBody()

fun KtPsiFactory.createNonEmptyClassBody(body: String): KtClassBody {
    return createClass("class A(){\n$body\n}").body!!
}

fun KtClassOrObject.addPsiToBody(prop: PsiElement): PsiElement? =
    this.body?.addBeforeRBrace(prop) ?: this.add(Factory.psiFactory.createNonEmptyClassBody(prop.text))

fun KtClassBody.addBeforeRBrace(psiElement: PsiElement): PsiElement {
    return this.rBrace?.let { rBrace ->
        val ws = this.addBefore(Factory.psiFactory.createWhiteSpace("\n"), rBrace)
        val res = this.addAfter(psiElement, ws)
        this.addAfter(Factory.psiFactory.createWhiteSpace("\n"), res)
        res
    } ?: psiElement
}

fun KtFile.getAvailableValuesToInsertIn(
    node: PsiElement,
    ctx: BindingContext
): List<Pair<KtExpression, KotlinType?>> {
    val nodeParents = node.parents.toList()
    val parameters = nodeParents
        .filterIsInstance<KtCallableDeclaration>()
        .flatMap { it.valueParameters }
        .filter { it.name != null }
        .map { Factory.psiFactory.createExpression(it.name ?: "") to it.typeReference?.getAbbreviatedTypeOrType(ctx) }
        .filter { it.second != null }
    val props = nodeParents
        .flatMap { it.getAllPSIDFSChildrenOfType<PsiElement>().takeWhile { it != node } }
        .filterIsInstance<KtProperty>()
        .filterDuplicates { a: KtProperty, b: KtProperty -> if (a == b) 0 else 1 }
        .filter { it.parents.filter { it is KtBlockExpression }.all { it in nodeParents } }
        .filter { it.name != null }
        .map {
            val kotlinType = it.typeReference?.getAbbreviatedTypeOrType(ctx) ?: it.initializer?.getType(ctx)
            Factory.psiFactory.createExpression(it.name ?: "") to kotlinType
        }
        .filter { it.second != null }
    return parameters + props
}

fun PsiElement.addAfterThisWithWhitespace(psiElement: PsiElement, whiteSpace: String): PsiElement {
    return try {
        val placeToInsert = this.allChildren.lastOrNull() ?: this
        placeToInsert.add(Factory.psiFactory.createWhiteSpace(whiteSpace))
        val res = placeToInsert.add(psiElement)
        placeToInsert.add(Factory.psiFactory.createWhiteSpace(whiteSpace))
        res
    } catch (e: IncorrectOperationException) {
        this
    }
}

fun KtFile.findFunByName(name: String): KtNamedFunction? =
    this.getAllPSIChildrenOfType<KtNamedFunction>().find { it.name == name }

fun ASTNode.getLineNumberOfNode(): Int {
    val nodeOffset = this.startOffset
    val file = this.getAllParents().firstOrNull { it.psi is PsiFile } ?: return -1
    return file.getAllChildrenNodes()
        .filter { it.startOffset <= nodeOffset }
        .filter { it.children().firstOrNull() == null }
        .sumBy { it.text.count { it == '\n' } } + 1
}

fun PsiElement.getLineNumberOfNode(): Int = this.node.getLineNumberOfNode()

fun KtNamedFunction.getSourceCodeLinesRange() =
    this.node
        .getAllParents()
        .firstOrNull { it.psi is PsiFile }?.let { file ->
            val spacesBefore = file.text.substring(0, this.startOffset).count { it == '\n' }
            val linesInFunc = file.text.substring(this.startOffset until this.endOffset).count { it == '\n' }
            spacesBefore..spacesBefore + linesInFunc
        } ?: -1..-1

fun PsiMethod.getSourceCodeLinesRange() =
    this.node
        .getAllParents()
        .firstOrNull { it.psi is PsiFile }?.let { file ->
            val spacesBefore = file.text.substring(0, this.startOffset).count { it == '\n' }
            val linesInFunc = file.text.substring(this.startOffset..this.endOffset).count { it == '\n' }
            spacesBefore..spacesBefore + linesInFunc
        } ?: -1..-1
