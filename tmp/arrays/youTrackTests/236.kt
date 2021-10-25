// Original bug: KT-45673

class MyAwesomeMap: AbstractMutableMap<String, Int?> {
    val inner: MutableMap<String, Int?> = mutableMapOf()

    override val entries: MutableSet<MutableMap.MutableEntry<String, Int?>>
        get() = inner.entries

    override fun put(key: String, value: Int?): Int? = inner.put(key, value)

    constructor(map: Map<String, Int?>) {
        for ((k, v) in map) put(k, v)
    }

    // if you remove this override, everything works as intended
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}
