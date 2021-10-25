// Original bug: KT-18302

fun main(args: Array<String>) {

    // Prepare test data
    val floatBounds = floatArrayOf(0.0f, 1.0f, -1.0f, Float.MAX_VALUE, Float.MIN_VALUE,
            -Float.MAX_VALUE, -Float.MIN_VALUE, Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NaN)

    val doubleBounds = doubleArrayOf(0.0, 1.0, -1.0, Double.MAX_VALUE, Double.MIN_VALUE,
            -Double.MAX_VALUE, -Double.MIN_VALUE, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NaN)

    val nanFloatRanges = floatBounds.map { (it..Float.NaN) } + floatBounds.map { (Float.NaN..it) }
    val nanDoubleRanges = doubleBounds.map { (it..Double.NaN) } + doubleBounds.map { (Double.NaN..it) }

    // All float/double ranges with NaN are empty
    nanFloatRanges.forEach {
        println("$it - isEmpty: ${it.isEmpty()}")
    }

    nanDoubleRanges.forEach {
        println("$it - isEmpty: ${it.isEmpty()}")
    }

    // ...and all of them throw IllegalArgumentException if used as parameters of coerceIn
    nanFloatRanges.forEach {
        try {
            println("$it - coerceIn: ${0.0f.coerceIn(it)}")
        } catch (e : IllegalArgumentException) {
            println("$it - coerceIn: $e")
        }
    }

    nanDoubleRanges.forEach {
        try {
            println("$it - coerceIn: ${0.0.coerceIn(it)}")
        } catch (e : IllegalArgumentException) {
            println("$it - coerceIn: $e")
        }
    }

    // But! If explicit bounds used, results are different!
    nanFloatRanges.forEach {
        try {
            println("$it - coerceIn: ${0.0f.coerceIn(it.start, it.endInclusive)}")
        } catch (e : IllegalArgumentException) {
            println("$it - coerceIn: $e")
        }
    }

    nanDoubleRanges.forEach {
        try {
            println("$it - coerceIn: ${0.0.coerceIn(it.start, it.endInclusive)}")
        } catch (e : IllegalArgumentException) {
            println("$it - coerceIn: $e")
        }
    }

    // If bound of coerceAtLeast/coerceAtMost is NaN, then coerceAt... is receiver
    floatBounds.forEach {
        println("$it - coerceAtLeast(NaN): ${it.coerceAtLeast(Float.NaN)}")
        println("$it - coerceAtLeast(NaN): ${it.coerceAtMost(Float.NaN)}")
    }

    doubleBounds.forEach {
        println("$it - coerceAtLeast(NaN): ${it.coerceAtLeast(Double.NaN)}")
        println("$it - coerceAtLeast(NaN): ${it.coerceAtMost(Double.NaN)}")
    }
}
