// Original bug: KT-41227

open class A {
    fun foo() {}
    open fun boo() {}
}

class B : A() {
    override fun boo() {}
    fun bar() {}
}

fun main() {
    val b = B()
    b.boo()
    b.foo()
    b.bar()
}
