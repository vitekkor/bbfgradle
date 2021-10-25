// Original bug: KT-21724

class NeverNullMap<K, V>(private val backing: MutableMap<K, V> = mutableMapOf(), val default: () -> V): MutableMap<K, V> by backing {
    override operator fun get(key: K): V = backing.getOrPut(key, default)
}

fun main(args: Array<String>) {
    val myMap = NeverNullMap<String, Int> {0}
    myMap["test"] +=  10
}
