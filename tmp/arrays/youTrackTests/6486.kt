// Original bug: KT-29506

open class A {
    open fun foo(a: Int, b: Int, c: Int) {}
}

class B : A() {
    override fun foo(a: Int, b: Int, c: Int) {
        super.foo(a = a, b = b, c = c)
    }
}
