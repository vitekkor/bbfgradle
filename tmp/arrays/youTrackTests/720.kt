// Original bug: KT-40179

fun main() {
    val vector = MutableDouble3(1.0, 2.0, 3.0)
    println("vector[0] is ${vector[0]}")
    println("calling 'vector[0] = 4.0'")
    vector[0] = 4.0
    println("vector[0] is ${vector[0]}")
}

sealed class Double3 {
    abstract val x: Double
    abstract val y: Double
    abstract val z: Double
}

class MutableDouble3(
    override var x: Double,
    override var y: Double,
    override var z: Double
) : Double3()

operator fun Double3.get(index: Int): Double = when (index) {
    0 -> x
    1 -> y
    2 -> z
    else -> throw IndexOutOfBoundsException("index=$index, validIndices=0..2")
}

operator fun MutableDouble3.set(index: Int, newValue: Double): Unit = when (index) {
    0 -> x = newValue
    1 -> y = newValue
    2 -> z = newValue
    else -> throw IndexOutOfBoundsException("index=$index, validIndices=0..2")
}

inline fun Double3.forEachIndexed(action: (index: Int, component: Double) -> Unit) {
    for (index in 0 until 3) {
        action(index, this[index])
    }
}

operator fun MutableDouble3.plusAssign(other: Double3): Unit = forEachIndexed { index, _ ->
    this[index] += other[index]
}
