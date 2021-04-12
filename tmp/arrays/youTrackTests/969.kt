// Original bug: KT-45432

typealias LazySeq<T> = LazyCons<T>?

data class LazyCons<out T>(val tailThunk: Lazy<LazySeq<T>>)

fun <T> generateLazySeq(start: T?, next: (T) -> T?): LazySeq<T> =
    start?.let { LazyCons(lazy { generateLazySeq(next(it), next) }) }
