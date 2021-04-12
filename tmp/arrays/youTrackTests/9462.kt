// Original bug: KT-13025

class A(val x: Int, val f: (A.() -> Int)?)

fun test(g: (A.() -> Int)?): Int? {
    val a = A(2, g)
    return a.f?.invoke(a)
}

fun main(args: Array<String>) {
    println(test { x + 3 })
}
