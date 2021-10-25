// Original bug: KT-13130

sealed class Base {
    sealed class A : Base() {
        object A1 : A()
        sealed class A2 : A()
    }
    sealed class B : Base() {
        sealed class B1 : B()
        object B2 : B()
    }

    fun foo() = when (this) {
        is A -> 1
        is B.B1 -> 2
        B.B2 -> 3
        // No else required
    }
}
