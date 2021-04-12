// Original bug: KT-41305

sealed class A {
    sealed class B : A() {
        object C : B()
        object D : B()
    }

    object Z : A()
}

fun test(a: A) {
    when (a) {
        is A.B -> {
            when (a) {   // <-- incorrect warning: NON_EXHAUSTIVE_WHEN_ON_SEALED_CLASS 'when' expression on sealed classes is recommended to be exhaustive, add 'Z' branch or 'else' branch instead
                is A.B.C -> {}
                is A.B.D -> {}
            }
        }
        is A.Z -> {}
    }
}
