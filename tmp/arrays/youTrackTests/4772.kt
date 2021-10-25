// Original bug: KT-31653

fun main(args: Array<String>) {
    try {
        try {
            return
        } catch (fail: Throwable) {}
    } finally {
        println(1)
        throw RuntimeException()
    }
}
