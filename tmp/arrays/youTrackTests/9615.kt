// Original bug: KT-11918

val map: Map<String, String> = mapOf("a" to "b", "c" to "")
val newMap: Map<String, Int> = map.mapNotNull {
    val (key, value) = it
    val newValue = if (value.isEmpty()) null else value.length
    newValue?.let { key to it }
}.toMap()
