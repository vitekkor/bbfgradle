// Original bug: KT-43059

class A : Map<String, String> {
    override val size: Int get() = 56

    override fun isEmpty(): Boolean {
        throw UnsupportedOperationException()
    }

    override fun containsKey(key: String): Boolean {
        throw UnsupportedOperationException()
    }

    override fun containsValue(value: String): Boolean {
        throw UnsupportedOperationException()
    }

    override fun get(key: String): String? {
        throw UnsupportedOperationException()
    }

    override val keys: Set<String> get() {
        throw UnsupportedOperationException()
    }

    override val values: Collection<String> get() {
        throw UnsupportedOperationException()
    }

    override val entries: Set<Map.Entry<String, String>> get() {
        throw UnsupportedOperationException()
    }
}
