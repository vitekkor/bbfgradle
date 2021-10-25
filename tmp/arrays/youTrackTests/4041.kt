// Original bug: KT-23141

class Inv<T>

fun <K, V> select(a: K, other: Inv<V>) {}
fun <T> select(a: T, other: T) {}

fun main(args: Array<String>) {
    select(Inv<Int?>(), null) // (1)
}
