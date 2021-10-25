// Original bug: KT-38384

// current public API
/*wannabe inline*/ fun <T, R> Sequence<T>.map(/*wannabe noinline*/ transform: (T) -> R): Sequence<R> =
    TransformingSequence(this, transform)

// reworked implementation
/*wannabe @PublishedApi*/ internal class TransformingSequence<T, R>
constructor(private val sequence: Sequence<T>, private val transformer: (T) -> R) : Sequence<R> {
    override fun iterator(): Iterator<R> = sequence.iterator().map(transformer)
    //   Compiler is smart enough to instantiate ThisKt$map$1 ^^^^^^^^^^^^^^^^^
    // without copy-pasting anonymous class to the call-site,
    // because `transformer` is known to be `noinline`

 // internal fun <E> flatten(iterator: (R) -> Iterator<E>): Sequence<E> = â¦
}

// new API

// Use with `noinline transformer` introduces no changes;
// use with a crossinline function merges transformer with iterator,
// avoiding extra object and extra virtual call
inline fun <T, R> Iterator<T>.map(crossinline transformer: (T) -> R): Iterator<R> {
    return object : TransformingIterator<T, R>(this@map) {
        override fun next(): R =
            transformer(source.next())
    }
}

@PublishedApi internal abstract class TransformingIterator<T, R>(
    @JvmField protected val source: Iterator<T>
) : Iterator<R> {
    final override fun hasNext(): Boolean =
        source.hasNext() // by the way, there are currently at least 4 sequence implementation that do this
}
