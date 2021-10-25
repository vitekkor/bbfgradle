// Original bug: KT-33181

package superCallsSimple

fun main() {
    Bar().foo()
}

open class Foo {
    open fun foo() = 5
}

class Bar : Foo() {
    override fun foo(): Int {
        //Breakpoint!
        return 6
    }
}
