// Original bug: KT-36816

inline fun <R> test(transform: () -> R) {}

class Inv<T>(x: T?) {}

fun <K> foo(x: K) {
    test { Inv(x) } // [TYPE_MISMATCH] Type mismatch. Required: () â Inv<K>; Found: () â Inv<K!!>
}
