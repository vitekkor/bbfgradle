// Original bug: KT-25447

// src/foo/bar/main.kt
package foo.bar

class A

open class B {
    open fun f(a: A, x: Int, y: Int): Unit {}
}

class C : B() {
    // type override here and choose to override `B.f` 
}
