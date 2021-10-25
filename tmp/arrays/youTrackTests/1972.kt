// Original bug: KT-31005

fun main() {
    TestList().removeAt(0) // AbstractMethodError
}

class TestList : MutableListBase<String>() {
    override fun removeAt(index: Int): String { return ""}
}

abstract class MutableListBase<E> : MutableList<E> {
// Uncomment for hotfix:
//    abstract override fun removeAt(index: Int): E

    override fun isEmpty(): Boolean = TODO()
    override fun containsAll(elements: Collection<E>): Boolean = TODO()
    override fun contains(element: E): Boolean = TODO()
    override fun lastIndexOf(element: E): Int = TODO()
    override fun indexOf(element: E): Int = TODO()
    override fun add(index: Int, element: E): Unit = TODO()
    override val size: Int get() = TODO()
    override fun get(index: Int): E = TODO()
    override fun set(index: Int, element: E): E = TODO()
    override fun add(element: E): Boolean = TODO()
    override fun addAll(index: Int, elements: Collection<E>): Boolean = TODO()
    override fun addAll(elements: Collection<E>): Boolean = TODO()
    override fun remove(element: E): Boolean = TODO()
    override fun removeAll(elements: Collection<E>): Boolean = TODO()
    override fun retainAll(elements: Collection<E>): Boolean = TODO()
    override fun clear(): Unit = TODO()
    override fun iterator(): MutableIterator<E> = TODO()
    override fun listIterator(): MutableListIterator<E> = TODO()
    override fun listIterator(index: Int): MutableListIterator<E> = TODO()
    override fun subList(fromIndex: Int, toIndex: Int): MutableList<E> = TODO()
}
