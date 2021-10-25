// Original bug: KT-43225

private fun <K, V> merge(first: Map<K, V>, second: Map<K, V>, valueOp: (V, V) -> V): Map<K, V> {
    val result = first.toMutableMap()
    for ((k, v) in second) {
        result.merge(k, v, valueOp)
    }
    return result
}
