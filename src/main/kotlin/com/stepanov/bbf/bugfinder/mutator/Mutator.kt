package com.stepanov.bbf.bugfinder.mutator

import com.intellij.psi.PsiFile
import com.stepanov.bbf.bugfinder.executor.project.BBFFileFactory
import com.stepanov.bbf.bugfinder.executor.project.LANGUAGE
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.mutator.javaTransformations.*
import com.stepanov.bbf.bugfinder.mutator.projectTransformations.ShuffleNodes
import com.stepanov.bbf.bugfinder.mutator.transformations.*
import com.stepanov.bbf.bugfinder.executor.checkers.MutationChecker
import com.stepanov.bbf.bugfinder.util.getFileLanguageIfExist
import org.apache.log4j.Logger
import org.jetbrains.kotlin.resolve.BindingContext
import kotlin.random.Random

class Mutator(val project: Project) {

    private fun executeMutation(t: Transformation, probPercentage: Int = 50) {
        if (Random.nextInt(0, 100) < probPercentage) {
            try {
                t.transform()
                log.debug("After ${t::class.simpleName} = ${Transformation.checker.curFile.text}")
                log.debug("Verify = ${verify()}")
                //Update ctx and file
                checker.curFile.changePsiFile(checker.curFile.text)
                //val newFile = Project.createFromCode(Transformation.checker.curFile.text).files.first()
                //Transformation.checker.curFile = newFile
            } catch (e: Exception) {
                log.debug("Exception ${e.localizedMessage}\n${e.stackTrace.toList().joinToString("\n") { "$it" }}")
                System.exit(1)
            } catch (e: Error) {
                log.debug("Error ${e.localizedMessage}\n${e.stackTrace.toList().joinToString("\n") { "$it" }}")
            }
        }
    }


    fun startMutate() {
        for (bbfFile in project.files) {
            log.debug("Mutation of ${bbfFile.name} started")
            Transformation.checker.curFile = bbfFile
            when (bbfFile.getLanguage()) {
                LANGUAGE.JAVA -> startJavaMutations()
                else -> startKotlinMutations()
            }
            log.debug("End")
        }
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
        executeMutation(AddNullabilityTransformer())
        executeMutation(AddPossibleModifiers())
        executeMutation(AddReifiedToType())
        executeMutation(ChangeSmthToExtension())
        executeMutation(AddDefaultValueToArg())
        executeMutation(ChangeArgToAnotherValue())
        executeMutation(ReinitProperties())
        executeMutation(AddNotNullAssertions())
        executeMutation(AddBlockToExpression())
        executeMutation(ChangeOperators())
        executeMutation(ChangeConstants())
        executeMutation(ChangeTypes())
        executeMutation(ChangeReturnValueToConstant())
        //executeMutation(RemoveRandomLines())
        executeMutation(AddBracketsToExpression())
        executeMutation(ChangeModifiers())
        executeMutation(AddSameFunctions())
        executeMutation(ChangeOperatorsToFunInvocations())
        if (project.files.size > 1) {
            executeMutation(ShuffleNodes(), 75)
        } else {
            executeMutation(ChangeRandomASTNodes(), 75)
        }
        executeMutation(AddNodesFromAnotherFiles(), 75)
        executeMutation(AddFunInvocations(), 75)
        executeMutation(ChangeRandomLines())
        executeMutation(ChangeRandomASTNodesFromAnotherTrees(), 75)

//        //AddNullabilityTransformer().transform()
//        log.debug("After AddNullabilityTransformer = ${curFile.text}")
//        log.debug("Verify = ${verify()}")
//        executeMutation(AddPossibleModifiers())
//        //AddPossibleModifiers().transform()
//        log.debug("After AddPossibleModifiers = ${curFile.text}")
//        log.debug("Verify = ${verify()}")
//        executeMutation(AddReifiedToType())
//        //AddReifiedToType().transform()
//        log.debug("After AddReifiedToType = ${curFile.text}")
//        log.debug("Verify = ${verify()}")
//        executeMutation(ChangeSmthToExtension())
//        //ChangeSmthToExtension().transform()
//        log.debug("After ChangeSmthToExtension = ${curFile.text}")
//        log.debug("Verify = ${verify()}")
//        executeMutation(AddDefaultValueToArg())
//        //AddDefaultValueToArg().transform()
//        log.debug("After AddDefaultValueToArg = ${curFile.text}")
//        log.debug("Verify = ${verify()}")
//        executeMutation(ChangeArgToAnotherValue())
//        //ChangeArgToAnotherValue().transform()
//        log.debug("After ChangeArgToAnotherValue = ${curFile.text}")
//        log.debug("Verify = ${verify()}")
//        if (curFile.ctx != null)
//            executeMutation(ReinitProperties(curFile.ctx))
//        //ReinitProperties(context).transform()
//        log.debug("After ReinitProperties = ${curFile.text}")
//        log.debug("Verify = ${verify()}")
//        executeMutation(AddNotNullAssertions())
//        //AddNotNullAssertions().transform()
//        log.debug("After AddNotNullAssertions = ${curFile.text}")
//        log.debug("Verify = ${verify()}")
//        executeMutation(AddBlockToExpression())
//        //AddBlockToExpression().transform()
//        log.debug("After AddBlockToExpression = ${curFile.text}")
//        log.debug("Verify = ${verify()}")
//        executeMutation(ChangeOperators())
//        //ChangeOperators().transform()
//        log.debug("After ChangeOperators = ${curFile.text}")
//        log.debug("Verify = ${verify()}")
//        executeMutation(ChangeConstants())
//        //ChangeConstants().transform()
//        log.debug("After ChangeConstants = ${curFile.text}")
//        log.debug("Verify = ${verify()}")
//        if (curFile.ctx != null)
//            executeMutation(ChangeTypes(curFile.ctx))
//        //ChangeConstants().transform()
//        log.debug("After ChangeTypes = ${curFile.text}")
//        log.debug("Verify = ${verify()}")
//        executeMutation(ChangeReturnValueToConstant())
//        //ChangeReturnValueToConstant().transform()
//        log.debug("After ChangeReturnValueToConstant = $curFile.text}")
//        log.debug("Verify = ${verify()}")
//        //has the meaning?
//        //ChangeVarToNull().transform()
//        //executeMutation(RemoveRandomLines())
//        //RemoveRandomLines().transform()
//        //log.debug("After RemoveRandomLines = ${Transformation.file.text}")
//        //log.debug("Verify = ${verify()}")
//        executeMutation(AddBracketsToExpression())
//        //AddBracketsToExpression().transform()
//        log.debug("After AddBrackets = ${curFile.text}")
//        log.debug("Verify = ${verify()}")
//        executeMutation(ChangeModifiers())
//        //ChangeModifiers().transform()
//        log.debug("After ChangeModifiers = ${curFile.text}")
//        log.debug("Verify = ${verify()}")
//        if (curFile.ctx != null)
//            executeMutation(AddSameFunctions(curFile.ctx))
//        //AddSameFunctions(context!!).transform()
//        log.debug("After AddSameFunctions = ${curFile.text}")
//        log.debug("Verify = ${verify()}")
//        executeMutation(ChangeOperatorsToFunInvocations())
//        //ChangeOperatorsToFunInvocations().transform()
//        log.debug("After ChangeOperatorsToFunInvocations = ${curFile.text}")
//        log.debug("Verify = ${verify()}")
//        if (isProject()) {
//            executeMutation(ShuffleNodes(), 75)
//            log.debug("After ShuffleNodes = ${curFile.text}")
//            log.debug("Verify = ${verify()}")
//        } else {
//            executeMutation(ChangeRandomASTNodes(), 75)
//            log.debug("After ChangeRandomASTNodes = ${curFile.text}")
//            log.debug("Verify = ${verify()}")
//        }
//        executeMutation(AddNodesFromAnotherFiles(), 75)
//        log.debug("After AddNodesFromAnotherFiles = ${curFile.text}")
//        log.debug("Verify = ${verify()}")
//        executeMutation(AddFunInvocations(), 75)
//        log.debug("After AddFunInvocations = ${curFile.text}")
//        log.debug("Verify = ${verify()}")
//        executeMutation(ChangeRandomLines())
//        log.debug("After ChangeRandomLines = ${curFile.text}")
//        log.debug("Verify = ${verify()}")
//        executeMutation(ChangeRandomASTNodesFromAnotherTrees(), 75)
//        log.debug("After ChangeRandomASTNodesFromAnotherTrees = ${curFile.text}")
//        log.debug("Verify = ${verify()}")
    }


    private fun isProject() = project.files.size > 1

    private fun verify(): Boolean {
        val res = Transformation.checker.checkCompiling(project)
        if (!res) {
            log.debug("Cant compile project ${project}")
            System.exit(1)
        }
        return res
    }
    //private fun verify(): String = "${compilers.checkCompilingForAllBackends(Transformation.file)}"
    //private fun verify(): String = Transformation.checker.isCompilationSuccessful()

    private val log = Logger.getLogger("mutatorLogger")
    private val checker
        get() = Transformation.checker

}