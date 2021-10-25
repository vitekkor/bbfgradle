// Original bug: KT-27108

data class Size(val width: UInt, val height: UInt) {
    val aspectRatio: Double = width.toLong().toDouble() / height.toLong().toDouble()

    /* ... */
}
