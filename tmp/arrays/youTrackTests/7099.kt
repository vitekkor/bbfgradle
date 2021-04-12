// Original bug: KT-28421

interface A {
    fun foo() {}
}

interface B {
    fun foo() {}
}

fun some(ab: Any) {
    if (ab is A && ab is B) {
        ab.foo() // Why it's not an ambiguity?
    }
}
