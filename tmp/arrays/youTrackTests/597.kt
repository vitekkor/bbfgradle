// Original bug: KT-42995

fun main() {
    val a: Boolean
    try {
        run {
            a = true
            println(a) // prints true
            throw Exception("hmm")
        }
    } catch (e: Exception) {
        a = false
        println(a) // prints false
    }
}
