package com.stepanov.bbf.reduktor.passes

import com.stepanov.bbf.bugfinder.util.debugPrint
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.bugfinder.util.replaceThis
import com.stepanov.bbf.reduktor.executor.CompilerTestChecker
import org.jetbrains.kotlin.psi.*

class SimplifyContainerType : SimplificationPass() {

    override fun simplify() {
        file.getAllPSIChildrenOfType<KtProperty>().forEach { prop ->
            prop.initializer?.let { init ->
                val call = init.getAllPSIChildrenOfType<KtCallExpression>().firstOrNull() ?: return@forEach
                val initCopy = init.copy() as KtExpression
                val typeRefCopy = prop.typeReference?.copy() as KtTypeReference?
                prop.typeReference?.let { typeRef ->
                    typeRef.getAllPSIChildrenOfType<KtTypeReference>().firstOrNull()?.let { childType ->
                        typeRef.replaceThis(childType)
                    }
                }
                init.replaceThis(call)
                if (!checker.checkTest()) {
                    call.replaceThis(initCopy)
                    prop.typeReference?.replaceThis(typeRefCopy!!)
                }
            }
        }
        //Try to simplify all typeRefs with typeArgs
        try {
            file.getAllPSIChildrenOfType<KtTypeReference>()
                .filter { it.getAllPSIChildrenOfType<KtTypeReference>().isNotEmpty() }
                .forEach { typeRef ->
                    val child = typeRef.getAllPSIChildrenOfType<KtTypeReference>().first()
                    checker.replaceNodeIfPossible(typeRef, child)
                }

        } catch (e: Exception) {
            return
        }
    }
}