// Original bug: KT-34690

class A {
    val a = 5
    class B {}
}

fun bar(x: () -> Int) {}

fun foo(x: () -> A.B) {}

fun main() {
    bar { A().a } // "convert lambda to reference" suggest to convert this to `bar(A()::a)`
    foo { A.B() } // no intention "convert lambda to reference"
    foo(A::B) // the same as above
}
