// Original bug: KT-28937

private val Number.isInteger get() = this is Byte || this is Short || this is Int || this is Long

operator fun Number.plus(other: Number): Number {
    return if (this.isInteger && other.isInteger) {
        this.toLong() + other.toLong()
    } else {
        this.toDouble() + other.toDouble()
    }
}

operator fun Number.minus(other: Number): Number {
    return if (this.isInteger && other.isInteger) {
        this.toLong() - other.toLong()
    } else {
        this.toDouble() - other.toDouble()
    }
}

operator fun Number.times(other: Number): Number {
    return if (this.isInteger && other.isInteger) {
        this.toLong() * other.toLong()
    } else {
        this.toDouble() * other.toDouble()
    }
}

operator fun Number.div(other: Number): Number {
    return this.toDouble() / other.toDouble()
}

operator fun Number.unaryMinus(): Number {
    return if (this.isInteger) {
        -this.toLong()
    } else {
        -this.toDouble()
    }
}
