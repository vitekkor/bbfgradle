// Original bug: KT-8221

class A {
    fun foo(): Boolean {
        return true
    }
}

fun bar(): A? {
    return A()
}

fun baz() {
    val x = bar()
    if (x?.foo() != null) {
        x.foo() // <- error: x may be null ?!
    }
}
