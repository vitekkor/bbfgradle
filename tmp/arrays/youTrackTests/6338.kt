// Original bug: KT-22332

// Exact copy of stdlib function (but without inline and @InlineOnly annotation)
operator fun <T> MutableCollection<in T>.plusAssign(elements: Iterable<T>) {
    this.addAll(elements)
}
