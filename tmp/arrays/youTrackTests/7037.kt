// Original bug: KT-16496

inline fun f(
    wait: Int = 0,
    action: (Int) -> Unit
): Boolean {
    var millis: Long = 1
    try {
    } catch (e: Throwable) {
        millis = millis
    }
    return false
}

fun main(args: Array<String>) {
    var x = 0
    f {
        x++
    }
}
