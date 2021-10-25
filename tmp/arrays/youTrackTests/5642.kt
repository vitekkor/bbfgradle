// Original bug: KT-33203

fun main() {
    MyMap<Unit,Unit>().entries
}

class MyMap<K,V>: AbstractMap<K, V>() {
    override val entries: HashSet<Map.Entry<K, V>>
        get() = hashSetOf()
    override val keys: Set<K>
        get() = setOf()
    override val values: Collection<V>
        get() = listOf()
}
