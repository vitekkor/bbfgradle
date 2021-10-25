// Original bug: KT-42722

fun main() {
    val set = setOf<Int>(1, 2, 3, 4, 5)
    println(0 in set)
    println(1 in set)
    println(null in set) // [TYPE_INFERENCE_ONLY_INPUT_TYPES_WARNING] Type inference failed. The value of the type parameter T should be mentioned in input types (argument types, receiver type or expected type). Try to specify it explicitly.
}
