// Original bug: KT-31431

@file: Suppress("NOTHING_TO_INLINE")
package ru.spbstu.wheels

inline fun Int.asBits() = IntBits(this)

inline class IntBits(val data: Int) {
    companion object {
        val Zero = 0.asBits()
    }

    inline fun asInt() = data

    inline infix fun andNot(that: IntBits): IntBits = IntBits(data and that.data.inv())

    inline val popCount: Int get() = Integer.bitCount(data)
    inline val lowestBitSet get() = Integer.lowestOneBit(data).asBits()

    inline fun forEachOneBit(body: (IntBits) -> Unit) {
        var mask = this
        while(mask != Zero) {
            val bit = mask.lowestBitSet
            body(bit)
            mask = mask andNot bit
        }
    }
}

inline fun Bits(data: Int) = data.asBits()
