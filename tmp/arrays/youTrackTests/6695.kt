// Original bug: KT-29106

/** VERSION 2 */
fun <K, V: Any, F: (K) -> V> F.memoize(): (K) -> V {
    val map = mutableMapOf<K, V>()
    return {
        map.getOrPut(it) { this(it) }
    }
}
