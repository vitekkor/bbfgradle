// Original bug: KT-40306

class MapImpl<K, out A>(private val map: Map<K, A>) : Map<K, A> {
    override val entries: Set<Map.Entry<K, A>> get() = map.entries
    override val keys: Set<K> get() = map.keys
    override val size: Int get() = map.size
    override val values: Collection<A> get() = map.values
    override fun containsKey(key: K): Boolean = map.containsKey(key)
    override fun containsValue(value: @UnsafeVariance A): Boolean = map.containsValue(value)
    override fun get(key: K): A? = map.get(key)
    override fun isEmpty(): Boolean = map.isEmpty()
}
