// Original bug: KT-5128

package test

interface A {
    fun foo(): Boolean
}

interface B {
    fun foo(): Any
}

open class C : A, B {
    override fun foo(): Boolean = true
}


fun main(args: Array<String>) {
    println(C().foo())
}
