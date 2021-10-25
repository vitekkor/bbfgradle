// Original bug: KT-5424

/**
 * Returns single element matching the given *predicate*, or null if element was not found or more than one elements were found
 */
public inline fun <T> Array<out T>.singleOrNull(predicate: (T) -> Boolean): T? {
    var single: T? = null
    var found = false
    for (element in this) {
        if (predicate(element)) {
            if (found) throw IllegalArgumentException("Collection contains more than one matching element")
            single = element
            found = true
        }
    }
    if (!found) return null
    return single
}
