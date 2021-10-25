// Original bug: KT-17691

fun foo(vararg foo: String, bar: Any) {}

fun main() {
    val x: String? = null
    foo(foo = arrayOf(checkNotNull(x)), bar = { x.length }()) // x is smartcast to String, NPE
}
