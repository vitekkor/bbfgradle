// Original bug: KT-19406

package test

open class Base {
    @JvmField val x = "O"
}

open class Derived: Base() {
    companion object {
        val x = "K"
        fun bar(s: String) = s + x
    }
}

class Host : Derived() {
    fun foo() = bar(x) // (*)
    
    companion object {
        val x = "K"
        fun bar(s: String) = s + x
    }
}

fun box() = Host().foo()

fun main(args: Array<String>) {
    println(box())
}
