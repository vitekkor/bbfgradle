package com.stepanov.bbf.bugfinder.mutator.transformations

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNamedElement
import com.intellij.psi.PsiWhiteSpace
import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.util.*
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.calls.callUtil.getType
import org.jetbrains.kotlin.resolve.diagnostics.MutableDiagnosticsWithSuppression
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.isNullable
import org.jetbrains.kotlin.types.typeUtil.makeNotNullable
import java.io.File
import kotlin.random.Random
import kotlin.streams.toList
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory.psiFactory as psiFactory

class AddNodesFromAnotherFiles : Transformation() {

    override fun transform() {
        val creator = PSICreator("")
        var psi = creator.getPSIForText(file.text)
        var ctx = creator.ctx!!
        for (i in 0 until randomConst) {
            val line = File("database.txt").bufferedReader().lines().toList().random()
            val randomType = line.takeWhile { it != ' ' }
            val files = line.dropLast(1).takeLastWhile { it != '[' }.split(", ")
            val randomFile = files.random()
            val placeToInsert =
                psi.getAllPSIDFSChildrenOfType<PsiWhiteSpace>().filter { it.text.contains("\n") }.random()
            val creator2 = PSICreator("")
            val (psi2, ctx2) = creator2.getPSIForText(File("${CompilerArgs.baseDir}/$randomFile").readText()) to creator2.ctx!!
            val sameTypeNodes = psi2.node.getAllChildrenNodes().filter { it.elementType.toString() == randomType }
            val targetNode = sameTypeNodes.random()
            val psiBackup = psi.text
            val backup = targetNode.text
            if (targetNode.psi.getAllPSIChildrenOfType<KtExpression>().isEmpty()) continue
            log.debug("Trying to insert ${targetNode.text}")
            //If node is fun or property then rename
            if (targetNode.psi is KtProperty || targetNode.psi is KtNamedFunction) {
                (targetNode.psi as PsiNamedElement).setName(generateRandomName())
                if (tryToAdd(targetNode.psi, psi, placeToInsert) != null) continue
            }
            val isRenamingCorrect = renameNameReferences(ctx, placeToInsert, targetNode.psi, ctx2)
            log.debug("renamed = ${targetNode.text}")
            if (targetNode.psi.text == backup || !isRenamingCorrect) continue
            val res = tryToAdd(targetNode.psi, psi, placeToInsert)
            creator.updateCtx()
            ctx = creator.ctx!!
            if (res != null) {
                val diagnostics = (ctx.diagnostics as MutableDiagnosticsWithSuppression).getOwnDiagnostics()
                if (diagnostics.any { it.toString().contains("UNREACHABLE_CODE") }) {
                    log.debug("Expression makes some code unreachable")
                    res.replaceThis(psiFactory.createWhiteSpace("\n"))
                }
                if (res.getAllPSIChildrenOfType<KtNameReferenceExpression>().isEmpty()) {
                    log.debug("Expression using only constants and useless")
                    res.replaceThis(psiFactory.createWhiteSpace("\n"))
                }
                if (!checker.checkCompiling(psi)) {
                    log.debug("CANT COMPILE AFTER REMOVING $res")
                    psi = creator.getPSIForText(psiBackup)
                    ctx = creator.ctx!!
                }
            }
        }
        checker.curFile.changePsiFile(creator.getPSIForText(psi.text))
        //file = creator.getPSIForText(psi.text)
    }

    private fun tryToAdd(targetNode: PsiElement, psi: KtFile, placeToInsert: PsiElement): PsiElement? {
        val block = psiFactory.createBlock(targetNode.text)
        block.lBrace?.delete()
        block.rBrace?.delete()
        return checker.addNodeIfPossibleWithNode(placeToInsert, block)
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
            val node = nodesForChange.random()
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
                } else changeValuesInExpression(expressionsWhichWeCanPaste.random().first, psiCtx, table1)
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

    private fun changeValuesInExpression(
        node: PsiElement,
        ctx: BindingContext,
        fileTable: List<Triple<KtExpression, String, KotlinType?>>
    ): PsiElement {
        val nodeCopy = node.copy() as PsiElement
        while (true) {
            val table = node.getAllPSIChildrenOfType<KtExpression>()
                .map { it to it.getType(ctx) }
                .filter { it.second != null && !it.second!!.toString().contains("Nothing") }
            if (table.isEmpty() || Random.getTrue(20)) break
            val randomEl = table.randomOrNull() ?: continue
            val newEl = generateDefValuesAsString(randomEl.second!!.toString())
            //Get value of same type
            val newElFromProg = fileTable.filter { it.third == randomEl.second }.randomOrNull()
            when {
                newEl.isNotEmpty() && Random.nextBoolean() -> {
                    try {
                        psiFactory.createExpressionIfPossible(newEl)?.let { randomEl.first.replaceThis(it) }
                    } catch (e: Exception) {
                        log.debug("Cant create expression from $newEl")
                    }
                }
                newElFromProg != null && Random.nextBoolean() -> randomEl.first.replaceThis(newElFromProg.first.copy())
            }
        }
        //Return old node
        node.replaceThis(nodeCopy)
        return node.copy() as PsiElement
    }

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

    private fun updateReplacement(replacementNode: PsiElement, replacementCtx: BindingContext) =
        replacementNode.getAllPSIChildrenOfType<KtExpression>()
            .map { it to it.getType(replacementCtx) }
            .filter { it.second != null }

    private fun generateRandomName() = java.util.Random().getRandomVariableName(4)

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

    private fun <T> Collection<T>.randomOrNull(): T? = if (this.isEmpty()) null else this.random()

    private fun Random.getTrue(prob: Int): Boolean =
        Random.nextInt(0, 100) < prob

    private val randomConst = Random.nextInt(700, 1000)
}