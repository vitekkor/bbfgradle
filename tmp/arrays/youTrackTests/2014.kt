// Original bug: KT-36423

package test

open class A<T> {
    fun foo(x: A<T>) = "K"
}

interface C<E> {
    fun foo(x: E) = "O"
}

class B : A<A<String>>(), C<A<String>>

fun box(): String {
    val x: A<String> = A()
    val y: A<A<String>> = A()
    val b = B()
    return b.foo(x) + b.foo(y)
}

fun main() {
    println(box())
}
