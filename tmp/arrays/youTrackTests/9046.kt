// Original bug: KT-10648

sealed class A
sealed class B : A()

class C : B()
class D : B()

fun test(a: A): Any {
    return when (a) {
        is C -> ""
        is D -> ""
    }
}
