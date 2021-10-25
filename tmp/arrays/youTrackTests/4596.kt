// Original bug: KT-35701

class Point(var x: Int, var y: Int) {

    operator fun plus(other: Point): Point {
        return Point(x + other.x, y + other.y)
    }

    override fun toString(): String {
        return "<Point(${x}, ${y})>"
    }
}

fun main() {
    val p1 = Point(1, 2)
    val p2 = Point(3, 4)
    val p3 = Point(5, 6)
    println(p1 + p2 + p3)
}
