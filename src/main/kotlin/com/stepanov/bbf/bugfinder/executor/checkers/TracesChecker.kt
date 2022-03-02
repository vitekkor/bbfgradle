package com.stepanov.bbf.bugfinder.executor.checkers

import com.stepanov.bbf.bugfinder.executor.COMPILE_STATUS
import com.stepanov.bbf.bugfinder.executor.CommonCompiler
import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.manager.Bug
import com.stepanov.bbf.bugfinder.manager.BugManager
import com.stepanov.bbf.bugfinder.manager.BugType
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.util.Stream
import com.stepanov.bbf.bugfinder.util.addToTheTop
import com.stepanov.bbf.bugfinder.util.checkCompilingForAllBackends
import com.stepanov.bbf.bugfinder.util.instrumentation.CoverageGuidingCoefficients
import org.apache.logging.log4j.LogManager
import java.io.File

enum class CHECKRES(val scoreForBug: Int) {
    BUG(CoverageGuidingCoefficients.SCORES_FOR_BUG), OK(0), NOT_OK(-1)
}

// Transformation is here only for PSIFactory
class TracesChecker(private val compilers: List<CommonCompiler>) : CompilationChecker(compilers) {


    //Regex and replacing
    val exclErrorMessages = listOf(
        "IndexOutOfBoundsException",
        "ArithmeticException",
        "Annotation class cannot be instantiated",
        "KotlinReflectionInternalError" //TODO!!
    )

    private companion object DuplicatesFilter {

        fun filterDuplicates(dir: String) {
            val files = File(dir).listFiles().sortedByDescending { it.lastModified() }
            val res = mutableListOf(files.first().let { it to it.lastModified() })
            for (f in files.drop(1)) {
                val date = f.lastModified()
                val fileFromRes = res.last()
                val diffInMinutes = (fileFromRes.second - date) / 1000 / 60
                if (diffInMinutes > 20) {
                    res.add(f to date)
                }
            }
            files.forEach { if (it !in res.map { it.first }) it.delete() }
        }
    }


    fun checkBehavior(project: Project, saveFoundBugs: Boolean = true): CHECKRES {
        val (groupedRes, didCrash) = checkTest(project)
        if (groupedRes.size > 1) {
            var onlyAddresses = false
            if (groupedRes.keys.first().split("\n").any { it.matches(Regex(""".+@[0-9a-z]+""")) }) {
                val comment = Factory.psiFactory.createComment("// DIFF_ONLY_IN_ADDRESSES")
                project.files.first().psiFile.addToTheTop(comment)
                onlyAddresses = true
            }
            if (saveFoundBugs) {
                val didSavedSuccessfully =
                    BugManager.saveBug(
                        Bug(
                            groupedRes.map { it.value.first() },
                            "",
                            project,
                            BugType.DIFFBEHAVIOR
                        )
                    )
                if (!didSavedSuccessfully) {
                    return CHECKRES.NOT_OK
                }
            }
            return if (onlyAddresses) {
                CHECKRES.NOT_OK
            } else {
                CHECKRES.BUG
            }
        }
        if (CompilerArgs.isStrictMode) {
            if (didCrash) {
                return CHECKRES.NOT_OK
            }
            if (groupedRes.isEmpty() || groupedRes.keys.any { it.contains("Exception", true) }) {
                return CHECKRES.NOT_OK
            }
        }
        return CHECKRES.OK
    }

    private fun checkTest(project: Project): Pair<Map<String, List<CommonCompiler>>, Boolean> {
        //log.debug("Trying to compile with main function:")
        log.debug("Trying to compile with main function:")
        //val extendedCompilerList = compilers + listOf(JVMCompiler("-Xno-optimize"))
        val extendedCompilerList = compilers
        if (!extendedCompilerList.checkCompilingForAllBackends(project)) {
            log.debug("Cannot compile with main + \n$project")
            return mapOf<String, List<CommonCompiler>>() to false
        }

        //println("Executing traced code:\n$project")
        log.debug("Executing traced code:\n$project")
        val results = mutableListOf<Pair<CommonCompiler, String>>()
        val errorsMap = mutableListOf<Pair<CommonCompiler, String>>()
        var jvmCrashed = false
        var hasErrors = false
        for (comp in extendedCompilerList) {
            val status = comp.compile(project)
            if (status.status != COMPILE_STATUS.OK)
                return mapOf<String, List<CommonCompiler>>() to false
            val res = comp.exec(status.pathToCompiled)
            val errors = comp.exec(status.pathToCompiled, Stream.ERROR)
            if (errors.isNotEmpty()) hasErrors = true
            File(status.pathToCompiled).let { if (it.exists()) it.deleteRecursively() }
            log.debug("Result of ${comp.compilerInfo}: $res\n")
            log.debug("Errors: $errors")
            if (exclErrorMessages.any { errors.contains(it) })
                return mapOf<String, List<CommonCompiler>>() to false
            results.add(comp to res.trim())
            if (errors.contains("java.lang.VerifyError: Bad type on operand stack")) {
                errorsMap.add(comp to "Bytecode by $comp is incorrect\n${errors.trim()}")
                jvmCrashed = true
            } else {
                errorsMap.add(comp to errors.trim())
            }
        }
        if (jvmCrashed) {
            return errorsMap.groupBy({ it.second }, valueTransform = { it.first }).toMutableMap() to true
        }
        if (results.all { it.second.trim().isEmpty() }) {
            return mapOf<String, List<CommonCompiler>>("Exception" to listOf()) to true
        }
//        //Compare with java
//        if (CompilerArgs.useJavaAsOracle) {
//            try {
//                val res = JCompiler().compile(pathToFile)
//                if (res.status == 0) {
//                    val execRes = JCompiler().exec(res.pathToCompiled, Stream.BOTH)
//                    log.debug("Result of JAVA: $execRes")
//                    results.add(JCompiler() to execRes.trim())
//                } else log.debug("Cant compile with Java")
//            } catch (e: Exception) {
//                log.debug("Exception with Java compilation")
//            }
//        }
        return results.groupBy({ it.second }, valueTransform = { it.first }).toMutableMap() to hasErrors
    }

    private val log = LogManager.getLogger("mutatorLogger")
}
