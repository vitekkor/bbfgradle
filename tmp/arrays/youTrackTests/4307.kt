// Original bug: KT-36885

fun main() {
    val myVal =
        if (true) {
            null
        } else (if (true) { // note open brace
            null
        } else {
            42
        } ?: 0)

    println(myVal) // prints null
}
