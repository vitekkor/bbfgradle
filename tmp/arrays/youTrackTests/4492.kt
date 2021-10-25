// Original bug: KT-4734

fun <K, V> Map<K, V?>.filterNotNullValues(): Map<K, V> {
  return mapNotNull { (key, nullableValue) -> 
    nullableValue?.let { key to it } 
  }.toMap()
}
