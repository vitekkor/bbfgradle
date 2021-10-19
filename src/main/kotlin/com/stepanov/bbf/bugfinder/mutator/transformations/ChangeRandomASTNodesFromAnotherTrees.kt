package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.executor.CompilerArgs

import com.stepanov.bbf.bugfinder.util.NodeCollector
import com.stepanov.bbf.bugfinder.util.debugPrint
import com.stepanov.bbf.bugfinder.util.getAllChildrenNodes
import com.stepanov.bbf.bugfinder.util.getAllParentsWithoutNode
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.apache.log4j.Logger
import org.jetbrains.kotlin.KtNodeTypes
import org.jetbrains.kotlin.psi.KtConstantExpression
import org.jetbrains.kotlin.psi.KtIfExpression
import org.jetbrains.kotlin.psi.KtNameReferenceExpression
import org.jetbrains.kotlin.psi.KtNamedFunction
import java.io.File
import kotlin.random.Random
import kotlin.system.exitProcess


object ChangeRandomASTNodesFromAnotherTrees : Transformation() {

    override fun transform() {
        val nodes = file.node.getAllChildrenNodes().filter { it.elementType !in NodeCollector.excludes }
        val randomNode = nodes.randomOrNull() ?: return
        //Do not touch box func
        if (randomNode.psi is KtNamedFunction && randomNode.text.contains("fun box")) return
        //Searching nodes of same type in another files
        val line = File("database.txt").bufferedReader().lines()
            .filter { it.takeWhile { it != ' ' } == randomNode.elementType.toString() }.findFirst()
        if (!line.isPresent) return
        val files = line.get().dropLast(1).takeLastWhile { it != '[' }.split(", ")
        val randomFile = files.random()
        val psi = Factory.psiFactory.createFile(File("${CompilerArgs.baseDir}/$randomFile").readText())
        val targetNode = psi.node.getAllChildrenNodes().filter { it.elementType == randomNode.elementType }.random()
        if (targetNode.psi is KtConstantExpression) return
        checker.replaceNodeIfPossible(randomNode, targetNode)
    }
}