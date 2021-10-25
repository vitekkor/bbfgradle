// Original bug: KT-11953

inline fun <T> MutableList<T>.removeFirst(predicate: (T) -> Boolean): T? {
    val index = indexOfFirst { predicate(it) }

    return if (index >= 0) {
        removeAt(index)
    } else {
        null
    }
}
