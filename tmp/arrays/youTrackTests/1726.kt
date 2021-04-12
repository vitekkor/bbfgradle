// Original bug: KT-43106

class MyIterator<T>(val v: T): Iterator<T> {
    override fun next(): T = TODO()
    override fun hasNext(): Boolean = TODO()

    public fun remove(): Unit? = TODO()
}
