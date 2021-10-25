// Original bug: KT-15642

open class Base {
    init { f() }
    open fun f() { }
}

class DerivedNull : Base() {
    var s: String? = null
    override fun f() { s = "set in f()" }
}

class DerivedNotNull : Base() {
    var s: String? = "set in ctor"
    override fun f() { s = "set in f()" }
}

fun main(args: Array<String>) {
    println(DerivedNull().s)
    println(DerivedNotNull().s)
}
