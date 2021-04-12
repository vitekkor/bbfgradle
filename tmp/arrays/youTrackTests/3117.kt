// Original bug: KT-21521

class CompilerKillingIterator<T, out R>(private val underlying: Iterator<T>, private val transform: suspend (e: T) -> Iterator<R>) {
    private var currentIt: Iterator<R> = null!!

    suspend tailrec fun next(): R {
        return if (currentIt.hasNext()) {
            currentIt.next()
        } else if (underlying.hasNext()) {
            currentIt = transform(underlying.next())
            next()
        } else {
            throw IllegalArgumentException("Cannot call next() on the empty iterator")
        }
    }
}
