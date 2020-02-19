package com.stepanov.bbf.bugfinder.executor

import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtPsiFactory

//Just string representation of ktFiles
class Project(texts: List<String>?, files: List<KtFile>? = null) {

    val texts: List<String>

    init {
        this.texts = texts?.let { it } ?: files!!.map { it.text }
    }

    constructor(text: String) : this(listOf(text))

    fun getCommonText(commonPath: String) =
        texts.mapIndexed { index, s -> "//File: ${commonPath.split(" ")[index]}\n$s" }.joinToString("\n")

    fun getCommonTextWithDefaultPath() =
        texts
            .mapIndexed { index, s -> "//File: ${CompilerArgs.pathToTmpFile.substringBefore(".kt")}$index.kt\n$s" }
            .joinToString("\n")

    fun getKtFiles(psiFactory: KtPsiFactory): List<KtFile> = texts.map { psiFactory.createFile(it) }


}