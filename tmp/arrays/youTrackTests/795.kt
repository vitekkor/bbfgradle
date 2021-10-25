// Original bug: KT-44503

public inline fun <T, K, R, M : MutableMap<in K, R>> Grouping<T, K>.aggregateTo(
    destination: M,
    operation: (key: K, accumulator: R?, element: T, first: Boolean) -> R
): M {
    for (e in this.sourceIterator()) {
        val key = keyOf(e)
        val accumulator = destination[key]
        destination[key] = operation(key, accumulator, e, accumulator == null && !destination.containsKey(key))
    }
    return destination
}
