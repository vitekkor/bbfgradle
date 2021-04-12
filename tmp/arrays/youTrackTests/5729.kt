// Original bug: KT-19228

fun Double.coerceToLong(): Long = when {
    isNaN() -> throw IllegalArgumentException("NaN")
    this > Long.MAX_VALUE -> Long.MAX_VALUE
    this < Long.MIN_VALUE -> Long.MIN_VALUE
    else -> toLong()
}
