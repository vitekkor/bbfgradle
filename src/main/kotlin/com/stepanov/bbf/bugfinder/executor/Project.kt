package com.stepanov.bbf.bugfinder.executor

import org.jetbrains.kotlin.psi.KtFile

class Project(texts: List<String>?, files: List<KtFile>? = null) {

    val texts: List<String>
    init {
        this.texts = texts?.let { it } ?: files!!.map { it.text }
    }


}