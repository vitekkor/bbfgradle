// Original bug: KT-15646

fun main(args: Array<String>) {
    runBlock {
        throw Exception() // set breakpoint here and hit Step Over
    }
}

inline fun runBlock(block: () -> Unit) {
    try {
        block()
    } catch (e: Throwable) {
    }
    throw Exception()
}
