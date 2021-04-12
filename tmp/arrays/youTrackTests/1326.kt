// Original bug: KT-23329

object A {
    val x: String = B.y
}

object B {
    val y: String = A.x
}

fun main(args: Array<String>) {
    println(A.x) // null
    println(B.y) // null
}
