package com.stepanov.bbf.bugfinder

import com.stepanov.bbf.bugfinder.executor.CommonCompiler
import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.executor.ProjectCompilationChecker
import com.stepanov.bbf.bugfinder.executor.TracesChecker
import com.stepanov.bbf.bugfinder.executor.compilers.JSCompiler
import com.stepanov.bbf.bugfinder.executor.compilers.JVMCompiler
import com.stepanov.bbf.bugfinder.executor.compilers.MutationChecker
import com.stepanov.bbf.bugfinder.manager.BugManager
import com.stepanov.bbf.bugfinder.manager.BugType
import com.stepanov.bbf.bugfinder.mutator.Mutator
import com.stepanov.bbf.bugfinder.mutator.transformations.Transformation
import com.stepanov.bbf.bugfinder.tracer.Tracer
import com.stepanov.bbf.bugfinder.util.BBFProperties
import com.stepanov.bbf.bugfinder.util.checkCompilingForAllBackends
import com.stepanov.bbf.bugfinder.util.getRandomVariableName
import com.stepanov.bbf.reduktor.parser.PSICreator
import org.apache.log4j.Logger
import org.jetbrains.kotlin.psi.KtPsiFactory
import java.io.File
import java.util.*

class BugFinder(private val path: String) : Runnable {

    override fun run() {
        findBugsInFile()
    }

    fun findBugsInFile() {
        try {
            println("Let's go")
            ++counter
            log.debug("Name = $path")
            val psiCreator = PSICreator("")
            val psiFile =
                    try {
                        psiCreator.getPSIForFile(path)
                    } catch (e: Throwable) {
                        println("e = $e")
                        return
                    }
            //Init compilers
            val compilersConf = BBFProperties.getStringGroupWithoutQuotes("BACKENDS")
            compilersConf.filter { it.key.contains("JVM") }.forEach { compilers.add(
                JVMCompiler(
                    it.value
                )
            ) }
            compilersConf.filter { it.key.contains("JS") }.forEach { compilers.add(
                JSCompiler(
                    it.value
                )
            ) }


            val filterBackends = compilersConf.map { it.key }
            val ignoreBackendsFromFile =
                    psiFile.text.lineSequence()
                            .filter { it.startsWith("// IGNORE_BACKEND:") }
                            .map { it.substringAfter("// IGNORE_BACKEND:") }
                            .map { it.split(",") }
                            .flatten()
                            .map { it.trim() }
                            .toList()
            if (ignoreBackendsFromFile.any { filterBackends.contains(it) }) {
                log.debug("Skipped because one of the backends is ignoring")
                return
            }

            //Init lateinit vars
            Transformation.file = psiFile
//            MutationChecker.factory = KtPsiFactory(psiFile.project)
//            MutationChecker.compilers = compilers

            //Check for compiling
            if (!compilers.checkCompilingForAllBackends(psiFile)) {
                log.debug("Could not compile $path")
                return
            }
            log.debug("Start to mutate")

            Mutator(psiFile, psiCreator.ctx, compilers).startMutate()
            val resultingMutant = PSICreator("").getPSIForText(Transformation.file.text)

            if (!compilers.checkCompilingForAllBackends(resultingMutant)) {
                log.debug("Could not compile after mutation $path")
                log.debug(resultingMutant.text)
                System.exit(1)
            }
            log.debug("Mutated = ${resultingMutant.text}")

            //Save mutated file
            if (CompilerArgs.shouldSaveMutatedFiles) {
                val pathToNewTests = CompilerArgs.dirForNewTests
                File(pathToNewTests).mkdirs()
                val pathToSave = "$pathToNewTests/${Random().getRandomVariableName(10)}.kt"
                File(pathToSave).writeText(resultingMutant.text)
            }

            val checker = MutationChecker(compilers)
            //Now begin to trace mutated file
            val tracer = Tracer(resultingMutant, psiCreator.ctx!!, checker)
            val traced = tracer.trace()
            log.debug("Traced = ${traced.text}")
            if (!compilers.checkCompilingForAllBackends(traced)) {
                log.debug("Could not compile after tracing $path")
                log.debug(traced.text)
            }

            val res = TracesChecker(compilers).checkTest(traced.text)
            log.debug("Result = $res")
            //Save into tmp file and reduce
            if (res != null) {
                File(CompilerArgs.pathToTmpFile).writeText(traced.text)
                val reduced =
                        if (CompilerArgs.shouldReduceDiffBehavior)
                            Reducer.reduceDiffBehavior(
                                CompilerArgs.pathToTmpFile,
                                compilers
                            )
                        else
                            traced.text
                BugManager.saveBug(res.joinToString(separator = ","), "", reduced, BugType.DIFFBEHAVIOR)
            }
            return
        } catch (e: Error) {
            println("ERROR: $e")
            log.debug("ERROR: $e")
            return
            //System.exit(0)
        }
    }

    private val compilers: MutableList<CommonCompiler> = mutableListOf()
    var counter = 0
    private val log = Logger.getLogger("bugFinderLogger")
}