// Original bug: KT-8584

inline fun <T> T.upcast() = this

fun main(args: Array<String>) {
    val x1 = setOf(1, 2) + setOf(1, 3) // => setOf(1, 2, 3)
    val x2 = setOf(1, 2) as Collection<Int> + setOf(1, 3) // => collection containing 1, 1, 2, 3
    val x3 = setOf(1, 2).upcast<Collection<Int>>() + setOf(1, 3) // => collection containing 1, 1, 2, 3
}
