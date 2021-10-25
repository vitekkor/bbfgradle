// Original bug: KT-40038

val a: A<() -> Unit> = A()

fun addListener(listener: () -> Unit) {
    a.foo(listener) // no completion for this case
}
class A<T> {
    fun foo(element: T) {}
}
