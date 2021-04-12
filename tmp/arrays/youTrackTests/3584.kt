// Original bug: KT-38337

inline class ArrayWrapper(val array: IntArray)

data class InlineValue(val delegate: Map<Int, ArrayWrapper>) : Map<Int, ArrayWrapper> by delegate

fun main() {
    val id = 1234
    val wrap = ArrayWrapper(intArrayOf(1, 2, 3))

    val values = InlineValue(mutableMapOf(id to wrap))
    println(values[id])
}
