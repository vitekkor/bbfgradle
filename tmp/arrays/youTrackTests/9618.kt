// Original bug: KT-11886

public inline fun <T> Iterable<T>.singleOrNull(predicate: (T) -> Boolean): T? {
    var single: T? = null
    var found = false
    for (element in this) {
        if (predicate(element)) {
            if (found) return null // when the element found then the function return null instead of the element
            single = element
            found = true
        }
    }
    if (!found) return null
    return single
}
