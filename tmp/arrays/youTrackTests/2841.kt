// Original bug: KT-38989

sealed class A {}

sealed class B : A()
class C : A()

sealed class D : B()
sealed class E : B()

interface W {
    fun foo() {}
}

fun main(a: A, w: W) {
    return when (a) {
        is C -> w
        is D -> w
        is E -> w
    }.foo() // Unresolved reference, works in FE 1.0
}
