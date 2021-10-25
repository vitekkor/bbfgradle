// Original bug: KT-21372

class IntAddition(val value: Int) {
    fun addTo(i: Int) = i + value
    fun addToAll(ints: Iterable<Int>) = ints.map(::addTo) // reference to this::addTo
}
