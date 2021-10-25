// Original bug: KT-17573

fun zap(s: String) = s

inline fun tryZap(string: String, fn: (String) -> Unit) {
    fn(try { zap(string) } finally {})
}

fun main(args: Array<String>) {
    tryZap("") {}
}
