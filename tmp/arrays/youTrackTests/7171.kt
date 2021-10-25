// Original bug: KT-27161

package test

enum class Test {
    A, B, OTHER
}

fun peek() = Test.A

fun main(args: Array<String>) {
    val x = when (val type = peek()) {
        Test.A -> "A"
        else -> "other: $type"
    }
    println(x)
}
