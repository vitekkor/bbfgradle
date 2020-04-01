package com.stepanov.bbf.bugfinder.executor

import com.intellij.psi.PsiErrorElement
import com.intellij.psi.PsiFile
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.util.getFileLanguageIfExist
import com.stepanov.bbf.bugfinder.util.saveOrRemoveToTmp
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.util.getAllChildrenNodes
import java.io.File

class ProjectMultiCompilerTestChecker(private val compiler: CommonCompiler, var otherFiles: Project?) :
    MultiCompilerCrashChecker(compiler) {

    fun isAlreadyCheckedOrWrong(files: List<PsiFile>): Pair<Boolean, Boolean> {
        val text = files.joinToString("\n") { it.text }
        val hash = text.hashCode()
        if (alreadyChecked.containsKey(hash)) {
            log.debug("ALREADY CHECKED!!!")
            return true to alreadyChecked[hash]!!
        }
        if (files.any { it.node.getAllChildrenNodes().any { it.psi is PsiErrorElement } }) {
            log.debug("Not correct syntax")
            alreadyChecked[hash] = false
            return true to false
        }
        return false to false
    }

    override fun checkTest(text: String, pathToFile: String): Boolean {
        var hasJava = false
        if (otherFiles == null) return false
        val psiFiles = (listOf(text) + otherFiles!!.texts).map {
            if (it.getFileLanguageIfExist() == LANGUAGE.KOTLIN) {
                PSICreator("").getPSIForText(it, false)
            } else {
                hasJava = true
                PSICreator("").getPsiForJava(it, Factory.file.project)
            }
        }
        //WE SHOULD'NT REMOVE COMMENT WITH FILE NAME
        if (!text.contains(Regex("""((//\s*FILE)|(//\s*File )).*(.java|.kt)\n"""))) return false

        val firstCheck = isAlreadyCheckedOrWrong(psiFiles)
        if (firstCheck.first) return firstCheck.second
        val proj =
            if (hasJava) {
                Project(psiFiles.map { it.text }, null, LANGUAGE.KJAVA)
            } else {
                Project(psiFiles.map { it.text })
            }
        val path = proj.saveOrRemoveToTmp(true)
        val res = compiler.isCompilerBug(path)
        proj.saveOrRemoveToTmp(false)
        alreadyChecked[text.hashCode()] = res
        return res
    }
}