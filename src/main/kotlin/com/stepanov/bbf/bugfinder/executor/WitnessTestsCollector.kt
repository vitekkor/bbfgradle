package com.stepanov.bbf.bugfinder.executor

import com.stepanov.bbf.bugfinder.manager.BugType
import com.stepanov.bbf.bugfinder.mutator.transformations.Transformation.Companion.file
import com.stepanov.bbf.bugfinder.util.BoundedSortedByFirstElSet
import com.stepanov.bbf.bugfinder.util.FilterDuplcatesCompilerErrors.newCheckErrsMatching
import com.stepanov.bbf.reduktor.parser.PSICreator
import org.jetbrains.kotlin.psi.KtFile

class WitnessTestsCollector(
    bugType: BugType,
    private val compilers: List<CommonCompiler>
) : Checker() {

    override fun checkCompiling(file: KtFile): Boolean {
        if (!checker.checkTest(file.text)) {
            witnessDatabase.add(file.copy() as KtFile)
        }
        return false
    }

    override fun checkTextCompiling(text: String): Boolean = checkCompiling(PSICreator("").getPSIForText(text, false))


    val witnessDatabase = BoundedSortedByFirstElSet(
        file.copy() as KtFile,
        100,
        Comparator { f1: KtFile, f2: KtFile -> ((-newCheckErrsMatching(f1.text, f2.text) * 100 + 50) * 2 + 0.9).toInt() }
    )

    val checker = when (bugType) {
        BugType.BACKEND -> MultiCompilerCrashChecker(compilers.first())
        BugType.DIFFBEHAVIOR -> DiffBehaviorChecker(compilers)
        BugType.DIFFCOMPILE -> DiffCompileChecker(compilers)
        BugType.FRONTEND -> MultiCompilerCrashChecker(compilers.first())
        BugType.UNKNOWN -> MultiCompilerCrashChecker(compilers.first())
    }

    init {
        checker.pathToFile = file.name
    }

}