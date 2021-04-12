// Original bug: KT-13687

open class Shape {
    object Circle : Shape() {
        override fun toString() = "Circle"
    }
    companion object {
        val shapes = listOf(Circle)
        fun test() {
            println(shapes.joinToString())
        }
    }
}
fun main(args: Array<String>) {
    val a = Shape.Circle
    println(a)   // Result: Circle
    Shape.test() // Result: Circle
}
