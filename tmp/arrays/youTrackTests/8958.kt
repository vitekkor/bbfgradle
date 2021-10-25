// Original bug: KT-17572

fun zap(s: String) = s

inline fun tryZap(string: String, fn: (String) -> Unit) {
    fn(try { zap(string) } catch (e: Exception) { "" })
}

fun main(args: Array<String>) {
    tryZap("") {}
}
