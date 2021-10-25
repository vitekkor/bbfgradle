// Original bug: KT-31622

enum class E {
    A, B;
}

fun foo(e: E) {
    if (e == E.A) {
        when (e) { // Warning: [NON_EXHAUSTIVE_WHEN] 'when' expression on enum is recommended to be exhaustive, add 'B' branch or 'else' branch instead
            E.A -> {}
        }
    }
}
