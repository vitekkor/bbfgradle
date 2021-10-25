// Original bug: KT-33012

class Recursive<T : Recursive<T>>

fun foo(a: Any) {} // (2)
fun <T : Recursive<T>> foo(e: Recursive<T>) {} // (1)

fun test(k: Recursive<*>) {
    foo(k)
}
