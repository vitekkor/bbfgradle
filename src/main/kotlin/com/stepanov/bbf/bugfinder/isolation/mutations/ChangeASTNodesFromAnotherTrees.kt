package com.stepanov.bbf.bugfinder.isolation.mutations

import com.intellij.lang.ASTNode
import com.intellij.psi.impl.source.tree.TreeElement
import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.executor.MutationChecker
import com.stepanov.bbf.bugfinder.executor.compilers.JVMCompiler
import com.stepanov.bbf.bugfinder.mutator.transformations.Transformation
import com.stepanov.bbf.bugfinder.util.FilterDuplcatesCompilerErrors
import com.stepanov.bbf.bugfinder.util.NodeCollector
import com.stepanov.bbf.bugfinder.util.getAllChildrenNodes
import com.stepanov.bbf.bugfinder.util.getAllParentsWithoutNode
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.util.replaceThis
import org.jetbrains.kotlin.KtNodeTypes
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtFile
import java.io.File
import java.util.*
import kotlin.Comparator
import kotlin.random.Random
import kotlin.streams.toList

class ChangeASTNodesFromAnotherTrees(private val file: KtFile) {

    fun mutate(): List<KtFile> {
        val original = file.copy() as KtFile
        val goodMutants = mutableListOf<KtFile>()
        goodMutants.add(file)
        val nodes = file.node.getAllChildrenNodes()
            .filter { it.elementType !in NodeCollector.excludes }
            .filter { !KtTokens.WHITESPACES.contains(it.elementType) }
            .reversed()
        for (n in nodes) {
            println("n = $n")
            var i = 0
            val lines = File("database.txt").bufferedReader()
                .lines()
                .filter { it.takeWhile { it != ' ' } == n.elementType.toString() }
                .toList()
            if (lines.isEmpty()) continue
            val line = lines.first()
            println("GETTING FILE $line")
            val files = line.dropLast(1).takeLastWhile { it != '[' }.split(", ")
            while (true) {
                val randomFile =
                    if (files.size == 1)
                        files[0]
                    else
                        files[Random.nextInt(0, files.size - 1)]
                val psi = PSICreator("").getPSIForFile("${CompilerArgs.baseDir}/$randomFile")
                for (mutant in psi.node.getAllChildrenNodes().filter { it.elementType == n.elementType }) {
                    ++i
                    if (databaseNodeSet.any { it.text.trim() == mutant.text.trim() }) continue
                    databaseNodeSet.add(mutant)
                    val res = replaceNodeIfPossible(file, n, mutant)
                    println("$i Res = $res")
                    if (res) {
                        mutant.replaceThis(n)
                        goodMutants.add(file.copy() as KtFile)
                    }
                }
                if (i > someConst) {
                    databaseNodeSet.clear()
                    break
                }
            }
            println("\n\n")

        }
        println("SORTING")
        return goodMutants.sortedByDescending {
            FilterDuplcatesCompilerErrors.newCheckErrsMatching(
                original.text,
                it.text
            )
        }
    }

    fun replaceNodeIfPossible(file: KtFile, node: ASTNode, replacement: ASTNode): Boolean {
        if (node.text.isEmpty() || node == replacement) return false
        for (p in node.getAllParentsWithoutNode()) {
            try {
                if (node.treeParent.elementType.index == DUMMY_HOLDER_INDEX) continue
                val oldText = file.text
                val replCopy = replacement.copyElement()
                if ((node as TreeElement).treeParent !== p) {
                    continue
                }
                p.replaceChild(node, replCopy)
                if (oldText == file.text)
                    continue
                if (!compiler.checkCompilingText(file.text)) {
                    //println("checking ${file.text}")
                    p.replaceChild(replCopy, node)
                    return false
                } else {
                    return true
                }
            } catch (e: Error) {
            }
        }
        return false
    }

    val databaseNodeSet = mutableListOf<ASTNode>()
    private val compiler = JVMCompiler("")
    private val DUMMY_HOLDER_INDEX: Short = 86
    private val someConst = 100

}