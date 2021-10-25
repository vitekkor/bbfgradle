// Original bug: KT-12617

fun <T, K> Iterable<T>.groupFoldBy(keySelector: (T) -> K, operation: (T, K) -> T): Map<K, T> = TODO()
fun <T, K> Iterable<T>.groupFoldBy(keySelector: (T) -> K, initial: T, operation: (T, K) -> T): Map<K, T> = TODO()
