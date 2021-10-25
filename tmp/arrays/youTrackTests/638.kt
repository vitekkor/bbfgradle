// Original bug: KT-43431

data class Point(
    val x: Int,
    val y: Int,
) {
    // ERROR?
    operator fun component0(): Int =
        x + y
}


fun main() {
    val p = Point(1, 2)
    val (x, y) = p
    println(x)
    println(y)
}
