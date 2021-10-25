// Original bug: KT-9425

fun f(x: Any): Boolean =
    x is Array<*> && x.isArrayOf<MutableList<String>>()

fun main() {
    // Expected: "false"
    println(f(arrayOf(listOf(""))))
}
