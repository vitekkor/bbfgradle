package com.stepanov.bbf.bugfinder.mutator.transformations

import com.intellij.psi.PsiFile
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.StdLibraryGenerator
import com.stepanov.bbf.bugfinder.util.addAfterThisWithWhitespace
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.bugfinder.util.getTrue
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.KtLabeledExpression
import org.jetbrains.kotlin.psi.psiUtil.parents
import kotlin.random.Random
import kotlin.system.exitProcess

object AddRandomControlStatements : Transformation() {
    override fun transform() {
        insertRandomStatement()
    }

    private fun insertRandomStatement() {
        val fileBackup = file.copy() as PsiFile
        val randomExp = file.getAllPSIChildrenOfType<KtExpression>().randomOrNull() ?: return
        val randomLabel =
            randomExp.parents.filter { it is KtLabeledExpression }.toList().randomOrNull() as? KtLabeledExpression
        val labelAsString =
            if (randomLabel != null && Random.getTrue(75))
                "@${randomLabel.getLabelName()}"
            else
                ""
        val randomToken =
            when (Random.nextInt(0, 10)) {
                in 0..1 -> "return"
                in 2..5 -> "break"
                else -> "continue"
            }
        val randomControlExpr =
            if (Random.getTrue(75)) {
                Factory.psiFactory.createExpression("$randomToken$labelAsString")
            } else {
                Factory.psiFactory.createExpression("throw ${listOfRandomExceptions}(\"\")")
            }
        if (Random.getTrue(90)) {
            randomExp.addAfterThisWithWhitespace(randomControlExpr, "\n")
            if (!checker.checkCompiling()) {
                checker.curFile.changePsiFile(fileBackup, false)
            }
        } else {
            checker.replaceNodeIfPossible(randomExp, randomControlExpr)
        }
    }

    private val listOfRandomExceptions = StdLibraryGenerator.getListOfExceptionsFromStdLibrary()
}