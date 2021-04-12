// Original bug: KT-42469

inline class IT(val x: Int)

inline class InlineIterator(private val it: Iterator<IT>) : Iterator<IT> {
    override fun hasNext(): Boolean = it.hasNext()
    override fun next(): IT = it.next()
}
