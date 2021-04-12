// Original bug: KT-34723

interface I {
    fun foo(s: String)
}

open class A {
    open fun foo(s: Any) {} // Note `Any`, changing it to `String` avoids the problem
}

class B : A(), I {
    override fun foo(s: String) { // No "overrides" gutter
        super.foo(s) // No "Go to Super Method A.foo"
    }
}
