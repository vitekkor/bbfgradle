// Original bug: KT-45861

@Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")
fun <@kotlin.internal.OnlyInputTypes T> foo(a: T, b: T): T = a

fun bar(x: Int, y: Number) {
    foo(x, y)
}
