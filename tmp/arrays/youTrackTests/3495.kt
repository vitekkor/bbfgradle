// Original bug: KT-38691

class Inv<T>
fun <T> materializeInv() = null as Inv<T>
fun <R> foo(x: Inv<R>, y: R) = materializeInv<R>()
fun <R> foo(x: Inv<R>, y: () -> R) = materializeInv<R>()

fun <R> main(fn: () -> R) {
    fun bar(): R = null as R
    val x1 = foo<R>(materializeInv()) { fn() } // OVERLOAD_RESOLUTION_AMBIGUITY only in NI
    val x2 = foo<R>(materializeInv(), fn) // OK
    val x3 = foo<R>(materializeInv(), ::bar) // OK
}
