package com.stepanov.bbf.bugfinder.executor.project

import kotlin.reflect.KProperty

data class Header(
//    val fileName: String,
    val languageSettings: List<String>,
    val withDirectives: List<String>,
    val ignoreBackends: List<String>,
    val targetBackend: String
) {


    companion object {
        fun createHeader(commentSection: String): Header {
            val commentSectionLines = commentSection.lines()
//            val fileName: String by HeaderDelegate(commentSectionLines, Directives.fileName)
            val language: String by HeaderDelegate(commentSectionLines, Directives.language)
            val languageFeatures = if (language.trim().isEmpty()) listOf() else language.substringAfter(Directives.language).split(" ")
            val targetBackend: String by HeaderDelegate(commentSectionLines, Directives.targetBackend)
            val withDirectives = commentSectionLines.filter { it.startsWith(Directives.withDirectives) }
            val ignoreBackends = commentSectionLines.filter { it.startsWith(Directives.ignoreBackends) }
            return Header(languageFeatures, withDirectives, ignoreBackends, targetBackend)
        }
    }

}

internal object Directives {
    const val file = "// FILE: "
    const val module = "// MODULE: "
    const val language = "// !LANGUAGE: "
    const val withDirectives = "// WITH_"
    const val ignoreBackends = "// IGNORE_BACKEND"
    const val targetBackend = "// TARGET_BACKEND"
    const val coroutinesDirective = "// WITH_COROUTINES"
}

private class HeaderDelegate(private val sec: List<String>, private val directive: String) {
    operator fun getValue(line: Any?, property: KProperty<*>): String = sec.findDirectiveAndGetValue(directive)
}

private fun List<String>.findDirectiveAndGetValue(directive: String): String =
    this.find { it.startsWith(directive) }
        ?.takeAfterFirst(directive)
        ?: ""

private fun String.takeAfterFirst(s: String): String = this.substring(this.indexOf(s).takeIf { it >= 0 } ?: 0)