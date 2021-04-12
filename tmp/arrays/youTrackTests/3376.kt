// Original bug: KT-15971

interface A {
    fun foo(x: Int): Int
    
    fun bar(x: Int): Int
}

interface B {
    fun foo(x: Int = 42): Int
}

interface C {
    fun bar(x: Int = 33): Int
}

class D : C, B, A {
    override fun foo(x: Int) = x
    override fun bar(x: Int) = x
}

fun main(args: Array<String>) {
    println(D().foo())
    println(D().bar())
}
