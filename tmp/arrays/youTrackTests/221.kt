// Original bug: KT-31656

class Foo

private fun Foo.bar(vararg names: String, action: (String) -> Unit) {}
// Actual value of parameter '$this$bar' is always 'a'.
// Actual value of parameter 'names' is always 'b'.
// Actual value of parameter 'action' is always 'c'.

private fun baz(vararg names: String, action: (String) -> Unit) {}
// Actual value of parameter 'names' is always 'a'.
// Actual value of parameter 'action' is always 'b'.

fun test(node: Foo) {
    node.bar("a", "b", "c", "d") {}
    baz("a", "b", "c", "d") {}
}
