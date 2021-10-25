// Original bug: KT-42601

// WITH_RUNTIME

class MyMap<K : Any, V : Any> : AbstractMutableMap<K, V>() {
    override fun put(key: K, value: V): V? = null

    override val entries: MutableSet<MutableMap.MutableEntry<K, V>>
        get() = mutableSetOf()
}

fun box(): String {
    val map = MyMap<String, String>()
    return if (map.isEmpty()) "OK" else "FAIL"
}
