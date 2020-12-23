package com.stepanov.bbf.bugfinder.mutator.transformations

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.generators.RandomPropertyGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GClass
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.RandomInstancesGenerator
import com.stepanov.bbf.bugfinder.util.addToTheEnd
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.bugfinder.util.getAllPSIDFSChildrenOfType
import com.stepanov.bbf.bugfinder.util.replaceThis
import com.stepanov.bbf.reduktor.parser.PSICreator
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.isPropertyParameter
import kotlin.system.exitProcess

class AddPropertiesToClass : Transformation() {


    override fun transform() {
        repeat(RAND_CONST) {
            val ctx = PSICreator.analyze(file) ?: return
            val randomKlass = file.getAllPSIChildrenOfType<KtClass>().randomOrNull() ?: return
            val gClass = GClass.fromPsi(randomKlass)
            val (genProp, rType) = RandomPropertyGenerator(file as KtFile, ctx, gClass).generateInterestingProperty()
                ?: return@repeat
            var r = randomKlass.addPsiToBody(genProp) ?: return@repeat
            if (r is KtBlockExpression) {
                val newR = r.getAllPSIChildrenOfType<KtProperty>().firstOrNull()
                if (newR != null) r = newR
            }
            val res = checker.checkCompiling()
            if (!res) {
                if (r is KtProperty) r.addModifier(KtTokens.OVERRIDE_KEYWORD)
                if (r is KtNamedFunction) r.addModifier(KtTokens.OVERRIDE_KEYWORD)
                if (!checker.checkCompiling()) {
                    if (r is KtProperty) r.addModifier(KtTokens.ABSTRACT_KEYWORD)
                    if (r is KtNamedFunction) r.addModifier(KtTokens.ABSTRACT_KEYWORD)
                    if (!checker.checkCompiling()) {
                        if (r is KtProperty && rType != null) {
                            val expr = RandomInstancesGenerator(file as KtFile).generateValueOfType(rType)
                            val psiExpr = Factory.psiFactory.createExpression(expr)
                            r.initializer = psiExpr
                            if (!checker.checkCompiling()) {
                                r.delete()
                            }
                        } else {
                            r.delete()
                        }
                    }
                }
            }
            val newKlass = Factory.psiFactory.createClass(randomKlass.text)
            randomKlass.replaceThis(newKlass)
        }
    }

    private val RAND_CONST = 50
}


fun KtClass.addPsiToBody(prop: PsiElement): PsiElement? {
    return this.body?.addBeforeRBrace(prop) ?: this.add(Factory.psiFactory.createBlock(prop.text))
}

fun KtClassBody.addBeforeRBrace(psiElement: PsiElement): PsiElement {
    return this.rBrace?.let { rBrace ->
        val ws = this.addBefore(Factory.psiFactory.createWhiteSpace("\n\n"), rBrace)
        val res = this.addAfter(psiElement, ws)
        this.addAfter(Factory.psiFactory.createWhiteSpace("\n\n"), res)
        res
    } ?: psiElement
}