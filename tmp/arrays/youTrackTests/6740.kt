// Original bug: KT-16285

class A<T>{}

fun <T> foo(a:T) = A<A<A<A<T>>>>() // more nested A in one function

fun bar() {
    val v0 = 1
    val v1 = foo(v0)
    val v2 = foo(v1)
    val v3 = foo(v2)
    val v4 = foo(v3)
    val v5 = foo(v4)
    val v6 = foo(v5) // every next line compiles several times longer
}
