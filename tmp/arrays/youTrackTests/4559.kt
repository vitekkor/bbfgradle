// Original bug: KT-35813

class MyList<E>(private val delegate: List<E>, override val size: Int) : List<E> {
    override fun contains(element: E): Boolean = delegate.contains(element)

    override fun containsAll(elements: Collection<E>): Boolean = delegate.containsAll(elements)

    override fun get(index: Int): E = delegate[index]

    override fun indexOf(element: E): Int = delegate.indexOf(element)

    override fun isEmpty(): Boolean = delegate.isEmpty()

    override fun iterator(): Iterator<E> = delegate.iterator()

    override fun lastIndexOf(element: E): Int = delegate.lastIndexOf(element)

    override fun listIterator(): ListIterator<E> = delegate.listIterator()

    override fun listIterator(index: Int): ListIterator<E> = delegate.listIterator(index)

    override fun subList(fromIndex: Int, toIndex: Int): List<E> = delegate.subList(fromIndex, toIndex)

}
fun main() {
    val list = listOf(5)
    val myList = MyList(list, 1)

    println(list == myList) // true
    println(myList == list) // false
}
