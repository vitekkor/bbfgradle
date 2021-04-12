// Original bug: KT-31530

package test

class ML<T> {
    val list = ArrayList<T>()

    fun add(x: T) {
        list.add(x)
    }
}

interface IFoo {
    fun foo()
}

interface IBar {
    fun bar()
}

class C<T> where T : IFoo, T : IBar {
    fun foo(a: Any, b: Any) {
        a as ML<IBar>
        b as T
        a.add(b)
    }
}

class FooImpl : IFoo {
    override fun foo() {}
}

class FooBar : IFoo, IBar {
    override fun foo() {}
    override fun bar() {}
}

fun main() {
    val c = C<FooBar>()
    val ml = ML<IBar>()
    c.foo(ml, FooImpl())
    println(ml.list)
}
