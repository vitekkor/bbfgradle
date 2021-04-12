// Original bug: KT-15970

abstract class A {
    abstract fun foo(x: Int = 1, y: Int): Int
}

interface B {
    fun foo(x: Int, y: Int = 2): Int
}

class C : A(), B {
    override fun foo(x: Int, y: Int) = x + y
}

fun main(args: Array<String>) {
    println(C().foo())
}
