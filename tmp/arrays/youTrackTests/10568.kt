// Original bug: KT-3523

fun main(args : Array<String>) {
    Foo().Bar()
}

open class Base {
    fun doSomething() {
        println("SSS")
    }
}

class X(val action: () -> Unit) { }

class Foo : Base() {
    inner class Bar() {
        val x = X({ doSomething() })
    }
}
