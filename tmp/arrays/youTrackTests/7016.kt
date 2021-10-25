// Original bug: KT-29122

fun main() {
    val myMap = MyMutableMap<String, Int> { 0 }
    myMap["1"] = 1  // works
    myMap["2"]++    // throws exception
}

class MyMutableMap<K, V>(
    private val map: MutableMap<K, V> = mutableMapOf(),
    private val defaultValueSelector: (K) -> V
) : MutableMap<K, V> by map {

    override operator fun get(key: K): V =
        map[key] ?: defaultValueSelector(key).also { map[key] = it }
}
