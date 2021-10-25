// Original bug: KT-24727

private class ArrayByteIterator(private val array: ByteArray) : ByteIterator() {
    private var index = 0
    override fun hasNext() = index < array.size
    override fun nextByte() = when {
        ! hasNext() -> throw NoSuchElementException("The iterator doesn't have any more items")
        else -> array[index ++]
    }
}
