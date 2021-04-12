// Original bug: KT-13687

sealed class Shape {
    object Circle : Shape()
    object Triangle : Shape()
    object Rectangle : Shape()
    object Pentagon : Shape()
    
    companion object {
        val shapes = listOf(Circle, Triangle, Rectangle, Pentagon)
        
        fun test() {
            println(shapes.joinToString())
        }
    }
}

fun main(args: Array<String>) {
    Shape.test()
}
