// Original bug: KT-42728

class A : MutableMap<Any, Any> {
    override val entries: MutableSet<MutableMap.MutableEntry<Any, Any>>
        get() = throw UnsupportedOperationException()
    override val keys: MutableSet<Any>
        get() = throw UnsupportedOperationException()
    override val values: MutableCollection<Any>
        get() = throw UnsupportedOperationException()

    override fun clear() {
        throw UnsupportedOperationException()
    }

    override fun put(key: Any, value: Any): Any? {
        throw UnsupportedOperationException()
    }

    override fun putAll(from: Map<out Any, Any>) {
        throw UnsupportedOperationException()
    }

    override fun remove(key: Any): Any? {
        throw UnsupportedOperationException()
    }

    override val size: Int
        get() = throw UnsupportedOperationException()

    override fun containsKey(key: Any): Boolean {
        throw UnsupportedOperationException()
    }

    override fun containsValue(value: Any): Boolean {
        throw UnsupportedOperationException()
    }

    override fun get(key: Any): Any? {
        throw UnsupportedOperationException()
    }

    override fun isEmpty(): Boolean {
        throw UnsupportedOperationException()
    }

    override fun remove(key: Any, value: Any): Boolean {
        val h = key.hashCode() + value.hashCode()
        if (h != ("abc".hashCode() + "cde".hashCode())) return false
        return key == "abc" && value == "cde"
    }
}
