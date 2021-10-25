// Original bug: KT-30186

open class Parent {
    val parentField: Int = 42

    operator fun invoke(code: (Parent) -> Unit): Parent = apply(code)
}

class Child : Parent() {
    val childField: Int = 42

    operator fun invoke(code: (Child) -> Unit): Child = apply(code)
}

fun usage() {
    val child = Child()

    child { it: Child ->
        it.parentField
        it.childField
    }
}
