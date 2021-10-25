// Original bug: KT-44739

fun <A> doubleIfNotNullElseZeroInt(x: Int?): Int {
    return if (x != null) {
        x + x
    } else {
        0
    }
}

fun <A> doubleIfNotNullElseZeroDouble(x: Double?): Double {
    return if (x != null) {
        x + x
    } else {
        0.0
    }
}

// ... Plus even more if I have inline classes wrapping Ints and Doubles to represent typed units of measure.
