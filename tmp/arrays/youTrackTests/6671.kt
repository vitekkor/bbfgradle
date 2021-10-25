// Original bug: KT-28789

/**
 * Returns the single element, if any, or throws an exception if the list has more than one element.
 */
public fun <T> List<T>.optional(): T? {
    return when (size) {
        0 -> null
        1 -> this[0]
        else -> throw IllegalArgumentException("List has more than one element.")
    }
}
