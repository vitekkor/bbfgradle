// Original bug: KT-39046

fun foo(b: B<Int, Int>) {}

fun bar(b: B<String, Number>) {
    foo(b.myMap {
        it.k.length // implicits
    } as B<Int, Int>)
}

class B<out K, V>(val k: K, val v: V)

fun <X, R, V> B<X, V>.myMap(transform: (B<X, V>) -> R): B<R, V> = TODO()

