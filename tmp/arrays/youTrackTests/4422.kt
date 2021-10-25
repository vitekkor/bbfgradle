// Original bug: KT-34966

data class D(val a: Int, val b: Int)

fun main() {
    val list = listOf(
        D(3, 0),
        D(1, 0),
        D(2, 0)
    )

    println(list.sortedBy { it.b }.lastOrNull()?.a) // 2
    println(list.maxBy { it.b }?.a) // 3
}
