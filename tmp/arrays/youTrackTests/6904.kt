// Original bug: KT-29084

fun main() {
    val collection = listOf(0, 1, 2, 3, 4)
    collection.fold(0, ::subtract).also(::println)
    collection.foldRight(0, ::subtract).also(::println)
}

fun subtract(j: Int, i: Int): Int {
    return j - i
}
