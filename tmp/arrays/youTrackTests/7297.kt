// Original bug: KT-26554

data class RGBA(val rgba: Int)

inline class RgbaArray(val array: IntArray) {
   	val size: Int get() = array.size
	fun fill(value: RGBA, start: Int = 0, end: Int = this.size): Unit = array.fill(value.rgba, start, end)
}

fun main(args: Array<String>) {
    RgbaArray(IntArray(16)).fill(RGBA(0))
}
