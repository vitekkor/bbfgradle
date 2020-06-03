package com.stepanov.bbf.bugfinder.mutator

import com.stepanov.bbf.bugfinder.executor.project.LANGUAGE
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.mutator.javaTransformations.*
import com.stepanov.bbf.bugfinder.mutator.projectTransformations.ShuffleNodes
import com.stepanov.bbf.bugfinder.mutator.transformations.*
import org.apache.log4j.Logger
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