package com.stepanov.bbf.bugfinder.executor.checkers

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.executor.CommonCompiler
import com.stepanov.bbf.bugfinder.executor.project.BBFFile
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.mutator.metamorphicTransformations.MetamorphicTransformation

class MetamorphicMutationChecker(compilers: List<CommonCompiler>, project: Project, curFile: BBFFile) :
    MutationChecker(compilers, project, curFile) {
    override fun addNodeIfPossible(anchor: PsiElement, node: PsiElement, before: Boolean): Boolean {
        log.debug("Trying to add $node to $anchor")
        if (node.text.isEmpty() || node == anchor) return checkCompilingWithBugSaving(
            project,
            curFile,
            MetamorphicTransformation.originalProject
        )
        try {
            val addedNode =
                if (before) anchor.parent.addBefore(node, anchor)
                else anchor.parent.addAfter(node, anchor)
            if (checkCompilingWithBugSaving(project, curFile, MetamorphicTransformation.originalProject)) {
                log.debug("Result = true\nText:\n${curFile.text}")
                return true
            }
            log.debug("Result = false\nText:\n${curFile.text}")
            addedNode.parent.node.removeChild(addedNode.node)
            return false
        } catch (e: Throwable) {
            println("e = $e")
            return false
        }
    }
}