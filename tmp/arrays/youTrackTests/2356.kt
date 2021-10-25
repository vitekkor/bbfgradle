// Original bug: KT-31523

@Deprecated(message = "TODO", replaceWith = ReplaceWith("bar(i, f)"))
fun foo(i: Int, f: () -> Unit) {}
fun bar(i: Int, f: () -> Unit) {}

fun main() {
    foo(42) { } // OK
    foo(i = 42) { } // not OK
}
