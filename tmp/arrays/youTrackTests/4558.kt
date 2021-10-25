// Original bug: KT-35813

inline class MyMap<K, out V>(private val delegate: Map<K, V>) : Map<K, V> {
    override val size
        get() = delegate.size
    override fun isEmpty() =
        delegate.isEmpty()
    override fun containsKey(key: K) =
        delegate.containsKey(key)
    override fun containsValue(value: @UnsafeVariance V) =
        delegate.containsValue(value)
    override fun get(key: K) =
        delegate[key]
    override val keys
        get() = delegate.keys
    override val values
        get() = delegate.values
    override val entries
        get() = delegate.entries
}

fun main(args: Array<String>) {
    val original = mapOf(1 to 3)
    val myMap = MyMap(original)
    
    println(original == myMap)
    println(myMap == original)
}
