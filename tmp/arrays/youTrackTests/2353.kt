// Original bug: KT-41913

fun <T : Any> Iterable<T>.foo() = null as T
fun <T : Any> Iterable<T?>.bar() = null as T

fun main(x: ArrayList<out Number?>) {
    x.foo() // Any in NI, error in OI
    x.bar() // Any in NI, Number in OI
}
