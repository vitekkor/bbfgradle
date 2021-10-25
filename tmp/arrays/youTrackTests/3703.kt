// Original bug: KT-30186

open class Parent {
    fun foo(x: (Any) -> Unit): Parent = Parent()
}

class Child : Parent() {
    fun foo(x: (String) -> Unit): Child = Child()
}

fun usage() {
    val child = Child()
    val x = child.foo({  }) // No error, resolved to `Parent.foo`
}
