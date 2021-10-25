// Original bug: KT-33888

fun some() {
    fun indented() {
        // copy indented function
    }
}

val ti = """
    fun indented() {
            // copy indented function
        }
""".trimIndent()
