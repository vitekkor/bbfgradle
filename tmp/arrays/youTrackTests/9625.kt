// Original bug: KT-11499

object CrashMe {
    fun crash(x: Any?) = null
}

inline fun crashMe(value: Any?): Any? {
    return CrashMe.crash(value ?: return null)
}

fun test() {
    crashMe(null)
}
