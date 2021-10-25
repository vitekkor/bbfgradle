// Original bug: KT-13687

open class Shape {
    object Circle : Shape()

    companion object {
        val shapes by lazy { listOf(Circle) }

        fun test() {
            println(shapes.joinToString())
        }
    }
}

fun main(args: Array<String>) {
    val a = Shape.Circle
    println(a)   // OK
    Shape.test() // OK
}
