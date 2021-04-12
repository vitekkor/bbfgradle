// Original bug: KT-25220

private fun generated_for_debugger_fun(): kotlin.Pair<kotlin.String, kotlin.String> {
    val a = "a"
    val b = when (a) {
        "a" -> "A"
        "b" -> "B"
        else -> throw RuntimeException()
    }
    return kotlin.Pair(b, b)
}
