// Original bug: KT-17580

sealed class S {
    class A : S()
    class B : S()
}

fun foo(s: S) {
    when (s) { // There should be an "Add remaining branches" intention on this when

    }
}
