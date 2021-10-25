// Original bug: KT-43059

private object EmptyStringMap : Map<String, Nothing> {
    override val size: Int get() = 0
    override fun isEmpty(): Boolean = true

    override fun containsKey(key: String): Boolean = false
    override fun containsValue(value: Nothing): Boolean = false
    override fun get(key: String): Nothing? = null
    override val entries: Set<Map.Entry<String, Nothing>> get() = null!!
    override val keys: Set<String> get() = null!!
    override val values: Collection<Nothing> get() = null!!
}

