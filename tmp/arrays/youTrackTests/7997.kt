// Original bug: KT-23946

open class A {
    open fun foo(x: Int = 42) {
        throw AssertionError()
    }
}

class B : A() {
    @JvmOverloads
    override fun foo(x: Int) {
        if (x != 42) throw AssertionError(x.toString())
    }
}
