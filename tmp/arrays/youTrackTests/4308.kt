// Original bug: KT-36885

fun main() {
    val myVal =
        (if (true) {
            null
        } else if (true) {
            null
        } else {
            42
        }) ?: 0

    println(myVal) // prints 0
}
