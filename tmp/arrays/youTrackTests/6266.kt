// Original bug: KT-27108

inline class NonNegativeFix32(val rawBits: UInt) /*: Number*/ {
    companion object {
        const val FRACTION_BITS = 16
    }

    fun toDouble(): Double = rawBits.toLong().toDouble() / (1 shl FRACTION_BITS)

    /* ... */
}
