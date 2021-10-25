// Original bug: KT-27328

data class ComparablePair<T : Comparable<T>, U : Comparable<U>>(val first: T, val second: U) : Comparable<ComparablePair<T, U>> {
    override fun compareTo(other: ComparablePair<T, U>): Int =
            compareValuesBy(this, other, { it.first }, { it.second })
}
