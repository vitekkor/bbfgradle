// Original bug: KT-45279

inline fun test(block: () -> Unit) {
    try { block() }
    finally { return }
}
fun main() {
    test {
        println("Good")
        return@main
    }
    println("Bad")
}
