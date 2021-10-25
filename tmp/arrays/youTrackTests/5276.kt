// Original bug: KT-34043

/**
 * Compares two nullable [Comparable] values. Null is considered less than any value.
 *
 * @sample samples.comparisons.Comparisons.compareValues
 */
public fun <T : Comparable<*>> compareValues(a: T?, b: T?): Int {
    if (a === b) return 0
    if (a == null) return -1
    if (b == null) return 1

    @Suppress("UNCHECKED_CAST")
    return (a as Comparable<Any>).compareTo(b)
}
