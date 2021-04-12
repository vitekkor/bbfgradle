// Original bug: KT-42711

class ULongMutableCollection(private val mc: MutableCollection<ULong>) : MutableCollection<ULong> {
    override val size: Int get() = mc.size
    override fun contains(element: ULong): Boolean = mc.contains(element)
    override fun containsAll(elements: Collection<ULong>): Boolean = mc.containsAll(elements)
    override fun isEmpty(): Boolean = mc.isEmpty()
    override fun add(element: ULong): Boolean = mc.add(element)
    override fun addAll(elements: Collection<ULong>): Boolean = mc.addAll(elements)
    override fun clear() { mc.clear() }
    override fun iterator(): MutableIterator<ULong> = mc.iterator()
    override fun remove(element: ULong): Boolean = mc.remove(element)
    override fun removeAll(elements: Collection<ULong>): Boolean = mc.removeAll(elements)
    override fun retainAll(elements: Collection<ULong>): Boolean = mc.retainAll(elements)
}
