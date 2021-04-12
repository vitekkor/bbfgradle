// Original bug: KT-5347

fun main(args: Array<String>) {
    val capture = "0"

    class A(val x: Int) {
        fun aaa() = capture + x
        fun copy() = A(x)
    }

    val x = A(1).copy().aaa()
}
