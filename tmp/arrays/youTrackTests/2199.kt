// Original bug: KT-40616

fun main() {
    val i = setOf(1, 2, 3)
    val x = setOf(1, 2)
    val a = i + x - x
    val e = setOf(1, 2, 3)
    println("expected $e")
    println("actual $a")
}
