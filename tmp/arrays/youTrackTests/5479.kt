// Original bug: KT-32524

abstract class Parent {
    abstract fun fooImpl()
    inner class ParentInner {
        fun foo() = fooImpl()
    }
}

val staticChildInstance = Child()

class Child : Parent() {
    val inner = staticChildInstance.ParentInner()
    override fun fooImpl() {}
}

fun main() {
    println(staticChildInstance.inner) // prints Parent$ParentInner@5d624da6
    println(staticChildInstance.inner.foo()) // NPE
}
