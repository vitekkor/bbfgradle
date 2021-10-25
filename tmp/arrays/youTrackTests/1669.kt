// Original bug: KT-34719

interface CloseableSequence<T> {
	fun <R> transform(transformer: (Sequence<T>) -> Sequence<R>): CloseableSequence<R>
	fun <R> consume(consumer: (Sequence<T>) -> R): R
}
