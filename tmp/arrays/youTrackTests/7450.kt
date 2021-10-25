// Original bug: KT-26650

class A<T>(private val list: List<T>) : Iterable<T> {
    override fun iterator(): Iterator<T> = list.iterator()
}

fun <T, T1, T2> A<T>.apply(foo1: (T) -> T1, foo2: (T1) -> T2) = map(foo1).map(foo2).toList()
