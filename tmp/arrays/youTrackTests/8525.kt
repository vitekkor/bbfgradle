// Original bug: KT-19772

open class A(c: Char) {
    open val c: Char = c
    fun foo(): Char = 'a'
    open fun bar(): Char = 'a'
}

interface B {
    val c: Any
    fun foo(): Any
    fun bar(): Char
}

class C(c: Char) : A(c), B

fun main(args: Array<String>) {
    val b = (C('c') as B)
    println(b.c)
    println(b.foo())
    println(b.bar())
}
