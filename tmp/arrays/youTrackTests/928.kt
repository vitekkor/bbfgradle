// Original bug: KT-39880

fun foo(fn: () -> Boolean) {}

fun main() {
    foo { 1 in setOf("1") } // [CONSTANT_EXPECTED_TYPE_MISMATCH] The integer literal does not conform to the expected type String
    val a = 1 in setOf("1") // CONSTANT_EXPECTED_TYPE_MISMATCH
}
