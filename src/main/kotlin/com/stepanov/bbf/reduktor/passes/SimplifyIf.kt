package com.stepanov.bbf.reduktor.passes

import com.intellij.psi.PsiElement
import com.stepanov.bbf.reduktor.executor.CompilerTestChecker
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.util.replaceThis

import org.apache.log4j.Logger
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.allChildren

class SimplifyIf : SimplificationPass() {

    private fun replaceIfOnCondition(ifExpressions: List<KtIfExpression>, isIfExp: Boolean) {
        for (ifExp in ifExpressions) {
            if (ifExp.condition is KtBinaryExpression) {
                val cond = ifExp.condition as KtBinaryExpression
                if (cond.children.size >= 3) {
                    var res: PsiElement? = null
                    if (cond.operationReference.prevSibling != null) {
                        res = if (isIfExp) checker.replaceNodeOnItChild(ifExp.node, cond.left!!.node)?.psi
                        else checker.replaceNodeOnItChild(cond.node, cond.left!!.node)?.psi
                        if (res == null) continue
                    }
                    val binRes = res as? KtBinaryExpression
                    if (binRes != null && binRes.operationReference.nextSibling != null) {
                        if (checker.replaceNodeOnItChild(binRes.node, binRes.right!!.node) == null) continue
                    }
                }
            }
        }
    }

    private fun tryToReplaceCondOnConst(ifExpressions: List<KtIfExpression>) {
        for (ifExp in ifExpressions) {
            if (ifExp.condition != null) {
                val trueExp = psiFactory.createExpression("true")
                checker.replaceNodeIfPossible(ifExp.condition!!, trueExp)
            }
        }
    }

    private fun tryToReplaceCondOnItPart(ifExpressions: List<KtIfExpression>) {
        for (ifExp in ifExpressions) {
            ifExp.then?.let { then ->
                val oldIf = ifExp.copy() as KtIfExpression
                //Remove rbrace and lbrace from then
                if (then is KtBlockExpression) {
                    then.lBrace?.replaceThis(psiFactory.createWhiteSpace())
                    then.rBrace?.replaceThis(psiFactory.createWhiteSpace())
                }
                //Replace node if possible
                ifExp.replaceThis(then)
                if (!checker.checkTest()) {
                    if (ifExp.condition is KtIsExpression) {
                        val isExp = ifExp.condition as KtIsExpression
                        if (isExp.isNegated) {
                            then.replaceThis(oldIf)
                            return@let
                        }
                        val asExp =
                            psiFactory.createExpression("${isExp.leftHandSide.text} as ${isExp.typeReference?.text}")
                        then.addBefore(asExp, then.firstChild)
                        if (!checker.checkTest()) {
                            then.replaceThis(oldIf)
                        }
                    } else {
                        if (oldIf.`else` == null) {
                            then.replaceThis(oldIf)
                        } else {
                            val copy = oldIf.copy()
                            val el = oldIf.`else`!!
                            then.replaceThis(el)
                            if (!checker.checkTest()) {
                                el.replaceThis(copy)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun simplify() {
        //Try to replace if on it condition if condition is BinaryExpression
        replaceIfOnCondition(file.getAllPSIChildrenOfType<KtIfExpression>(), true)
        //Try to replace if condition is BinaryExpression on left or right side
        replaceIfOnCondition(file.getAllPSIChildrenOfType<KtIfExpression>(), false)
        //Try to replace if condition on constant
        tryToReplaceCondOnConst(file.getAllPSIChildrenOfType<KtIfExpression>())
        //Try to replace if on then
        tryToReplaceCondOnItPart(file.getAllPSIChildrenOfType<KtIfExpression>())

//        val ifExpressions = file.getAllPSIChildrenOfType<KtIfExpression>()
//        for (ifExp in ifExpressions) {
//            //Try to replace if on it condition if condition is BinaryExpression
//            if (ifExp.condition is KtBinaryExpression) {
//                val cond = ifExp.condition as KtBinaryExpression
//                if (cond.children.size >= 3) {
//                    var res: PsiElement? = null
//                    if (cond.operationReference.prevSibling != null) {
//                        res = checker.replaceNodeOnItChild(file, ifExp.node, cond.left!!.node)?.psi
//                        if (res == null) continue
//                    }
//                    val binRes = res as? KtBinaryExpression
//                    if (binRes != null && binRes.operationReference.nextSibling != null) {
//                        if (checker.replaceNodeOnItChild(file, binRes.node, binRes.right!!.node) == null) continue
//                    }
//                }
//            }

//            //Try to replace if condition is BinaryExpression on left or right side
//            if (ifExp.condition is KtBinaryExpression) {
//                val cond = ifExp.condition as KtBinaryExpression
//                if (cond.children.size >= 3) {
//                    var res: PsiElement? = null
//                    if (cond.operationReference.prevSibling != null) {
//                        res = checker.replaceNodeOnItChild(file, cond.node, cond.left!!.node)?.psi
//                        if (res == null) continue
//                    }
//                    val binRes = res as? KtBinaryExpression
//                    if (binRes != null && binRes.operationReference.nextSibling != null) {
//                        if (checker.replaceNodeOnItChild(file, binRes.node, binRes.right!!.node) == null) continue
//                    }
//                }
//            }

        //Try to replace if condition on constant
//            if (ifExp.condition != null && ifExp.then != null) {
//                val trueExp = psiFactory.createExpression("true")
//                val oldIfCopy = psiFactory.createIf(ifExp.condition!!, ifExp.then!!, ifExp.`else`)
//                val newIfExp = psiFactory.createIf(trueExp, ifExp.then!!, ifExp.`else`)
//                ifExp.replaceThis(newIfExp)
//                if (!checker.checkTest(file.text)) {
//                    newIfExp.replaceThis(oldIfCopy)
//                }
//            }

        //Try to replace if on then
//            ifExp.then?.let { then ->
//                val oldIf = ifExp.copy() as KtIfExpression
//                //Remove rbrace and lbrace from then
//                if (then is KtBlockExpression) {
//                    then.lBrace?.replaceThis(psiFactory.createWhiteSpace())
//                    then.rBrace?.replaceThis(psiFactory.createWhiteSpace())
//                }
//                //Replace node if possible
//                ifExp.replaceThis(then)
//                if (!checker.checkTest(file.text)) {
//                    if (ifExp.condition is KtIsExpression) {
//                        val isExp = ifExp.condition as KtIsExpression
//                        if (isExp.isNegated) {
//                            then.replaceThis(oldIf)
//                            return@let
//                        }
//                        val asExp =
//                            psiFactory.createExpression("${isExp.leftHandSide.text} as ${isExp.typeReference?.text}")
//                        then.addBefore(asExp, then.firstChild)
//                        if (!checker.checkTest(file.text)) {
//                            then.replaceThis(oldIf)
//                        }
//                    } else {
//                        if (oldIf.`else` == null) {
//                            then.replaceThis(oldIf)
//                        } else {
//                            val copy = oldIf.copy()
//                            val el = oldIf.`else`!!
//                            then.replaceThis(el)
//                            if (!checker.checkTest(file.text)) {
//                                el.replaceThis(copy)
//                            }
//                        }
//                    }
//                }
//            }
    }

    private val psiFactory = KtPsiFactory(file.project)
    private val log = Logger.getLogger("transformationManagerLog")
}
