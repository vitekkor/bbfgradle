// Original bug: KT-38029

open class Base
class Derived(val s: String) : Base()

interface Foo<T> {
    fun foo(): T
}

interface Foo2 : Foo<Derived> {
    override fun foo(): Derived = Derived("123")
}

abstract class A1<T> : Foo<T>

open class A2 : A1<Derived>(), Foo2

open class A3 : A2() {
    fun test(): Derived = super.foo()
}

class A4 : A3() {
    override fun foo(): Derived = error("don't call me")
}

fun main() {
    A4().test()
}
