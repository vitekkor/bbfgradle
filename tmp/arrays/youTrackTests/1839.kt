// Original bug: KT-40305

class ListImpl<A> : List<A> {
    override val size: Int = 0
    override fun contains(element: A): Boolean = false
    override fun containsAll(elements: Collection<A>): Boolean = false
    override fun get(index: Int): A = null!!
    override fun indexOf(element: A): Int = -1
    override fun isEmpty(): Boolean = true
    override fun iterator(): Iterator<A> = null!!
    override fun lastIndexOf(element: A): Int = -1
    override fun listIterator(): ListIterator<A> = null!!
    override fun listIterator(index: Int): ListIterator<A> = null!!
    override fun subList(fromIndex: Int, toIndex: Int): List<A> = null!!
}
