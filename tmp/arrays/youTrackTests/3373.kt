// Original bug: KT-15971

interface A {
    fun foo(x: Int): Int
}

abstract class B {
    abstract fun foo(x: Int = 42): Int
}

class C : A, B() {
    override fun foo(x: Int) = x
}

fun main(args: Array<String>) {
    println(C().foo())
}
