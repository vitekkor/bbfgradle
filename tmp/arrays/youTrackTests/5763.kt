// Original bug: KT-30197

@Deprecated("", replaceWith = ReplaceWith("bar(y)"))
fun foo(x: Any, y: Any, z: Any) {
}

fun bar(y: Any) {}
fun main() {
    foo(4::class, 42::dec, ::bar)
}
