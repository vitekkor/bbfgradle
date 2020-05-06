package com.stepanov.bbf.bugfinder.mutator.transformations

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiWhiteSpace
import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.util.generateDefValuesAsString
import com.stepanov.bbf.bugfinder.util.getAllChildrenNodes
import com.stepanov.bbf.bugfinder.util.getAllPSIDFSChildrenOfType
import com.stepanov.bbf.bugfinder.util.replaceThis
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.util.getAllChildren
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.calls.callUtil.getType
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.isNullable
import org.jetbrains.kotlin.types.typeUtil.makeNotNullable
import java.io.File
import kotlin.random.Random
import kotlin.streams.toList

class AddNodeFromAnotherFile : Transformation() {

    override fun transform() {
        val creator = PSICreator("")
        var psi = creator.getPSIForText(file.text)
        var ctx = creator.ctx!!
        for (i in 0 until randomConst) {
            val line = File("database.txt").bufferedReader().lines().toList().random()
            val randomType = line.takeWhile { it != ' ' }
            val files = line.dropLast(1).takeLastWhile { it != '[' }.split(", ")
            val randomFile =
                if (files.size == 1)
                    files[0]
                else
                    files[Random.nextInt(0, files.size - 1)]
            val placeToInsert =
                psi.getAllPSIDFSChildrenOfType<PsiWhiteSpace>().filter { it.text.contains("\n") }.random()
            val creator2 = PSICreator("")
            val (psi2, ctx2) = creator2.getPSIForText(File("${CompilerArgs.baseDir}/$randomFile").readText()) to creator2.ctx!!
            val sameTypeNodes = psi2.node.getAllChildrenNodes().filter { it.elementType.toString() == randomType }
            val targetNode =
                if (sameTypeNodes.size == 1)
                    sameTypeNodes[0]
                else
                    sameTypeNodes[Random.nextInt(0, sameTypeNodes.size - 1)]
            val backup = targetNode.text
            log.debug("Trying to insert ${targetNode.text}")
            val isRenamingCorrect = renameNameReferences(ctx, placeToInsert, targetNode.psi, ctx2)
            log.debug("renamed = ${targetNode.text}")
            if (targetNode.psi.text == backup || !isRenamingCorrect) continue
            val block = psiFactory.createBlock(targetNode.text)
            block.lBrace?.delete()
            block.rBrace?.delete()
            checker.addNodeIfPossible(psi, placeToInsert, block)
            psi = creator.getPSIForText(psi.text)
            ctx = creator.ctx!!
        }
    }

    private fun renameNameReferences(
        psiCtx: BindingContext,
        placeToInsert: PsiElement,
        replacementNode: PsiElement,
        replacementCtx: BindingContext
    ): Boolean {
        val table1 = getSlice(placeToInsert)
            .map { Triple(it, it.text, it.getType(psiCtx)) }
            .filter { it.third != null }
        if (table1.isEmpty()) return false
        var nodesForChange = updateReplacement(replacementNode, replacementCtx)
        while (nodesForChange.isNotEmpty()) {
            val node = nodesForChange.first()
            val expressionsWhichWeCanPaste = getInsertableExpressions(table1, node)
            val replacement =
                if (expressionsWhichWeCanPaste.isEmpty()) {
                    //Try to generate value with needed type
                    val expr =
                        node.second?.let {
                            if (it.isNullable() && Random.getTrue(25)) "null"
                            else generateDefValuesAsString(node.second?.makeNotNullable().toString())
                        } ?: ""
                    psiFactory.createExpressionIfPossible(expr)
                } else expressionsWhichWeCanPaste.random().first.copy() as KtExpression
            if (replacement == null) {
                log.debug("Cant find and generate replacement for ${node.first.text} type ${node.second}")
                return false
            }
            log.debug("replacement of ${node.first.text} of type ${node.second} is ${replacement.text}")
            node.first.replaceThis(replacement)
            nodesForChange = updateReplacement(replacementNode, replacementCtx)
        }
        return true
    }

    private fun Random.getTrue(prob: Int): Boolean =
        Random.nextInt(0, 100) < prob

    private fun getInsertableExpressions(
        table: List<Triple<KtExpression, String, KotlinType?>>,
        node: Pair<KtExpression, KotlinType?>
    ): List<Triple<KtExpression, String, KotlinType?>> {
        //Nullable or most common types
        val res = mutableListOf<Triple<KtExpression, String, KotlinType?>>()
        val nodeType = node.second ?: return emptyList()
        for (el in table) {
            when {
                el.third == nodeType -> res.add(el)
                el.third?.toString() == "$nodeType?" -> res.add(el)
                commonTypesMap[nodeType.toString()]?.contains(el.third?.toString()) ?: false -> res.add(el)
            }
        }
        return res
    }

    private fun getSlice(node: PsiElement): Set<KtExpression> {
        val res = mutableSetOf<KtExpression>()
        getPropsUntil(node.parent, node).forEach { res.addAll(it.getAllPSIDFSChildrenOfType()) }
        node.getAllParentsWithoutThis().zipWithNext().forEach {
            getPropsUntil(it.second, it.first).forEach { res.add(it) }
        }
        return res
    }

    private fun PsiElement.getAllParentsWithoutThis(): List<PsiElement> {
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

    private fun getPropsUntil(node: PsiElement, until: PsiElement) =
        node.getAllChildren()
            .takeWhile { it != until }
            .filter { it !is KtNamedFunction && it !is KtClassOrObject && it is KtExpression }
            .flatMap { it.getAllPSIDFSChildrenOfType<KtExpression>() }

    private fun updateReplacement(replacementNode: PsiElement, replacementCtx: BindingContext) =
        replacementNode.getAllPSIChildrenOfType<KtExpression>()
            .map { it to it.getType(replacementCtx) }
            .filter { it.second != null }

    private val commonTypesMap = mapOf(
        "Byte" to listOf("Number"),
        "Short" to listOf("Number"),
        "Int" to listOf("Number"),
        "Long" to listOf("Number"),
        "Float" to listOf("Number"),
        "Double" to listOf("Number"),
        "String" to listOf("CharSequence"),
        "Collection" to listOf("Iterable"),
        "MutableCollection" to listOf("Collection", "MutableIterable"),
        "MutableIterable" to listOf("Iterable"),
        "List" to listOf("Collection"),
        "Set" to listOf("Collection"),
        "List" to listOf("Collection"),
        "MutableList" to listOf("List", "MutableCollection"),
        "MutableSet" to listOf("Set", "MutableCollection"),
        "MutableMap" to listOf("Map")
    )
    private val randomConst = Random.nextInt(1000, 1001)
}