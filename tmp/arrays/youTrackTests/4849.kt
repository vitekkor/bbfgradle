// Original bug: KT-33698

open class A {
    @JvmOverloads
    open fun foo(x: Any = 1) {}
}

class B : A() {
    fun foo() {} // ACCIDENTAL_OVERRIDE should be reported, but it's not
}
