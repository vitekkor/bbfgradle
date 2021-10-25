// Original bug: KT-2885

package test

fun Float.isNan() = java.lang.Float.isNaN(this)
fun Float.isInfinity() = java.lang.Float.isInfinite(this)

fun main(args: Array<String>) {
    val f1 : Float = 0.0.toFloat()
    val f2 : Float = 0.5.toFloat()

    println((f1 / f1).isNan())      // True                          
    println((f1 / f1).isInfinity()) // False
    println((f2 / f1).isNan())      // False
    println((f2 / f1).isInfinity()) // True
}
