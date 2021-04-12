// Original bug: KT-37471

abstract class A {
    protected abstract fun foo() // The problem occurs only with protected visibility modifier
}

class B: A() {
    override fun foo() {

    }

    fun bar() {
        val b = foo()
    }
}
