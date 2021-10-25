// Original bug: KT-23205

import java.lang.Integer.parseInt

class A {
    val a: String
        get() = b.toString()
    val b: Int
        get() = parseInt(a)
}

fun main(args: Array<String>) {
    val c = A()
    println(c.a)
}

