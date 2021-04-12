// Original bug: KT-34719

import java.io.Closeable
import java.util.stream.Stream

interface CloseableSequence<T> : Sequence<T>, Closeable

private class CloseableSequenceImpl<T>(
    sequence: Sequence<T>,
    private val sourceStream: Stream<*>
) : CloseableSequence<T>, Sequence<T> by sequence {
    override fun close() = sourceStream.close()
}

private class DummyCloseableSequenceImpl<T>(sequence: Sequence<T>) : CloseableSequence<T>, Sequence<T> by sequence {
    override fun close() = Unit
}

fun <T> Sequence<T>.closeable(): CloseableSequence<T> =
    DummyCloseableSequenceImpl(this)

fun <T> Sequence<T>.closeable(sourceStream: Stream<*>): CloseableSequence<T> =
    CloseableSequenceImpl(this, sourceStream)
