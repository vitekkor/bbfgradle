// Original bug: KT-8440

data class A(val x : Int, val component2 : () -> Int)

fun foo(x : A) {
    val (y, z) = x
    println(y)
    println(z)
}

fun main(args: Array<String>) {
    foo(A(1) { 2 })
}
