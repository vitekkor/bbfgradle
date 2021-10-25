// Original bug: KT-26564

fun main(args: Array<String>) {                          
    println("Hello, world!")    
    val v = Vector(1.0, 0.0, 2.0)    
    println("Vector! $v")
}
inline class Vector(val raw: DoubleArray) {
    constructor(x: Double, y: Double, z: Double): this(doubleArrayOf(x, y, z))
}
