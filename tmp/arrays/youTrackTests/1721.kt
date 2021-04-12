// Original bug: KT-42662

package test

abstract class GenericStringMap<K> : Map<K, String>

class StringStringMap(private val m: MutableMap<String, String>) : GenericStringMap<String>() {
    override val size: Int get() = m.size
    override fun containsKey(key: String): Boolean = m.containsKey(key)
    override fun containsValue(value: String): Boolean = m.containsValue(value)
    override fun get(key: String): String? = m[key]
    override fun isEmpty(): Boolean = m.isEmpty()
    override val entries: MutableSet<MutableMap.MutableEntry<String, String>> get() = m.entries
    override val keys: MutableSet<String> get() = m.keys
    override val values: MutableCollection<String> get() = m.values
}

fun <T> test(m: GenericStringMap<T>, key: T) = m[key]

fun main() {
    val d = StringStringMap(hashMapOf("a" to "1", "b" to "2"))
    println(test(d, "a"))
}
