// Original bug: KT-30892

var cond = true

fun main(args: Array<String>) {
    val v = if (cond) {
        fun foo1() = 1
        ::foo1
    } else {
        fun foo2() = 2
        ::foo2
    }
    println(v())
}
