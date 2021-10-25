// Original bug: KT-33012

class Recursive<T : Recursive<T>>

fun <T : Recursive<T>> foo(e: Recursive<T>) {}
fun nonGeneric(e: Recursive<*>) {}

fun <K : Recursive<K>> test(s: Recursive<*>, k: Recursive<K>) {
    nonGeneric(s) // OK
    nonGeneric(k) // OK
    foo(k) // OK
}
