// Original bug: KT-8374

fun main(args : Array<String>) {
    println(Double.MAX_VALUE.toInt())
    println(Double.MIN_VALUE.toInt())
    println(Double.NaN.toInt())
    println(Double.POSITIVE_INFINITY.toInt())
    println(Double.NEGATIVE_INFINITY.toInt())
}
