// Original bug: KT-42028

fun <T> builder0(f: suspend () -> T): T {
    TODO()
}

fun <T> builder(f: suspend Any.() -> T): T {
    return builder0 {
        f(object {
            suspend fun foo(g: suspend String.() -> Unit) {
                g("OK")
                "Force non-tail call".length
            }
        })
    }
}
