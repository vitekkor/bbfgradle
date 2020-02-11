package com.stepanov.bbf.bugfinder.executor

import org.jetbrains.kotlin.psi.KtFile

class Project(texts: List<String>?, files: List<KtFile>? = null) {

    val texts: List<String>

    init {
        this.texts = texts?.let { it } ?: files!!.map { it.text }
    }

    constructor(text: String) : this(listOf(text))

    fun getCommonText(commonPath: String) =
        texts.mapIndexed { index, s -> "//File: ${commonPath.split(" ")[index]}\n$s" }.joinToString("\n")


}