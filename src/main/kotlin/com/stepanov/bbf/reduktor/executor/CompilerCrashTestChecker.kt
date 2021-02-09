package com.stepanov.bbf.reduktor.executor

import com.intellij.lang.ASTNode
import com.intellij.lang.FileASTNode
import com.intellij.psi.PsiErrorElement
import com.intellij.psi.PsiFile
import com.stepanov.bbf.reduktor.executor.backends.CommonBackend
import com.stepanov.bbf.reduktor.executor.error.Error
import com.stepanov.bbf.reduktor.util.getAllChildrenNodes
import com.stepanov.bbf.reduktor.util.getAllParentsWithoutNode
import com.stepanov.bbf.reduktor.util.replaceThis
import org.apache.log4j.Logger
import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageLocation
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSourceLocation
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtPsiFactory
import java.io.File

class KotlincInvokeStatus(
        val combinedOutput: String,
        val isCompileSuccess: Boolean,
        val hasException: Boolean,
        val hasTimeout: Boolean,
        val locations: List<CompilerMessageSourceLocation> = listOf()
) {
    fun hasCompilerCrash(): Boolean = hasTimeout || hasException

    fun hasCompilationError(): Boolean = !isCompileSuccess
}