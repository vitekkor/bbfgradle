// Original bug: KT-26873

data class PartialPackageInfo(val dependencies: Map<String, String>?) {
    companion object {
        private val VERSION_MODIFIERS = setOf(
            "<=", ">="
        ) + "~<>^".asIterable().map { String(charArrayOf(it)) } // java.lang.Throwable: ArgumentUnmapped for LAMBDA_ARGUMENT in successfully resolved call: String(charArrayOf(it))
    }
    fun getDependenciesWithDirectVersions(): Map<String, String> {
        return dependencies?.mapValues { (_, version) ->
            val startOfVersion = (VERSION_MODIFIERS.firstOrNull(version::startsWith) ?: "").length
            version.substring(startOfVersion)
        } ?: mapOf()
    }
}
