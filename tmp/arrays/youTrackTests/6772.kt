// Original bug: KT-11273

interface NumberBag<T: Number> {
    val value: T
    fun append(other: T)
}

fun putTo(bag: NumberBag<in Double>) {
    bag.append(1.0)
    val v = bag.value // got Any? could be Number
}

fun useStar(bag: NumberBag<*>) {
    val outBag: NumberBag<out Number> = bag
    val inBag: NumberBag<in Nothing> = bag
    outBag.value // Number
    inBag.value  // Any?
}
