// Original bug: KT-26554

inline class RgbaArray(val array: IntArray) {
    fun fill(size: Int = array.size) { }
}

fun main(args: Array<String>) {
    RgbaArray(IntArray(16)).fill()
}
