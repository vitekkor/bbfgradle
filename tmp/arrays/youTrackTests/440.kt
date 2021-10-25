// Original bug: KT-41308

fun main() {
    sequence { // [NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER] Not enough information to infer type variable T
        val list: List<String>? = null
        val outputList = list ?: listOf()
        yieldAll(outputList)
    }
}
