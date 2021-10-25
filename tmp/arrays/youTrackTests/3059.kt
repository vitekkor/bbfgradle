// Original bug: KT-30725

fun <T : Comparable<T>> foo(list: List<T>) {
    list.sorted().first() // can be replaced with .min()
    list.sorted().last() // can be replaced with .max()
    list.sortedDescending().first() // can be replaced with .max()
    list.sortedDescending().last() // can be replaced with .min()
}
