// Original bug: KT-27243

// FILE: a.kt
interface A {
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "")
    fun foo()
}

// FILE: b.kt
class B : A {
    override fun foo() {}
}
