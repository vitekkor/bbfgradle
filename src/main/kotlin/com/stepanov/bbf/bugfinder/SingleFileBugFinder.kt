package com.stepanov.bbf.bugfinder

import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.executor.checkers.CoverageGuider
import com.stepanov.bbf.bugfinder.executor.checkers.MutationChecker
import com.stepanov.bbf.bugfinder.executor.checkers.TracesChecker
import com.stepanov.bbf.bugfinder.executor.compilers.JVMCompiler
import com.stepanov.bbf.bugfinder.executor.project.LANGUAGE
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.tracer.Tracer
import com.stepanov.bbf.bugfinder.util.*
import com.stepanov.bbf.reduktor.parser.PSICreator
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtEnumEntry
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtNamedFunction
import java.io.File
import kotlin.system.exitProcess

class SingleFileBugFinder(dir: String) : BugFinder(dir) {

    //TODO RETURN BACK TRY
    fun findBugsInFile() {
        try {
            println("Let's go")
            ++counter
            log.debug("Name = $dir")
            var project = Project.createFromCode(File(dir).readText())
            PSICreator.curProject = project
            //TEMPORARY!! FIX THIS TODO
            if (project.language != LANGUAGE.KOTLIN) CompilerArgs.isMiscompilationMode = false
            if (project.files.isEmpty()) {
                log.debug("Cant create project")
                return
            }
            //TODO fuzz with coroutines also
            if (project.configuration.isWithCoroutines()) return
            if (project.files.size > 1 && project.language == LANGUAGE.KOTLIN) {
                project = project.convertToSingleFileProject()
            }
//            if (project.files.first().psiFile.getAllPSIChildrenOfType<KtNamedFunction> { it.isTopLevel }.size < 2) {
//                if (project.files.first().psiFile.getAllPSIChildrenOfTwoTypes<KtClassOrObject, KtEnumEntry>().isEmpty()) {
//                    log.debug("Uninteresting test")
//                    return
//                }
//            }
            //Add some imports
            for (f in project.files.map { it.psiFile }) {
                if (f !is KtFile) continue
                f.addImport("kotlin.properties", true)
                f.addImport("kotlin.reflect", true)
                f.addImport("kotlin.math", true)
            }
            val compilersConf = BBFProperties.getStringGroupWithoutQuotes("BACKENDS")
            val filterBackends = compilersConf.map { it.key }
            if (filterBackends.any { project.isBackendIgnores(it) }) {
                //TODO disable?
                log.debug("Ignore some of backends")
            }
            if (compilers.any { !it.checkCompiling(project) }) {
                log.debug("Can not compile $dir")
                return
            }
            log.debug("Start to mutate")
            log.debug("BEFORE = ${project.files.first().text}")
//            CompilerArgs.isInstrumentationMode = false
            if (CompilerArgs.isGuidedByCoverage) {
                CoverageGuider.init("", project)
            }
            //ProjectPreprocessor.preprocess(project, null)
            val checker = MutationChecker(compilers, project, project.files.first())
            if (!checker.checkCompiling()) {
                log.debug("=(")
                exitProcess(0)
            }
            //noLastLambdaInFinallyBlock temporary for avoiding duplicates bugs
            mutate(project, project.files.first(), listOf(::noBoxFunModifying, ::noLastLambdaInFinallyBlock))
//            //Save mutated file
//            if (CompilerArgs.shouldSaveMutatedFiles) {
//                val pathToNewTests = CompilerArgs.dirForNewTests
//                File(pathToNewTests).mkdirs()
//                val pathToSave = "$pathToNewTests/${Random().getRandomVariableName(10)}.kt"
//                File(pathToSave).writeText(resultingMutant.text)
//            }
            log.debug("Mutated = $project")
//            if (!CompilerArgs.isMiscompilationMode) {
//                Tracer(compilers.first(), project).trace()
//                log.debug("Traced = $project")
//                TracesChecker(compilers).checkBehavior(project)
//            }
            return
        } catch (e: Error) {
            log.debug("ERROR: ${e.localizedMessage}\n${e.stackTrace.map { it.toString() + "\n" }}")
            return
            //System.exit(0)
        }
    }

    var counter = 0
}