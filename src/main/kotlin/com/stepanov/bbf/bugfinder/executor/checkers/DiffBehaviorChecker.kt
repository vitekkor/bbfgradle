package com.stepanov.bbf.bugfinder.executor.checkers

import com.stepanov.bbf.bugfinder.executor.CommonCompiler
import com.stepanov.bbf.bugfinder.executor.project.BBFFile
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.util.checkCompilingForAllBackends
import com.stepanov.bbf.reduktor.executor.error.Error
import com.stepanov.bbf.reduktor.parser.PSICreator
import org.apache.log4j.Logger
import org.jetbrains.kotlin.psi.KtPsiFactory
import java.io.File

class DiffBehaviorChecker(
    override val project: Project,
    override val curFile: BBFFile,
    private val compilers: List<CommonCompiler>
) : MultiCompilerCrashChecker(project, curFile, null) {

    private fun compileAndGetExecResult(): List<Pair<CommonCompiler, String>> = TODO()
//    {
//        //Add main fun if need
//        val text = File(pathToFile).readText()
//        if (!text.contains("fun main(")) {
//            File(pathToFile).writeText("$text\nfun main(args: Array<String>) { println(box()) }")
//        }
//        val results = mutableListOf<Pair<CommonCompiler, String>>()
//        for (comp in compilers) {
//            val status = comp.compile(pathToFile)
//            if (status.status == -1)
//                return listOf()
//            val res = comp.exec(status.pathToCompiled)
//            log.debug("Result of ${comp.compilerInfo}: $res\n")
//            results.add(comp to res.trim())
//        }
//        return results
//    }

    private fun isSameDiffBehavior(text: String): Boolean {
        val psiFile = Factory.psiFactory.createFile(text)
        if (!compilers.checkCompilingForAllBackends(psiFile)) {
            log.debug("Cannot compile with main")
            return false
        }
        log.debug("Executing traced code:\n$text")
        val results = compileAndGetExecResult()
        if (results.isEmpty()) return false
        val backup = prevResults.map { it }
        for ((ind, res) in results.withIndex()) {
            val prevRes = prevResults[ind]
            val curRes = res.second.split("\n").filter { it.isNotEmpty() }
            log.debug("prevRes for ${res.first.compilerInfo}: $prevRes\ncurRes: $curRes\n\n")
            if (curRes.any { !prevRes.contains(it) }) {
                for ((j, b) in backup.withIndex()) {
                    prevResults[j] = b
                }
                return false
            }
            prevResults[ind] = curRes
        }
        val set = results.map {
            it.second
                .split("\n")
                .map { it.trim() }
                .filter { it.isNotEmpty() }
                .joinToString("\n")
        }.toSet()
        val res = results.size == set.size
        log.debug("Result = ${res}")
        if (!res) {
            for ((ind, b) in backup.withIndex()) {
                prevResults[ind] = b
            }
        }
        return res
    }

//    override fun checkTest(): Boolean {
//        val preCheck = isAlreadyCheckedOrWrong(text)
//        if (preCheck.first) return preCheck.second
//        val oldText = File(pathToFile).bufferedReader().readText()
//        var writer = File(pathToFile).bufferedWriter()
//        writer.write(text)
//        writer.close()
//        val res = isSameDiffBehavior(text)
//        writer = File(pathToFile).bufferedWriter()
//        writer.write(oldText)
//        writer.close()
//        alreadyChecked[text.hashCode()] = res
//        return res
//    }

//    override fun init(compilingPath: String, psiFactory: KtPsiFactory?): Error {
//        val results = compileAndGetExecResult()
//        results.forEachIndexed { _, pair -> prevResults.add(pair.second.split("\n").filter { it.isNotEmpty() }) }
//        return Error("")
//    }


    val prevResults: MutableList<List<String>> = ArrayList()

    override val log = Logger.getLogger("bugFinderLogger")
}