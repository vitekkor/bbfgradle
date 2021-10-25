// Original bug: KT-43069

interface A0 {
    val size: Int get() = 56
}

class B0 : Collection<String>, A0 {
    override fun isEmpty() = throw UnsupportedOperationException()
    override fun contains(o: String) = throw UnsupportedOperationException()
    override fun iterator() = throw UnsupportedOperationException()
    override fun containsAll(c: Collection<String>) = throw UnsupportedOperationException()
    override val size: Int
        get() = super.size
}
