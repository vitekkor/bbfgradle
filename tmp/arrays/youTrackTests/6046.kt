// Original bug: KT-31820

interface Signed<Self, Sgn : Int?> where Self : Signed<Self, Sgn>, Self : Number {
// â¦ or with built-in Self and Generic defaults: Signed<Sgn : Int? = Int?>
    /** https://en.wikipedia.org/wiki/Absolute_value */
    fun abs(): Self

    /** https://en.wikipedia.org/wiki/Absolute_difference */
    fun absDiff(): Self

    /** https://en.wikipedia.org/wiki/Sign_function */
    fun sgn(): Sgn

    val isNegative: Boolean

    val isPositive: Boolean
}

inline val Signed<*, *>.isSigned: Boolean
    get() = isNegative || isPositive

inline val Signed<*, *>.isUnsigned: Boolean
    get() = !isSigned

// Int : Signed<Int, Int>
// Long : Signed<Long, Int>
// BigInteger : Signed<BigInteger, Int>
// Float : Signed<Float, Int?>
// Double : Signed<Double, Int?>
// BigDecimal : Signed<BigDecimal, Int>
