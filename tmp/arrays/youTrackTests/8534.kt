// Original bug: KT-18264

fun Float.toIntBits(): Int = java.lang.Float.floatToIntBits(this)
fun Float.toRawIntBits(): Int = java.lang.Float.floatToRawIntBits(this)
fun Int.toFloatBits(): Float = java.lang.Float.intBitsToFloat(this)

fun Double.toLongBits(): Long = java.lang.Double.doubleToLongBits(this)
fun Double.toRawLongBits(): Long = java.lang.Double.doubleToRawLongBits(this)
fun Long.toDoubleBits(): Double = java.lang.Double.longBitsToDouble(this)
