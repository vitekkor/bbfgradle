// Original bug: KT-10582

data class Vector2(val x: Int, val y: Int)

fun main(args: Array<String>) {
    val v = Vector2(3, 4)
    when (v) {
        Vector2(3,4) -> println("Object")
        is Vector2 -> println("Type")
    }
}
