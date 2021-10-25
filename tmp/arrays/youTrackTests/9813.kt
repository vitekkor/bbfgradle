// Original bug: KT-10806

fun <T, V> T.iterableFor(count: T.() -> Int, get: T.(Int) -> Any) = object : Iterable<V> {
    override fun iterator(): Iterator<V> = object : Iterator<V> {
        var i = 0

        override fun hasNext(): Boolean = i < count()

        @Suppress("UNCHECKED_CAST")
        override fun next(): V = get(i++) as V
    }
}
