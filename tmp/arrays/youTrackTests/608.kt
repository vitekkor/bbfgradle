// Original bug: KT-40696

fun main() {
    val a: String? = null
    a as String // always fails
    val b: String? = null
    b!! // always fails
    val c: Number = 1
    c as Float // always fails
    if (c > 1) println("hi") // always false
}
