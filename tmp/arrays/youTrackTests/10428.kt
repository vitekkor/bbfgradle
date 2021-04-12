// Original bug: KT-268

class X : Cloneable
class Foo() {
    fun bar(c : Cloneable) {}
}

fun Foo.bar(x : X) {}

fun test() {
    Foo().bar(X()) // Correctly resolves to Foo.bar, not the extension method
}
