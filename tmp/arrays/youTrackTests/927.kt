// Original bug: KT-39880

fun foo(fn: () -> Boolean) {}

fun main() {
    foo { 1 in setOf("1") } // Type mismatch: inferred type is Unit but Boolean was expected
    val a = 1 in setOf("1") // TYPE_INFERENCE_ONLY_INPUT_TYPES_WARNING
}
