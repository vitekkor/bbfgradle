// Original bug: KT-29189

package test

fun test(a: String): String {
    val b = a.toLowerCase()
    return when { // *
        b.startsWith("a") -> "A"
        b.startsWith("b") -> "B"
        b.startsWith("c") -> "C"
        else -> "other"
    }
}

fun main() {
    println(test("abcd"))
}
