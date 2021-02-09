package com.stepanov.bbf.coverage

data class CoverageEntry(
    val pathToFun: String,
    val methodName: String,
    val parameters: List<String>,
    val returnType: String
) {
    companion object {
        fun parseFromKtCoverage(entry: String): CoverageEntry {
            val path =
                if (!entry.substringBefore(':').contains('$')) listOf(entry.substringBefore(':'))
                else entry.substringBefore(':')
                    .split("$")
                    .filterNot { it.matches(Regex("""\d+""")) }
            val klassName = path.lastOrNull()?.substringAfterLast('/') ?: "NoName"
            val methodName =
                entry.substringAfter(':').substringBefore('(').let {
                    val name = it.split("$").filterNot { it.matches(Regex("""\d+""")) }.lastOrNull() ?: "noName"
                    when (name) {
                        "<init>", "<clinit>" -> "$klassName()"
                        else -> name
                    }
                }
            val params = mutableListOf<String>()
            entry
                .substringAfter('(').substringBefore(')')
                .split(';')
                .forEach { param ->
                    val prefix = param.takeWhile { it.isUpperCase() }
                    prefix
                        .map { jvmTypeToName["$it"] ?: "" }
                        .filter { it.trim().isNotEmpty() }
                        .forEach { params.add(it) }

                    if (prefix != param) {
                        val name = param.substringAfterLast('/')
                        if (name.trim().isNotEmpty()) params.add(name.trim())
                    }
                }
            val rtv = entry.substringAfterLast(')').substringAfterLast('/').substringBeforeLast(';').let {
                jvmTypeToName[it] ?: it
            }
            return CoverageEntry(path.joinToString("\$"), methodName, params, rtv)
        }

        private val jvmTypeToName = mapOf(
            "V" to "void",
            "B" to "byte",
            "C" to "char",
            "D" to "double",
            "F" to "float",
            "I" to "int",
            "J" to "long",
            "S" to "short",
            "Z" to "boolean"
        )
    }

    override fun toString(): String {
        val strParams =
            if (parameters.isEmpty()) ""
            else parameters.joinToString(";", postfix = ";")
        return "$pathToFun:$methodName($strParams)$returnType;"
    }

    override fun equals(other: Any?) =
        if (other !is CoverageEntry) false
        else this.isSameEntries(other)


    fun isSameEntries(other: CoverageEntry): Boolean {
        if (!pathToFun.equals(other.pathToFun, true)) return false
        if (!methodName.equals(other.methodName, true)) return false
        return compareParamsAndRtv(parameters, other.parameters, returnType, other.returnType)
    }

    private fun compareParamsAndRtv(params1: List<String>, params2: List<String>, rtv1: String, rtv2: String): Boolean {
        if (params1 == params2) return true
        if (params1.size != params2.size) return false
        params1.forEachIndexed { index, s ->
            if (!compareParam(s, params2[index])) return false
        }
        return compareParam(rtv1, rtv2)
    }

    private fun compareParam(param1: String, param2: String) =
        when {
            param1.equals(param2, ignoreCase = true) -> true
            param1.substringAfterLast('$').equals(param2.substringAfterLast('$'), true) -> true
            param1.substringAfter("Mutable").equals(param2.substringAfter("Mutable"), true) -> true
            param1 == "Any" || param2 == "Any" -> true
            else -> param1 == "Object" || param2 == "Object"
        }
}