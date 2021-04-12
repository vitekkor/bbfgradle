// Original bug: KT-26873

fun main(args: Array<String>) {
    val s = "aaa"
    listOf("a", "b").firstOrNull(s::startsWith) // Error: None of the following functions can be called with the arguments supplied
}
