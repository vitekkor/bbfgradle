// Original bug: KT-11953

inline fun <T> MutableCollection<T>.removeFirst(predicate: (T) -> Boolean): T? {
    val iter = iterator()

    while (iter.hasNext()) {
        val next = iter.next()

        if (predicate(next)) {
            iter.remove()
            return next
        }
    }

    return null
}
