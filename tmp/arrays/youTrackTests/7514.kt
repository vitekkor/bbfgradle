// Original bug: KT-22844

@file:Suppress("NOTHING_TO_INLINE")

internal inline fun Int.mod15() = when {
    this < 15 -> this
    this < 30 -> this - 15
    else -> throw IllegalStateException("$this")
}
