// Original bug: KT-28789

/**
 * Returns the single element, or throws an exception if the list is empty or has more than one element.
 */
public fun <T> List<T>.single(): T {
    return when (size) {
        0 -> throw NoSuchElementException("List is empty.")
        1 -> this[0]
        else -> throw IllegalArgumentException("List has more than one element.")
    }
}
