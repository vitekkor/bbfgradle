// Original bug: KT-41738

fun main() {
    val element: Iterable<Int> = listOf(1)
    val list: List<Iterable<Int>> = listOf(element)
    val list2: List<Iterable<Int>> = list.plus<Iterable<Int>>(element)
}
