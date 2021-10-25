// Original bug: KT-36024

import kotlin.reflect.KCallable

fun useInvoke(fn: (Int, Int) -> Unit) {
    fn(1, 2)
}

fun useCall(fn: (Int, Int) -> Unit) {
    (fn as KCallable<*>).call(1, 2)
}

fun foo(a: Int, b: Int, c: Int = 0) {
    println("a: $a; b: $b; c: $c")
}

fun main() {
    useInvoke(::foo)    // OK: a: 1; b: 2; c: 0
    useCall(::foo)      // java.lang.IllegalArgumentException: Callable expects 3 arguments, but 2 were provided.
}
