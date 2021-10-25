// Original bug: KT-43019

open class Base {
    init {
        if (this is Derived) foo() // Info: Leaking 'this' in constructor of non-final class Base
    }
}

class Derived : Base() {
    private val bar = "asdf"

    fun foo() {
        if (bar != null) { // SENSELESS_COMPARISON: Condition 'bar != null' is always 'true'
            println(bar)
        }
        println(bar) // prints null
    }
}
