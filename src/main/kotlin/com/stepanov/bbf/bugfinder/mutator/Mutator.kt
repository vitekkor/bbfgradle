package com.stepanov.bbf.bugfinder.mutator

import com.intellij.psi.PsiFile
import com.stepanov.bbf.bugfinder.executor.LANGUAGE
import com.stepanov.bbf.bugfinder.executor.Project
import com.stepanov.bbf.bugfinder.mutator.javaTransformations.*
import com.stepanov.bbf.bugfinder.mutator.projectTransformations.ShuffleNodes
import com.stepanov.bbf.bugfinder.mutator.transformations.*
import com.stepanov.bbf.bugfinder.util.getFileLanguageIfExist
import org.apache.log4j.Logger
import org.jetbrains.kotlin.resolve.BindingContext
import kotlin.random.Random

class Mutator(val file: PsiFile, val context: BindingContext?) {

    private fun executeMutation(t: Transformation, probPercentage: Int = 50) {
        if (Random.nextInt(0, 100) < probPercentage) {
            try {
                t.transform()
            } catch (e: Exception) {
                log.debug("Exception ${e.localizedMessage}\n${e.stackTrace.toList().joinToString("\n") { "$it" }}")
                System.exit(1)
            } catch (e: Error) {
                log.debug("Error ${e.localizedMessage}\n${e.stackTrace.toList().joinToString("\n") { "$it" }}")
            }
        }
    }


    fun startMutate() {
        Transformation.file = file.copy() as PsiFile
        log.debug("Mutation started")
        when (file.text.getFileLanguageIfExist()) {
            LANGUAGE.JAVA -> startJavaMutations()
            else -> startKotlinMutations()
        }
        log.debug("End")
    }

    //Stub
    private fun startJavaMutations() {
        println("STARTING JAVA MUTATIONS")
        executeMutation(ChangeRandomJavaASTNodesFromAnotherTrees(), 100)
        println("END JAVA MUTATIONS")
        log.debug("Verify = ${verify()}")
        return
    }

    private fun startKotlinMutations() {
        //Set of transformations over PSI
        log.debug("File = ${file.name}")
        executeMutation(AddNullabilityTransformer())
        //AddNullabilityTransformer().transform()
        log.debug("After AddNullabilityTransformer = ${Transformation.file.text}")
        log.debug("Verify = ${verify()}")
        executeMutation(AddPossibleModifiers())
        //AddPossibleModifiers().transform()
        log.debug("After AddPossibleModifiers = ${Transformation.file.text}")
        log.debug("Verify = ${verify()}")
        executeMutation(AddReifiedToType())
        //AddReifiedToType().transform()
        log.debug("After AddReifiedToType = ${Transformation.file.text}")
        log.debug("Verify = ${verify()}")
        executeMutation(ChangeSmthToExtension())
        //ChangeSmthToExtension().transform()
        log.debug("After ChangeSmthToExtension = ${Transformation.file.text}")
        log.debug("Verify = ${verify()}")
        executeMutation(AddDefaultValueToArg())
        //AddDefaultValueToArg().transform()
        log.debug("After AddDefaultValueToArg = ${Transformation.file.text}")
        log.debug("Verify = ${verify()}")
        executeMutation(ChangeArgToAnotherValue())
        //ChangeArgToAnotherValue().transform()
        log.debug("After ChangeArgToAnotherValue = ${Transformation.file.text}")
        log.debug("Verify = ${verify()}")
        if (context != null)
            executeMutation(ReinitProperties(context))
        //ReinitProperties(context).transform()
        log.debug("After ReinitProperties = ${Transformation.file.text}")
        log.debug("Verify = ${verify()}")
        executeMutation(AddNotNullAssertions())
        //AddNotNullAssertions().transform()
        log.debug("After AddNotNullAssertions = ${Transformation.file.text}")
        log.debug("Verify = ${verify()}")
        executeMutation(AddBlockToExpression())
        //AddBlockToExpression().transform()
        log.debug("After AddBlockToExpression = ${Transformation.file.text}")
        log.debug("Verify = ${verify()}")
        executeMutation(ChangeOperators())
        //ChangeOperators().transform()
        log.debug("After ChangeOperators = ${Transformation.file.text}")
        log.debug("Verify = ${verify()}")
        executeMutation(ChangeConstants())
        //ChangeConstants().transform()
        log.debug("After ChangeConstants = ${Transformation.file.text}")
        log.debug("Verify = ${verify()}")
        if (context != null)
            executeMutation(ChangeTypes(context))
        //ChangeConstants().transform()
        log.debug("After ChangeTypes = ${Transformation.file.text}")
        log.debug("Verify = ${verify()}")
        executeMutation(ChangeReturnValueToConstant())
        //ChangeReturnValueToConstant().transform()
        log.debug("After ChangeReturnValueToConstant = ${Transformation.file.text}")
        log.debug("Verify = ${verify()}")
        //has the meaning?
        //ChangeVarToNull().transform()
        //executeMutation(RemoveRandomLines())
        //RemoveRandomLines().transform()
        //log.debug("After RemoveRandomLines = ${Transformation.file.text}")
        //log.debug("Verify = ${verify()}")
        executeMutation(AddBracketsToExpression())
        //AddBracketsToExpression().transform()
        log.debug("After AddBrackets = ${Transformation.file.text}")
        log.debug("Verify = ${verify()}")
        executeMutation(ChangeModifiers())
        //ChangeModifiers().transform()
        log.debug("After ChangeModifiers = ${Transformation.file.text}")
        log.debug("Verify = ${verify()}")
        if (context != null)
            executeMutation(AddSameFunctions(context))
        //AddSameFunctions(context!!).transform()
        log.debug("After AddSameFunctions = ${Transformation.file.text}")
        log.debug("Verify = ${verify()}")
        executeMutation(ChangeOperatorsToFunInvocations())
        //ChangeOperatorsToFunInvocations().transform()
        log.debug("After ChangeOperatorsToFunInvocations = ${Transformation.file.text}")
        log.debug("Verify = ${verify()}")
        if (isProject()) {
            executeMutation(ShuffleNodes(), 75)
            log.debug("After ShuffleNodes = ${Transformation.file.text}")
            log.debug("Verify = ${verify()}")
        } else {
            executeMutation(ChangeRandomASTNodes(), 75)
            log.debug("After ChangeRandomASTNodes = ${Transformation.file.text}")
            log.debug("Verify = ${verify()}")
        }
        executeMutation(AddNodesFromAnotherFiles(), 75)
        log.debug("After AddNodesFromAnotherFiles = ${Transformation.file.text}")
        log.debug("Verify = ${verify()}")
        executeMutation(AddFunInvocations(), 75)
        log.debug("After AddFunInvocations = ${Transformation.file.text}")
        log.debug("Verify = ${verify()}")
        executeMutation(ChangeRandomLines())
        log.debug("After ChangeRandomLines = ${Transformation.file.text}")
        log.debug("Verify = ${verify()}")
        executeMutation(ChangeRandomASTNodesFromAnotherTrees(), 75)
        log.debug("After ChangeRandomASTNodesFromAnotherTrees = ${Transformation.file.text}")
        log.debug("Verify = ${verify()}")
    }


    private fun isProject() = Transformation.checker.otherFiles != null

    private fun verify(): Boolean {
        val res = Transformation.checker.checkCompiling(Transformation.file, Transformation.checker.otherFiles)
        if (!res) {
            log.debug("Cant compile project ${Project(file) + Transformation.checker.otherFiles}")
            System.exit(1)
        }
        return res
    }
    //private fun verify(): String = "${compilers.checkCompilingForAllBackends(Transformation.file)}"
    //private fun verify(): String = Transformation.checker.isCompilationSuccessful()

    private val log = Logger.getLogger("mutatorLogger")

}