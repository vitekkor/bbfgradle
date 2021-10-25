// Original bug: KT-23806

interface UnmodifiableIterator<out E : Any> : Iterator<E>
abstract class UnmodifiableMutableIterator<out E : Any> internal constructor() : UnmodifiableIterator<E>, MutableIterator<E> {
    @Deprecated("Unsupported")
    final override fun remove(): Nothing = throw UnsupportedOperationException()
}
