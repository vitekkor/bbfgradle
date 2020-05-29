package com.stepanov.bbf.bugfinder.executor.project

import com.intellij.psi.PsiErrorElement
import com.stepanov.bbf.reduktor.util.MsgCollector
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArguments
import org.jetbrains.kotlin.cli.common.arguments.K2JSCompilerArguments
import org.jetbrains.kotlin.cli.common.arguments.K2JVMCompilerArguments
import org.jetbrains.kotlin.config.LanguageFeature
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
            val language = if (files.any { it.getLanguage() == LANGUAGE.JAVA}) LANGUAGE.KJAVA else LANGUAGE.KOTLIN
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

    override fun toString(): String = files.joinToString("\n") { it.psiFile.text }
}