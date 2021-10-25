// Original bug: KT-12206

package test

abstract class Base {
    val prop: String?
        get() = "OK"
}

class Derived : Base()

fun main(args: Array<String>) {
    val d = Derived()
    println() // <-- breakpoint here
}
