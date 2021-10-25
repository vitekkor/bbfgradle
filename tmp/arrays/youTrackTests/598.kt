// Original bug: KT-42995

fun main() {
    val a: Int
    try {
        return
    } catch (e: Exception) {
        a = 2
    } finally {
        run {
            println(a) // no error, prints 0
        }
    }
}

