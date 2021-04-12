// Original bug: KT-34466

enum class E {
    A, B;
}

fun bar(): E = E.A

fun foo(e: E): String {
    val c = when (e) {
        E.B -> "B"
        bar() -> "bar"
        else -> "else"
    }
    return c
}
