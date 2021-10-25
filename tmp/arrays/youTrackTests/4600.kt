// Original bug: KT-35876

inline fun <T, reified P: T> Iterable<T>.firstInstanceOf(): T {
    for (element in this) {
        if (element is P) return element
    }
    throw NoSuchElementException()
} 
