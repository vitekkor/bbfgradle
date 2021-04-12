// Original bug: KT-17839

sealed class A {
    sealed class B : A() {
        class C : B()
        class D : B()
    }

    class E : A()
}

fun x(a: A) = when (a) {
    is A.B -> 1
    is A.B.D -> 2 // this case can be never hit
    is A.E -> 3
}
