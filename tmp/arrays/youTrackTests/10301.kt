// Original bug: KT-7271

public val Char.Companion.MIN_HIGH_SURROGATE: Char
    get() = '\uD800'
public val Char.Companion.MAX_HIGH_SURROGATE: Char
    get() = '\uDBFF'

public fun Char.isHighSurrogate(): Boolean = this in Char.MIN_HIGH_SURROGATE..Char.MAX_HIGH_SURROGATE
