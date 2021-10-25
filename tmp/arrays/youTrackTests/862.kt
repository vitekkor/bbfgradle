// Original bug: KT-45073

fun main() {
    println(3.toChecked() + 5.toChecked())
    println(Int.MAX_VALUE.dec().toChecked() + 1.toChecked())
    println(runCatching { Int.MAX_VALUE.dec().toChecked() + 2.toChecked() })
    println(Int.MIN_VALUE.inc().toChecked() + (-1).toChecked())
    println(runCatching { Int.MIN_VALUE.inc().toChecked() + (-2).toChecked() })
    println(Int.MIN_VALUE.toChecked() + 0.toChecked())
}

fun Int.toChecked() = CheckedInt(this)

class OverflowException : ArithmeticException()

inline class CheckedInt(val value: Int) {
    operator fun plus(other: CheckedInt) = when {
        other.value > 0 -> if (value <= Int.MAX_VALUE - other.value) CheckedInt(this.value + other.value) else throw OverflowException()
        other.value < 0 -> if (value >= Int.MIN_VALUE - other.value) CheckedInt(this.value + other.value) else throw OverflowException()
        else -> this
    }
    override fun toString() = "$value"
}
