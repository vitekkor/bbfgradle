// Original bug: KT-9517

open class A {
    open val foo: () -> Number = null!!
}

class B: A() {
    override val foo: () -> Int
        get() = null!!
}

fun test(a: A) {
    if (a is B) {
        val foo = a.foo() // resolved to A::foo + invoke(), but must be B::foo + invoke()
    }
}
