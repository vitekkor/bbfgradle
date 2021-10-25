// Original bug: KT-25983

inline class InlineDouble(val value: Double)

data class DataDouble(val double: Double)

fun main(args: Array<String>) {
    println(InlineDouble(0.0) == InlineDouble(-0.0))
    println(InlineDouble(Double.NaN) == InlineDouble(Double.NaN))

    println(DataDouble(0.0) == DataDouble(-0.0))
    println(DataDouble(Double.NaN) == DataDouble(Double.NaN))
}
