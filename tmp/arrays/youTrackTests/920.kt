// Original bug: KT-43421

inline fun test(block: () -> Unit) {
    do {
        try { block() }
        finally { break }
    } while (false)
}
fun main() {
    test {
        println("Good")
        return@main
    }
    println("Bad")
}
