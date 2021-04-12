// Original bug: KT-10939

interface Base {
    fun <T> foo(a: T)
    fun <T> foo(a: T, vararg args: Any)
}

interface Derived : Base

fun testBase(x: Base) {
    x.foo("") // OK
}

fun testDerived(x: Derived) {
    x.foo("") // CANNOT_COMPLETE_RESOLVE
}
