// Original bug: KT-40179

fun main() {

}

open class Double3

class MutableDouble3 : Double3()

operator fun Double3.get(index: Int): Double = 2.0

operator fun MutableDouble3.set(index: Int, newValue: Double) {}

operator fun MutableDouble3.plusAssign(other: Double3): Unit { this[5] += 1.0 }
                                                                     //^^^ resolved to Double.plus
