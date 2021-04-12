// Original bug: KT-26154

open class Base {
    val a: String = toString()      // Calling non-final function toString in constructor
    val b: Base = this              // Leaking 'this' in constructor of non-final class Base
    val c: String = this.toString() // ! No warnings/diagnostics !
}

class Derived(private val x: String) : Base() {
    override fun toString() = x
}

fun main(args: Array<String>) {
    println(Derived("foo").c)
}
