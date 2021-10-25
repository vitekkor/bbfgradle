// Original bug: KT-37212

const val fpinfConst: Float = 1.0F / 0.0F
val fpinfVal: Float =  1.0F / 0.0F

fun main() {
    println(fpinfConst)  // prints 0.0
    println(fpinfVal)    // prints Infinity
}
