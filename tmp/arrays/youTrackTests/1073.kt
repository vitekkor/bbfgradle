// Original bug: KT-33561

// FILE: a.kt
class Foo {
    
    @JvmSynthetic
    inline fun foo(crossinline getter: () -> String) = foo(getter())

    fun foo(getter: String) = println(getter)
}
