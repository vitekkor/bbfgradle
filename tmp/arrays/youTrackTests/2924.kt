// Original bug: KT-28064

var b = false

inline fun <R> call(block: (Any?)->R): R {
    val x = block(null)
    val y = block(x)
    return if (b) y else x
}

fun main(args: Array<String>) {
    val z = call {
        class Z() {
            fun foo() {}
        }
        println(it is Z)
        Z()
    }
    z.foo()
}
