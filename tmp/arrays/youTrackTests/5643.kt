// Original bug: KT-33203

fun main() {
    MyMap<Unit,Unit>().entries
}

class MyMap<K,V>: AbstractMap<K, V>() {
    fun getEntries(): Set<Map.Entry<K, V>> {
        println("getEntries")
        return createEntries()
    }
    override val entries: HashSet<Map.Entry<K, V>>
        get() {
            println("entries")
            return createEntries()
        }
    private fun createEntries(): HashSet<Map.Entry<K, V>> {
        return hashSetOf()
    }

    override val keys: Set<K>
        get() = hashSetOf()
    override val values: Collection<V>
        get() = listOf()
}
