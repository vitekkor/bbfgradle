// Original bug: KT-17590

fun zap(s: String): String? = s

inline fun tryZap(s: String, fn: (String) -> String): String {
    return fn(return "OK")
}

fun box() = tryZap("OK") { it }
