// Original bug: KT-36647

inline fun <T> Iterable<T>.intersperseEach(separator: () -> Unit = {}, before: () -> Unit = {}, after: () -> Unit = {}, action: (T) -> Unit): Unit {
    val ite = iterator()
    var first = true
    before()
    while (ite.hasNext()) {
        if (first) {
            first = false
        } else {
            separator()
        }
        action(ite.next())
    }
    after()
}
