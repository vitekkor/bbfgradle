// Original bug: KT-33699

open class A {
    open fun foo(x: Any = 1) {}
}

class B : A() {
    @JvmOverloads // warning OVERLOADS_WITHOUT_DEFAULT_ARGUMENTS
    override fun foo(x: Any) {}
}
