// Original bug: KT-34431

class Queue<T>(override val size: Int) : Collection<T> {
    override fun contains(element: T): Boolean = TODO()

    override fun containsAll(elements: Collection<T>): Boolean = TODO()

    override fun isEmpty(): Boolean = TODO()

    override fun iterator(): Iterator<T> = TODO()

    fun remove(v: T) = Any() // name `remove` is crucial here, some leak from java.util.ArrayList?
}
