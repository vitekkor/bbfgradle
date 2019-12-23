//Created by Anastasiya Shadrina (github.com/shadrina)

package com.stepanov.bbf.bugfinder.duplicates.engine

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiWhiteSpace
import com.stepanov.bbf.bugfinder.duplicates.engine.conversion.Converter
import com.stepanov.bbf.bugfinder.duplicates.engine.conversion.DiffChunk
import com.stepanov.bbf.bugfinder.duplicates.engine.matching.MatchingManager
import com.stepanov.bbf.bugfinder.duplicates.engine.transforming.EditScriptGenerator
import com.stepanov.bbf.bugfinder.duplicates.lang.ContextLevel
import com.stepanov.bbf.bugfinder.duplicates.lang.LangCfg
import com.stepanov.bbf.bugfinder.duplicates.util.BinaryRelation
import com.stepanov.bbf.bugfinder.duplicates.util.DeltaTreeElement
import com.stepanov.bbf.bugfinder.duplicates.util.InputTuple


class Diff(private val langCfg: LangCfg) {
    fun diff(root1: PsiElement, root2: PsiElement) : List<DiffChunk>? {
        val relation: BinaryRelation<DeltaTreeElement> = BinaryRelation()

        val deltaTree = buildDeltaTree(root1.node)
        val goldTree = buildDeltaTree(root2.node)
        deltaTree.setContext(langCfg.contextManager.initialContextLevel)
        goldTree.setContext(langCfg.contextManager.initialContextLevel)

        MatchingManager(langCfg, relation).match(deltaTree, goldTree)

        val script = EditScriptGenerator.generateScript(InputTuple(deltaTree, goldTree, relation))
        return if (script !== null) Converter.convert(script) else null
    }

    private fun buildDeltaTree(node: ASTNode) : DeltaTreeElement {
        val root = DeltaTreeElement(
                node.psi,
                node.elementType,
                node.text
        )

        var nextChild = node.firstChildNode
        while (nextChild !== null) {
            if (nextChild !is PsiWhiteSpace)
                root.addChild(buildDeltaTree(nextChild))
            nextChild = nextChild.treeNext
        }

        root.identify()
        return root
    }

    private fun DeltaTreeElement.identify() {
        val identifierNode = children.find { it.name == "identifier" }
        if (identifierNode !== null) id = identifierNode.text
    }

    private fun DeltaTreeElement.setContext(currentContextLevel: ContextLevel) {
        this.contextLevel = currentContextLevel
        children.forEach { it.setContext(langCfg.contextManager.getNewContext(this, currentContextLevel)) }
    }
}