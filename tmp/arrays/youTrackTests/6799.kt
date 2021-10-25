// Original bug: KT-8962

fun main(args: Array<String>) {
    val value = Double.NaN
    println("NaN == NaN: ${value == Double.NaN}")
    println("NaN.equals(NaN): ${value.equals(Double.NaN)}")
    println()
    when (value) {
        Double.NaN -> println("When NaN is NaN?")
        else -> println("When NaN not NaN?")
    }
}
