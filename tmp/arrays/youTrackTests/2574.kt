// Original bug: KT-26248

fun main(args: Array<String>) {
    val first = listOf("hello")
    val second = listOf("hello", "world")
    val result = second.any(first::contains) // try to inline `first`
    println(result)
}
