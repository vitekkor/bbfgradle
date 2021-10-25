// Original bug: KT-10803

open class My
fun foo(): List<My> {
    val first = listOf(My()).map { object : My() {} }
    val second = listOf(My()).map { object : My() {} }
    return first + second // Error: non of the following functions can be called
}
