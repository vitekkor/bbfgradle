// Original bug: KT-21416

open class A {
    @Deprecated("")
    open fun foo() {}
}

open class B : A() {
    override fun foo() {}
}

class C : B() {
    override fun foo() {
        super.foo()  // <-- warning: 'foo(): Unit' is deprecated. Overrides deprecated member in 'A'.
    }
}
