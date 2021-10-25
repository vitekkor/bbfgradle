// Original bug: KT-28364

fun <K,V,R:Any> Map<K,V>.mapNotNullValues(transform: (Map.Entry<K, V>) -> R?): Map<K, R> {
    return entries.mapNotNull { entry -> transform(entry)?.let { entry.key to it } }.toMap()
}
