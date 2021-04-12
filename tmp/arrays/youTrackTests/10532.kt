// Original bug: KT-4611

// using Function2<K, V, Unit>
inline fun <K, V> Iterator<Map.Entry<K, V>>.forEach(operation: (K, V) -> Unit): Unit{
    for((key, value) in this) operation(key, value)
}
