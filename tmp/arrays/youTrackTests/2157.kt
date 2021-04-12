// Original bug: KT-24784

public inline fun <T> Iterable<T>.any(predicate: (T) -> Boolean): Boolean {
    if (this is Collection && isEmpty()) return false // This line kills perf
    for (element in this) if (predicate(element)) return true
    return false
}
