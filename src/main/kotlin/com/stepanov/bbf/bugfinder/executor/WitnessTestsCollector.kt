package com.stepanov.bbf.bugfinder.executor

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.manager.BugType
import com.stepanov.bbf.bugfinder.util.BoundedSortedByFirstElSet
import com.stepanov.bbf.bugfinder.util.FilterDuplcatesCompilerErrors.newCheckErrsMatching
import com.stepanov.bbf.reduktor.executor.CompilerTestChecker
import com.stepanov.bbf.reduktor.parser.PSICreator
import org.jetbrains.kotlin.psi.KtFile
import java.io.File

class WitnessTestsCollector(
    private val file: KtFile,
    bugType: BugType,
    private val compilers: List<CommonCompiler>
) : Checker() {

    override fun checkCompiling(file: KtFile): Boolean {
        if (!checker.checkTest(file.text))
            witnessDatabase.add(file)
        return false
    }

//    private fun handleOneCompilerBug(file: KtFile) {
//        val compiler = compilers.first()
//        if (!compiler.isCompilerBugForText(file.text)) {
//            witnessDatabase.add(file)
//        }
//    }


    override fun checkTextCompiling(text: String): Boolean = checkCompiling(PSICreator("").getPSIForText(text, false))


    val witnessDatabase = BoundedSortedByFirstElSet(
        file,
        100,
        Comparator { f1: KtFile, f2: KtFile -> (newCheckErrsMatching(f1.text, f2.text) * 100).toInt() }
    )

    val checker = when (bugType) {
        BugType.BACKEND -> MultiCompilerCrashChecker(compilers.first())
        BugType.DIFFBEHAVIOR -> DiffBehaviorChecker(compilers)
        BugType.DIFFCOMPILE -> DiffCompileChecker(compilers)
        BugType.FRONTEND -> MultiCompilerCrashChecker(compilers.first())
        BugType.UNKNOWN -> MultiCompilerCrashChecker(compilers.first())
    }

}