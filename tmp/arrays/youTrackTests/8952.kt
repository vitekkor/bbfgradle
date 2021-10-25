// Original bug: KT-16107

sealed class S {
    class A : S()
    class B : S()
}

fun foo(s: S?): String = when (s) {
    is S.A -> "A"
    is S.B -> "B"
    null -> "null"
    else -> error("Unreachable")  // <-- there should be a warning
}
