// Original bug: KT-42787

private suspend fun <K, V> SequenceScope<Map.Entry<K, V>>.visit(map: Map<K, V>) {
    map.entries.forEach {
        yield(it)
        if (it.value is Map<*, *>) {
            visit(it.value as Map<K, V>)
        }
    }
}

fun <K, V> Map<K, V>.toRecursiveSequence(): Sequence<Map.Entry<K, V>> = sequence {
    visit(this@toRecursiveSequence)
}
