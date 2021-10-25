// Original bug: KT-4410

inline fun <T, R> Iterable<T>.collectNotNull(transform: (T) -> R): Iterable<R> {
    val result = ArrayList<R>()
    for (item in this) transform(item)?.let { result.add(it) }
    return result
}
