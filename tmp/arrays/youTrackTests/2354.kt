// Original bug: KT-34959

interface I {
    fun foo(parent: String)
}

open class A {
    open fun foo(parent: Any) {}

}

class B : A(), I {
    override fun foo(parent: String) { // Remove redundant overriding method
        super.foo(parent)
    }
}
