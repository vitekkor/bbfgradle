// Original bug: KT-31369

class Thing

suspend fun something(thing: Thing) {
}

inline fun Thing?.use(block: (Thing?) -> Unit) {
    try {
        return block(this)
    } catch (e: Throwable) {
        throw e
    }
}

suspend inline fun Thing.doSomething(smth: (Thing) -> Unit): Unit = use { smth(this) }

fun <T> runClocking(block: suspend () -> T) {}

fun main() {
    runClocking {
        Thing().doSomething { something(it) }
    }
}
