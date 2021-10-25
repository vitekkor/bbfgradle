// Original bug: KT-24787

fun main(args: Array<String>) {
    var header = emptyList<String>()
    val alias = header
    header += listOf("a", "b", "c")
    println(alias) // []
    println(header) // [a, b, c]
}
