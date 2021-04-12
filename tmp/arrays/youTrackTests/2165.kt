// Original bug: KT-38901

fun bar(x: Int) {}

fun main() {
    bar(1 shl 31) // Ok in FE 1.0, prohibited in FIR because 2147483648 == (1 shl 31) is not in a range of valid int values
}
