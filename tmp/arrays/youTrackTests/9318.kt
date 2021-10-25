// Original bug: KT-14692

class A

class D {
    operator fun A.component1() = 1.0
    operator fun A.component2() = ' '
}

fun foobar(block: D.(A) -> Unit) { }

fun bar() {
    // Error: component functions are unresolved
    foobar { (a, b) -> }
}
