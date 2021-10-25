// Original bug: KT-38680

package test

inline class IC(val s: String)

interface IFoo<T> {
    fun foo(x: T, s: String = "K"): String
}

class FooImpl : IFoo<IC> {
    override fun foo(x: IC, s: String): String = x.s + s
}

fun main() {
    println(FooImpl().foo(IC("O")))
}
