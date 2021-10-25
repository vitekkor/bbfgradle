// Original bug: KT-31893

fun main(values: Map<Int, String>) {
    values["42"] // [TYPE_INFERENCE_ONLY_INPUT_TYPES_WARNING] Type inference failed. The value of the type parameter K should be mentioned in input types (argument types, receiver type or expected type). Try to specify it explicitly.
}
