// Original bug: KT-31923

fun main() {
    try {
        try {
            return
        } catch (fail: Throwable) {
            println("catch")
        }
    } finally {
        println("finally")
        throw RuntimeException()
    }
}
