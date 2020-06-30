package com.stepanov.bbf.bugfinder.generator.constructor

import com.intellij.lang.ASTNode
import com.intellij.psi.tree.IElementType
import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.util.*
import com.stepanov.bbf.reduktor.parser.PSICreator
import org.jetbrains.kotlin.KtNodeTypes
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.calls.callUtil.getType
import java.io.File
import kotlin.random.Random
import kotlin.streams.toList

object NodeDB {

    // DataStructure to using examples
    fun getRandomDataStructures(): Map<ASTNode, List<UsingExample>?> {
        val resMap = mutableMapOf<ASTNode, List<UsingExample>?>()
        val type =
            when (Random.nextInt(0, 10)) {
                0 -> KtNodeTypes.OBJECT_DECLARATION
                else -> KtNodeTypes.CLASS
            }
        val psiWithNeededNodes = getRandomFileWithNodesOfType(type)
        val dataStructures = psiWithNeededNodes.getAllPSIChildrenOfType<KtClassOrObject>()
        val ctx = PSICreator.analyze(psiWithNeededNodes) ?: return mapOf()
        for (ds in dataStructures) {
            if (ds.name == null) continue
            val usingExamples =
                psiWithNeededNodes
                    .getAllPSIChildrenOfType<KtExpression> { it.text.contains(ds.name!!) }
                    .map { it to it.getType(ctx) }
                    .filter { it.first !is KtClassOrObject && it.second != null && it.second.toString() != "Nothing" }
                    .map { UsingExample(it.first.node, it.second!!) }
            resMap[ds.node] =
                if (usingExamples.isEmpty())
                    null
                else
                    usingExamples

        }
        //println(resMap.entries.map { it.value?.map { it.node.text to it.type } })
        return resMap
    }

    private fun getRandomFileWithNodesOfType(type: IElementType): KtFile {
        val line = file.filter { it.takeWhile { it != ' ' } == type.toString() }.first()
        val files = line.dropLast(1).takeLastWhile { it != '[' }.split(", ")
        val randomFile = files.random()
//        val randomFile = "genericClass.kt"
        println("NAME = $randomFile")
        val psi = Factory.psiFactory.createFile(File("${CompilerArgs.baseDir}/$randomFile").readText())
        return psi
    }

    fun getAllNodesOfTypeFromRandomFile(type: IElementType): List<ASTNode> {
        val psi = getRandomFileWithNodesOfType(type)
        return psi.node.getAllChildrenNodes().filter { it.elementType == type }
    }

    fun getAllNodesOfTypeFromRandomFileWithCtx(type: IElementType): Pair<List<ASTNode>, BindingContext> {
        val psi = getRandomFileWithNodesOfType(type)
        val ctx = PSICreator.analyze(psi)
        return psi.node.getAllChildrenNodes().filter { it.elementType == type } to ctx!!
    }

    fun getRandomNodeOfTypeFromRandomFile(type: IElementType): ASTNode = getAllNodesOfTypeFromRandomFile(type).random()
    fun getRandomNodeOfTypeFromRandomFileWithCtx(type: IElementType): Pair<ASTNode, BindingContext> =
        getAllNodesOfTypeFromRandomFileWithCtx(type).let { it.first.random() to it.second }


    private val file = File("database.txt").bufferedReader().lines().toList()
}