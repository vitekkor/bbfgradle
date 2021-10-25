// Original bug: KT-43561

internal inline fun <K, V> Map<K, V>.getOrElseNullable(key: K, defaultValue: () -> V): V {
    val value = get(key)
    if (value == null && !containsKey(key)) {
        return defaultValue()
    } else {
        @Suppress("UNCHECKED_CAST")
        return value as V
 }
}
