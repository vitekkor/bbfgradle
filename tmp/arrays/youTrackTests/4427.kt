// Original bug: KT-36647

inline fun <T, R, C : MutableCollection<in R>> Iterable<T>.intersperseTo(destination: C, separator: () -> R? = { null }, before: () -> R? = { null }, after: () -> R? = { null }, transform: (T) -> R): C {
    val ite = iterator()
    var first = true
    val preElm = before()
    if (preElm != null) {
        destination.add(preElm)
    }
    while (ite.hasNext()) {
        if (first) {
            first = false
        } else {
            separator()
        }
        destination.add(transform(ite.next()))
    }
    val postElm = after()
    if (postElm != null) {
        destination.add(postElm)
    }
    return destination
}
