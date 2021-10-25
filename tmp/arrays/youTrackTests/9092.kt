// Original bug: KT-16532

fun single(onSubscribe: () -> Unit) {}

fun success() {}
fun error() {}

inline fun singleFunction(crossinline func: () -> Unit) {
    single {
        try {
            func()
            success()
        } catch (e: Exception) {
            error()
        }
    }
}

val lock = Any()

fun cache() {
    return singleFunction {
        synchronized(lock) {
           ""
        }
    }
}

