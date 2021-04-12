// Original bug: KT-38211

typealias V = List<Int>

fun main() {
    val ms: List<V> = vs()
    val m: V = v()
    val sum = ms + m // inferred overloading is Collection<T>.plus(elements: Iterable<T>)
    println(sum)
}

fun vs(): List<V> = listOf(
    listOf(1, 2),
    listOf(3, 4)
)

fun v(): V = listOf(5, 6)
