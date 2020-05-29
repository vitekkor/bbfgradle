package com.stepanov.bbf.reduktor.passes.slicer

import com.stepanov.bbf.reduktor.executor.CompilerTestChecker
import com.stepanov.bbf.reduktor.passes.SimplificationPass
import com.stepanov.bbf.reduktor.util.getAllChildrenNodes
import com.stepanov.bbf.reduktor.util.getLine

import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.getParentOfType

class Slicer(private val file: KtFile, private val checker: CompilerTestChecker) : KtVisitorVoid() {

    enum class Level {
        INTRAPROCEDURAL, FUNCTION, CLASS
    }

    fun computeSlice(line: Int, level: Level) {
        val endNode = file.getLine(line) ?: return
        if (endNode.text.isBlank())
            return
        when (level) {
            Level.CLASS -> {
                val func = endNode.psi.getParentOfType<KtNamedFunction>(false) ?: return
                val identifiers = func.node.getAllChildrenNodes().filter { it.elementType == KtTokens.EQ }.map { it.psi }
                ClassSlicer(checker).computeSlice(identifiers, file)
            }
            Level.INTRAPROCEDURAL -> {
                val slicer = IntraproceduralSlicer(checker)
                slicer.computeSlice(file, line)
                val newFile = slicer.getNewFile()
                file.node.replaceAllChildrenToChildrenOf(newFile.node)
            }
            Level.FUNCTION -> {
                val func = endNode.psi.getParentOfType<KtNamedFunction>(false) ?: return
                FunctionSlicer(checker).computeSlice(file, func)
            }
        }

    }

}