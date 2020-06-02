package com.stepanov.bbf.bugfinder.executor.project

import com.intellij.psi.PsiErrorElement
import com.intellij.psi.PsiFile
import com.stepanov.bbf.bugfinder.executor.addMain
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.util.MsgCollector
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.util.getAllWithout
import org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArguments
import org.jetbrains.kotlin.cli.common.arguments.K2JSCompilerArguments
import org.jetbrains.kotlin.cli.common.arguments.K2JVMCompilerArguments
import org.jetbrains.kotlin.config.LanguageFeature
import org.jetbrains.kotlin.psi.KtNamedFunction
import java.io.File

class Project(
    private val configuration: Header,
    val files: List<BBFFile>,
    val language: LANGUAGE = LANGUAGE.KOTLIN
) {

    companion object {
        fun createFromCode(code: String): Project {
            val configuration = Header.createHeader(getCommentSection(code))
            val files = BBFFileFactory(code, configuration).createBBFFiles() ?: return Project(configuration, listOf())
            val language = if (files.any { it.getLanguage() == LANGUAGE.JAVA }) LANGUAGE.KJAVA else LANGUAGE.KOTLIN
            return Project(configuration, files, language)
        }
    }

    fun saveOrRemoveToTmp(flag: Boolean): String {
        files.forEach {
            if (flag) {
                File(it.name).writeText(it.psiFile.text)
            } else File(it.name).delete()
        }
        return files.joinToString(" ") { it.name }
    }

    fun isBackendIgnores(backend: String): Boolean = configuration.ignoreBackends.contains(backend)

    fun getProjectSettingsAsCompilerArgs(backendType: String): CommonCompilerArguments {
        val args = if (backendType == "JVM") K2JVMCompilerArguments() else K2JSCompilerArguments()
        val languageSettings = configuration.languageSettings
        for (feature in languageSettings) {
            val isEnable = feature[0] == '+'
            val featureName = feature.substring(1)
            val langFeature = LanguageFeature.fromString(featureName) ?: continue
            args.configureLanguageFeatures(MsgCollector)[langFeature] =
                if (isEnable) LanguageFeature.State.ENABLED else
                    LanguageFeature.State.DISABLED
        }
        return args
    }

    fun addMain(): Project {
        if (files.map { it.text }.any { it.contains("fun main(") }) return Project(configuration, files, language)
        val file = files.first()
        val boxFuncs =
            files.flatMap { it.psiFile.getAllPSIChildrenOfType<KtNamedFunction> { it.name?.contains("box") == true } }
        val psiCopy = file.psiFile.copy() as PsiFile
        psiCopy.addMain(boxFuncs)
        val newFirstFile = BBFFile(file.name, psiCopy)
        val newFiles = listOf(newFirstFile) + files.getAllWithout(0).map { BBFFile(it.name, it.psiFile.copy() as PsiFile) }
        return Project(configuration, newFiles, language)
    }


    override fun toString(): String = files.joinToString("\n") { it.psiFile.text }
}