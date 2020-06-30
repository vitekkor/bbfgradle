package com.stepanov.bbf.bugfinder.generator.constructor

import com.stepanov.bbf.bugfinder.executor.Checker
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.util.getAllChildrenOfType
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.bugfinder.util.replaceThis
import org.jetbrains.kotlin.KtNodeTypes
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.psiUtil.isTopLevelKtOrJavaMember
import org.jetbrains.kotlin.resolve.calls.callUtil.getType

class ProgramConstructor(checker: Checker) {

    fun construct(): KtFile {
        addDataStructures()
        println(resFile.text)
        addFunctions()
        System.exit(0)
        return resFile
    }

    private fun addDataStructures() {
        for (i in 0 until 3) {
            val ds = NodeDB.getRandomDataStructures()
            ds.forEach {
                constructionContext.classesAndUsages[it.key] = it.value
                if (!it.key.psi.isTopLevelKtOrJavaMember()) return@forEach
                resFile.add(it.key.psi)
                resFile.add(Factory.psiFactory.createWhiteSpace("\n\n"))
            }
        }
    }

    private fun addFunctions() {
        val (func, ctx) = NodeDB.getRandomNodeOfTypeFromRandomFileWithCtx(KtNodeTypes.FUN).let { it.first.psi to it.second }
        val expToReplace = func.getAllPSIChildrenOfType<KtExpression>()
            .map { it to it.getType(ctx) }
            .filter { it.second != null && it.second!!.toString() != "Nothing"}
        for ((expr, type) in expToReplace) {
            val randomInstanceOfType = constructionContext.getUsageOfType(type!!)
            if (randomInstanceOfType != null) {
                expr.replaceThis(randomInstanceOfType.node.psi)
            }
        }
        println(func.text)
    }

    val constructionContext = ConstructionContext()
    val resFile = Factory.psiFactory.createFile("")
}