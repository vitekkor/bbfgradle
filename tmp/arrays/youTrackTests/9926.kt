// Original bug: KT-6961

interface  Stream<T>
interface  Sequence<T> : Stream<T>

public fun <T : Comparable<T>> Sequence<T>.min(): T? = null
public fun <T : Comparable<T>> Stream<T>.min(): T? = null

public fun <T> Sequence<T>.fn(): T? = null
public fun <T> Stream<T>.fn(): T? = null

fun test(seq : Sequence<String>) {
    seq.min() // bogus error: ambiguity
    seq.min<String>() // ok, no need to infer types
    seq.fn() // ok, no constraints
}
