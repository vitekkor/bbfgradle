// Original bug: KT-37087

// FILE: 1.kt
// SKIP_INLINE_CHECK_IN: inlineFun$default
package test

class A {
    var f: String
        get() = "OK"
        set(value) {}
}

inline fun inlineFun(lambda: () -> String = A()::f): String {
    return lambda()
}
