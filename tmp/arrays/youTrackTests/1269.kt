// Original bug: KT-44660

package test

inline class ValueOrClosed<out T>(private val holder: Any?) {
    companion object {
        @Suppress("NOTHING_TO_INLINE")
        internal inline fun <E> value(value: E): ValueOrClosed<E> = ValueOrClosed(value)
    }
}
