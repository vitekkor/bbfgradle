// Original bug: KT-28243

class A {
    override fun equals(other: Any?) = true
}

val Nothing.TYPE
    get() = null

fun f1(x: A, y: Nothing?) {
    if (x == y) {
        println(x::TYPE)
        println("unreachable code!")
    }
}

fun main() {
    f1(A(), null)
    // val kotlin.Nothing.TYPE: kotlin.Nothing?
    // unreachable code!
}
