package com.stepanov.bbf.bugfinder

import com.stepanov.bbf.bugfinder.executor.checkers.TracesChecker
import com.stepanov.bbf.bugfinder.executor.compilers.JVMCompiler
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.tracer.Tracer
import com.stepanov.bbf.bugfinder.util.*
import java.io.File

class SingleFileBugFinder(dir: String) : BugFinder(dir) {

    fun findBugsInFile() {
        try {
            println("Let's go")
            ++counter
            log.debug("Name = $dir")
            val project = Project.createFromCode(File(dir).readText())
            if (project.files.isEmpty()) {
                log.debug("Cant create project")
                return
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
            mutate(project, project.files.first(), listOf(::noBoxFunModifying))
//            //Save mutated file
//            if (CompilerArgs.shouldSaveMutatedFiles) {
//                val pathToNewTests = CompilerArgs.dirForNewTests
//                File(pathToNewTests).mkdirs()
//                val pathToSave = "$pathToNewTests/${Random().getRandomVariableName(10)}.kt"
//                File(pathToSave).writeText(resultingMutant.text)
//            }
            log.debug("Mutated = $project")
            Tracer(compilers.first(), project).trace()
            log.debug("Traced = $project")
            TracesChecker(compilers).checkTest(project)
            return
        } catch (e: Error) {
            log.debug("ERROR: $e")
            return
            //System.exit(0)
        }
    }

    var counter = 0
}