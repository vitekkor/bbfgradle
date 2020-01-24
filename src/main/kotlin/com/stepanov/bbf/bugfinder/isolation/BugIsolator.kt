package com.stepanov.bbf.bugfinder.isolation

import com.stepanov.bbf.bugfinder.executor.WitnessTestsCollector
import com.stepanov.bbf.bugfinder.executor.compilers.JVMCompiler
import com.stepanov.bbf.bugfinder.manager.BugType
import com.stepanov.bbf.bugfinder.mutator.transformations.*
import com.stepanov.bbf.bugfinder.util.BoundedSortedByFirstElSet
import com.stepanov.bbf.reduktor.parser.PSICreator
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.resolve.BindingContext

object BugIsolator {

    fun isolate(path: String, bugType: BugType): BoundedSortedByFirstElSet<KtFile> {
        val creator = PSICreator("")
        val file = creator.getPSIForFile(path)
        Transformation.file = file
        val collector = WitnessTestsCollector(bugType, listOf(JVMCompiler("")))
        Transformation.checker = collector
        mutate(creator.ctx)
        return collector.witnessDatabase
    }

    fun mutate(context: BindingContext?) {
        executeMutation(AddNullabilityTransformer())
        executeMutation(AddPossibleModifiers())
        executeMutation(AddReifiedToType())
        executeMutation(ChangeSmthToExtension())
        executeMutation(AddDefaultValueToArg())
        executeMutation(ChangeArgToAnotherValue())
        //ChangeArgToAnotherValue().transform()
        if (context != null)
            executeMutation(ReinitProperties(context))
        executeMutation(AddNotNullAssertions())
        executeMutation(AddBlockToExpression())
        executeMutation(ChangeOperators())
        executeMutation(ChangeConstants())
        executeMutation(ChangeReturnValueToConstant())
        executeMutation(ChangeRandomLines())
        executeMutation(RemoveRandomLines())
        executeMutation(AddBracketsToExpression())
        executeMutation(ChangeModifiers())
        if (context != null)
            executeMutation(AddSameFunctions(context))
        executeMutation(ChangeOperatorsToFunInvocations())
        executeMutation(ChangeRandomASTNodes())
        executeMutation(ChangeRandomASTNodesFromAnotherTrees())
    }

    private fun executeMutation(t: Transformation) {
        t.transform()
    }
}