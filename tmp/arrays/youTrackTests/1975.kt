// Original bug: KT-25117

package test

fun main(args: Array<String>) {
    val x = A()
    println(x.a)
}

class A {
    companion object {
        private val test = 5
    }

    val a: Int

    init {
        a = test // (*) breakpoint
    }
}
