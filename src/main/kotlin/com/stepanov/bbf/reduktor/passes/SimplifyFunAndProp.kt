package com.stepanov.bbf.reduktor.passes

import com.intellij.psi.PsiElement
import com.stepanov.bbf.reduktor.executor.CompilerTestChecker
import com.stepanov.bbf.reduktor.util.*

import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.allChildren

class SimplifyFunAndProp : SimplificationPass() {

    override fun simplify() {
        file.importList?.let {
            it.allChildren.toList().forEach { im ->
                if (im !is KtImportDirective)
                    return@forEach
                checker.removeNodeIfPossible(im.node)
            }
        }
        val funcs = file.getAllPSIChildrenOfType<KtNamedFunction>()
        val funcsToTransform = funcs
                .sortedByDescending { it.textLength }
                .filterNot { it.bodyExpression?.text?.contains("TODO()") ?: false }
                .filter { it.bodyExpression?.text?.isNotBlank() ?: false }
        for (f in funcsToTransform) {
            val copy = f.copy()
            if (!checker.removeNodeIfPossible(f.node)) {
                f.initBodyByTODO(psiFactory)
                checkAndReplaceBackIfError(f, copy)
            }
        }
        val props = file.getAllPSIChildrenOfType<KtProperty>().toMutableList()
        //Kostyl for property in property
        val propsToRemove = mutableListOf<KtProperty>()
        props.forEach {
            it.node.getAllChildrenNodes().find { it.psi is KtProperty }?.let {
                propsToRemove.add(it.psi as KtProperty)
            }
        }
        props.removeIf { propsToRemove.contains(it) || it.initializer == null }
        for (p in props) {
            val copy = p.copy() as KtProperty
            if (!checker.removeNodeIfPossible(p.node)) {
                p.initByTODO(psiFactory)
                if (checkAndReplaceBackIfError(p, copy) == copy) {
                    val initializerCopy = copy.initializer!!.copy()
                    copy.replaceThis(initializerCopy)
                    checkAndReplaceBackIfError(initializerCopy, copy)
                }
            }
        }
    }

    private fun checkAndReplaceBackIfError(prop: PsiElement, oldCopy: PsiElement): PsiElement {
        if (!checker.checkTest()) {
            prop.replaceThis(oldCopy)
            return oldCopy
        }
        return prop
    }

    val file = checker.curFile.psiFile as KtFile
    val psiFactory = KtPsiFactory(file.project)
}