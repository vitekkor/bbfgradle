// Original bug: KT-5343

class A {
    val x = object : Inner() {
        val u = foo()
    }

    open inner class Inner
    fun foo() = 42
}

fun main(args: Array<String>) {
    A().x
}
