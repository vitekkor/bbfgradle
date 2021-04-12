// Original bug: KT-35660

inline fun <K, V> Map<out K, V>.getOrElse(key: K, defaultValue: () -> V): V = get(key) ?: defaultValue()

fun test(map: Map<*, *>) {
    val a = map.get("abc")
    val b = map.getOrElse("abc") {"def" }
}

