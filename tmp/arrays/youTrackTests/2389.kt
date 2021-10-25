// Original bug: KT-42004

class Log {
    fun error(message: Any?) {}
}

private val log = Log()

class C {
    fun method() {}
}

fun <T : Any> df(r: suspend (T) -> Unit) {
}

fun foo(c: C?) {
    df<String> {
        c?.method() ?: log.error("OK")
    }
}
