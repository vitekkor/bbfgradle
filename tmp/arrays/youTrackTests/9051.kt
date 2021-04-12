// Original bug: KT-16876

class SomeClass: Map<String, String> {
    override val entries: Set<Map.Entry<String, String>>
        get() = TODO("not implemented")
    override val keys: Set<String>
        get() = TODO("not implemented")
    override val size: Int
        get() = TODO("not implemented")
    override val values: Collection<String>
        get() = TODO("not implemented")

    override fun containsKey(key: String): Boolean {
        throw UnsupportedOperationException("not implemented")
    }

    override fun containsValue(value: String): Boolean {
        throw UnsupportedOperationException("not implemented")
    }

    override fun get(key: String): String? {
        throw UnsupportedOperationException("not implemented")
    }

    override fun isEmpty(): Boolean {
        throw UnsupportedOperationException("not implemented")
    }
}

fun main(args: Array<String>) {
    SomeClass::class.members
}
