package com.stepanov.bbf.bugfinder.executor.project

import com.intellij.psi.PsiErrorElement
import com.intellij.psi.PsiFile
import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.executor.addMain
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.util.contains
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.bugfinder.util.getAllWithoutLast
import com.stepanov.bbf.reduktor.util.MsgCollector
import com.stepanov.bbf.reduktor.util.getAllWithout
import org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArguments
import org.jetbrains.kotlin.cli.common.arguments.K2JSCompilerArguments
import org.jetbrains.kotlin.cli.common.arguments.K2JVMCompilerArguments
import org.jetbrains.kotlin.cli.js.K2JSCompiler
import org.jetbrains.kotlin.cli.jvm.K2JVMCompiler
import org.jetbrains.kotlin.config.LanguageFeature
import org.jetbrains.kotlin.psi.KtNamedFunction
import java.io.File
import com.stepanov.bbf.bugfinder.util.flatMap

class Project(
    var configuration: Header,
    val files: List<BBFFile>,
    val language: LANGUAGE = LANGUAGE.KOTLIN
) {

    constructor(configuration: Header, file: BBFFile, language: LANGUAGE) : this(configuration, listOf(file), language)

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
                File(it.name.substringBeforeLast("/")).mkdirs()
                File(it.name).writeText(it.psiFile.text)
            } else {
                val createdDirectories = it.name.substringAfter(CompilerArgs.pathToTmpDir).substringBeforeLast('/')
                if (createdDirectories.trim().isNotEmpty()) {
                    File("${CompilerArgs.pathToTmpDir}$createdDirectories").deleteRecursively()
                } else {
                    File(it.name).delete()
                }
            }
        }
        return files.joinToString(" ") { it.name }
    }

    fun moveAllCodeInOneFile() =
        StringBuilder().apply {
            append(configuration.toString());
            if (configuration.isWithCoroutines())
                files.getAllWithoutLast().forEach { appendLine(it.toString()) }
            else files.forEach { appendLine(it.toString()) }
        }.toString()

    fun saveInOneFile(pathToSave: String) {
        val text = moveAllCodeInOneFile()
        File(pathToSave).writeText(text)
    }


    fun isBackendIgnores(backend: String): Boolean = configuration.ignoreBackends.contains(backend)

    fun getProjectSettingsAsCompilerArgs(backendType: String): CommonCompilerArguments {
        val args = when (backendType) {
            "JVM" -> K2JVMCompilerArguments()
            else -> K2JSCompilerArguments()
        }
        val languageDirective = "-XXLanguage:"
        val languageFeaturesAsArgs = configuration.languageSettings.joinToString(
            separator = " $languageDirective",
            prefix = languageDirective,
        ).split(" ")
        when (backendType) {
            "JVM" -> args.apply {
                K2JVMCompiler().parseArguments(
                    languageFeaturesAsArgs.toTypedArray(),
                    this as K2JVMCompilerArguments
                )
            }
            "JS" -> args.apply {
                K2JSCompiler().parseArguments(
                    languageFeaturesAsArgs.toTypedArray(),
                    this as K2JSCompilerArguments
                )
            }
        }
        args.optIn = configuration.useExperimental.toTypedArray()
        return args
    }

    fun addMain(): Project {
        if (files.map { it.text }.any { it.contains("fun main(") }) return Project(configuration, files, language)
        val boxFuncs =
            files.flatMap { it.psiFile.getAllPSIChildrenOfType<KtNamedFunction> { it.name?.contains("box") == true } }
        if (boxFuncs.isEmpty()) return Project(configuration, files, language)
        val indOfFile =
            files.indexOfFirst {
                it.psiFile.getAllPSIChildrenOfType<KtNamedFunction>().any { it.name?.contains("box") == true }
            }
        if (indOfFile == -1) return Project(configuration, files, language)
        val file = files[indOfFile]
        val psiCopy = file.psiFile.copy() as PsiFile
        psiCopy.addMain(boxFuncs)
        val newFirstFile = BBFFile(file.name, psiCopy)
        val newFiles =
            listOf(newFirstFile) + files.getAllWithout(indOfFile).map { BBFFile(it.name, it.psiFile.copy() as PsiFile) }
        return Project(configuration, newFiles, language)
    }

    fun copy(): Project {
        return Project(configuration, files.map { it.copy() }, language)
    }


    override fun toString(): String = files.joinToString("\n\n") {
        it.name + "\n" +
        it.psiFile.text
    }
}