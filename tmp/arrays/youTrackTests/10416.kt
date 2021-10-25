// Original bug: KT-5863

inline fun <R> performWithFinally(finally: () -> R) : R {
    try {
        throw RuntimeException("1")
    } catch (e: RuntimeException) {
        throw RuntimeException("2")
    } finally {
        return finally()
    }
}

inline fun test2Inline() = performWithFinally { "OK" }

fun main(args: Array<String>) {
    println(test2Inline())
}
