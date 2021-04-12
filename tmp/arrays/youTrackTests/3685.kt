// Original bug: KT-36682

/**
 * Returns a set containing all distinct elements from both collections.
 * 
 * The returned set preserves the element iteration order of the original collection.
 * Those elements of the [other] collection that are unique are iterated in the end
 * in the order of the [other] collection.
 */
public infix fun <T> Iterable<T>.union(other: Iterable<T>): Set<T> {
    val set = this.toMutableSet()
    set.addAll(other)
    return set
}
