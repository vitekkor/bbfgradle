// Original bug: KT-8460

public fun <T> Iterable<T>.contains(element: T): Boolean {
    if (this is Collection<*>)
        return contains(element)
    return indexOf(element) >= 0
}
