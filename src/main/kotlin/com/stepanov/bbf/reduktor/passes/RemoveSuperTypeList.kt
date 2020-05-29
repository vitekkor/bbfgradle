package com.stepanov.bbf.reduktor.passes

import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.util.replaceReturnValueTypeOnAny
import com.stepanov.bbf.reduktor.util.replaceThis

import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.KtPsiFactory

class RemoveSuperTypeList : SimplificationPass() {

    override fun simplify() {
        //file.getAllPSIChildrenOfType<KtClass>().map { tryToRemove(it) }
    }


    private fun tryToRemove(c: KtClass) {
        val oldClass = c.copy() as KtClass
        c.superTypeListEntries.map { c.removeSuperTypeListEntry(it) }
        c.getProperties().forEach { it.removeModifier(KtTokens.OVERRIDE_KEYWORD) }
        c.getAllPSIChildrenOfType<KtNamedFunction>().forEach {
            it.removeModifier(KtTokens.OVERRIDE_KEYWORD)
            if (it.typeReference == null && it.text.contains("TODO()"))
                it.replaceReturnValueTypeOnAny(KtPsiFactory(file.project))
        }
        //TODO if nothing changed dont check
        if (!checker.checkTest())
            c.replaceThis(oldClass)
    }
}