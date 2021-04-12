// Original bug: KT-18237

typealias NumberType = Double

inline fun Number.toNumberType() = when (NumberType::class) {
    Double::class -> this.toDouble() as NumberType
    Float::class -> this.toFloat() as NumberType
    Long::class -> this.toLong() as NumberType
    Int::class -> this.toInt() as NumberType
    Short::class -> this.toShort() as NumberType
    Byte::class -> this.toByte() as NumberType
    else -> throw IllegalArgumentException("NumberType must be in [Double, Float, Long, Int, Short, Byte]")
}
