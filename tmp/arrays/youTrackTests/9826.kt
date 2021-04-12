// Original bug: KT-9548

interface A {
    fun foo(y: List<String>)
}

interface B {
    fun foo(y: List<String>)
}

interface C : A, B

fun <S> bar(z: S, y: List<String>) where S : A, S : B, S : C {
    z.foo(y) // OK
}
