package com.stepanov.bbf.reduktor.passes

import com.stepanov.bbf.bugfinder.util.generateDefValuesAsString
import com.stepanov.bbf.reduktor.executor.CompilerTestChecker
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtPsiFactory
import org.jetbrains.kotlin.psi.KtTypeReference

class TypeChanger(private val file: KtFile, private val checker: CompilerTestChecker) {

    fun transform() {
        val types = file.getAllPSIChildrenOfType<KtTypeReference>()
        types.forEach { type ->
            if (standardTypes.any { type.text.contains(it) }) return@forEach
            for (standardType in standardTypes) {
                if (checker.replaceNodeIfPossible(file, type, KtPsiFactory(file.project).createType(standardType))) break
            }
        }
    }

    private val standardTypes = listOf("Int", "Any", "Double", "Char")
}