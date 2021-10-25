// Original bug: KT-36007

inline fun <T> List<T>.forEachByIndex(action: (T) -> Unit) {
    val initialSize = size
    for (i in 0..lastIndex) {
        if (size != initialSize) throw ConcurrentModificationException()
        action(get(i))
    }
}
