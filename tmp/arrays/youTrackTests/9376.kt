// Original bug: KT-10667

class E : Map.Entry<String, String> {
    override val value: String
        get() = throw UnsupportedOperationException()
    override val key: String
        get() = throw UnsupportedOperationException()
}
