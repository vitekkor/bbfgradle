// Original bug: KT-25175

class Inv<T>(val x: T)

fun <K> foo(x: K, i: Inv<K>) {}
fun <V> foo(x: Inv<V>, i: Inv<V>) {}

fun test(inv: Inv<Int>) {
    foo(inv, Inv<Int>(42)) // Here type argument should be specified explicitly
}
