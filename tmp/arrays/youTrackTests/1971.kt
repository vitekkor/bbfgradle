// Original bug: KT-31005


abstract class ListBase<out E> : List<E> {

    val lastIndex: Int
        get() = TODO()

    override fun indexOf(element: @UnsafeVariance E): Int {
        TODO()
    }

    override fun lastIndexOf(element: @UnsafeVariance E): Int {
        TODO()
    }

    override fun contains(element: @UnsafeVariance E): Boolean {
        TODO()
    }

    override fun containsAll(elements: Collection<@UnsafeVariance E>): Boolean {
        TODO()
    }

    override fun isEmpty(): Boolean {
        TODO()
    }

    override fun iterator(): Iterator<E> {
        TODO()
    }

    override fun listIterator(): ListIterator<E> {
        TODO()
    }

    override fun listIterator(index: Int): ListIterator<E> {
        TODO()
    }

    override fun subList(fromIndex: Int, toIndex: Int): List<E> {
        TODO()
    }
}

abstract class MutableListBase<E> : ListBase<E>(), MutableList<E> {

    override fun add(index: Int, element: E) {
        TODO()
    }

    override val size: Int
        get() = TODO()

    override fun get(index: Int): E = TODO()

    override fun set(index: Int, element: E): E {
        TODO()
    }

    override fun add(element: E): Boolean {
        TODO()
    }

    override fun addAll(index: Int, elements: Collection<E>): Boolean {
        TODO()
    }

    override fun addAll(elements: Collection<E>): Boolean {
        TODO()
    }

    override fun remove(element: E): Boolean {
        TODO()
    }

    override fun removeAll(elements: Collection<E>): Boolean {
        TODO()
    }

    override fun retainAll(elements: Collection<E>): Boolean {
        TODO()
    }

    override fun clear() {
        TODO()
    }

    override fun iterator(): MutableIterator<E> {
        TODO()
    }

    override fun listIterator(): MutableListIterator<E> {
        TODO()
    }

    override fun listIterator(index: Int): MutableListIterator<E> {
        TODO()
    }

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<E> {
        TODO()
    }
}
