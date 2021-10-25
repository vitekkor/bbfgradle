// Original bug: KT-26530

fun main(args: Array<String>) {
    println(calc()) // Expected 1, actual 0
}

fun calc(): Int {
    return with(true) {
        1 // The expression is unused
        + 0
    }
}
