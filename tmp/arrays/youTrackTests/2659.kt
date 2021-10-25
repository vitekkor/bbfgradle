// Original bug: KT-34506

public fun <T, R> Sequence<T>.flatMap(transform: (T) -> Iterable<R>): Sequence<R> {
    val tmp: (T) -> Sequence<R> = { transform(it).asSequence() }
    return this.flatMap(tmp)
}
