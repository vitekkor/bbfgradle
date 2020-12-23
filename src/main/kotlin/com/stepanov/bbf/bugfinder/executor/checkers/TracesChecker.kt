package com.stepanov.bbf.bugfinder.executor.checkers

import com.stepanov.bbf.bugfinder.executor.CommonCompiler
import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.executor.compilers.JVMCompiler
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.manager.Bug
import com.stepanov.bbf.bugfinder.manager.BugManager
import com.stepanov.bbf.bugfinder.manager.BugType
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.util.Stream
import com.stepanov.bbf.bugfinder.util.addToTheTop
import com.stepanov.bbf.bugfinder.util.checkCompilingForAllBackends
import org.apache.log4j.Logger
import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch

// Transformation is here only for PSIFactory
class TracesChecker(compilers: List<CommonCompiler>) : CompilationChecker(compilers) {

    private companion object FalsePositivesTemplates {
        //Regex and replacing
        val exclErrorMessages = listOf(
            "IndexOutOfBoundsException",
            "ArithmeticException",
            "KotlinReflectionInternalError" //TODO!!
        )
    }

    fun checkBehavior(project: Project): Boolean {
        val groupedRes = checkTest(project)
        if (groupedRes.size > 1) {
            if (groupedRes.keys.first().split("\n").any { it.matches(Regex(""".+@[0-9a-z]""")) }) {
                val comment = Factory.psiFactory.createComment("// DIFF_ONLY_IN_ADDRESSES")
                project.files.first().psiFile.addToTheTop(comment)
            }
            BugManager.saveBug(
                Bug(
                    groupedRes.map { it.value.first() },
                    "",
                    project,
                    BugType.DIFFBEHAVIOR
                )
            )
            return false
        }
        if (CompilerArgs.isStrictMode && CompilerArgs.isMiscompilationMode) {
            if (groupedRes.keys.firstOrNull() == "-1Exception") {
                return false
            }
        }
        return true
    }

    private fun checkTest(project: Project): Map<String, List<CommonCompiler>> {
        log.debug("Trying to compile with main function:")
        val extendedCompilerList = compilers + listOf(JVMCompiler("-Xno-optimize"))
        if (!extendedCompilerList.checkCompilingForAllBackends(project)) {
            log.debug("Cannot compile with main + \n$project")
            return mapOf()
        }

        log.debug("Executing traced code:\n$project")
        val results = mutableListOf<Pair<CommonCompiler, String>>()
        val errorsMap = mutableListOf<Pair<CommonCompiler, String>>()
        for (comp in extendedCompilerList) {
            val status = comp.compile(project)
            if (status.status == -1)
                return mapOf()
            val res = comp.exec(status.pathToCompiled)
            val errors = comp.exec(status.pathToCompiled, Stream.ERROR)
            log.debug("Result of ${comp.compilerInfo}: $res\n")
            log.debug("Errors: $errors")
            if (exclErrorMessages.any { errors.contains(it) })
                return mapOf()
            results.add(comp to res.trim())
            errorsMap.add(comp to errors.trim())
        }
        if (results.all { it.second.trim().isEmpty() }) {
            return mapOf("-1Exception" to listOf())
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
        return results.groupBy({ it.second }, valueTransform = { it.first }).toMutableMap()
    }

    private val log = Logger.getLogger("bugFinderLogger")
}
