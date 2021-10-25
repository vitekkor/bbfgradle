// Original bug: KT-26586

private class BatchSequence<T>(private val sequence: Sequence<T>, private val batchSize: Int) : Sequence<List<T>> {
    init {
        require(batchSize > 0) { "'batchSize' must be one or more" }
    }

    override fun iterator() = object : Iterator<List<T>> {
        private val source = sequence.iterator()
        override fun hasNext() = source.hasNext()

        override fun next(): List<T> {
            if (!hasNext()) { throw NoSuchElementException() }
            return mutableListOf<T>().apply {
                while (source.hasNext()) {
                    add(source.next())
                    if (size == batchSize) {
                        break
                    }
                }
            }
        }
    }
}

public fun <T> Sequence<T>.batch(batchSize: Int): Sequence<List<T>> = BatchSequence(this, batchSize)
