// Original bug: KT-33700

open class A {
    @JvmOverloads
    open fun foo(x: Any = 1) {}
}

class B : A() {
    @JvmOverloads // should not be possible
    override fun foo(x: Any) {}
}
