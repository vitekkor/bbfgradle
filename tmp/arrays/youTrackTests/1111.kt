// Original bug: KT-42722

class OpenHashSet(capacity: Int) {
    class HashCell(val key: Int, val value: Any?)
    internal val elements = Array(capacity) { HashCell(0, null) }
}
fun main() {
    val set = OpenHashSet(4)
    println("alpha" in set.elements) // TYPE_INFERENCE_ONLY_INPUT_TYPES_WARNING (NI), UNRESOLVED_REFERENCE (FIR)
}
