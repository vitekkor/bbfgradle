// Original bug: KT-38799

fun main() {
    fun foo(m: Map<String, (Array<Int>) -> Unit>) {}
    fun mySort(a: Array<Int>) {}

    foo(m = mapOf(
        "mySort" to ::mySort,
        //                          here -- vvvvvvvvvvvvvvvvvvvvvvv
        "mergeSort" to { a: Array<Int> -> } as (Array<Int>) -> Unit
    ))
}
