// Original bug: KT-21392

inline class Option<out T> @PublishedApi internal constructor(@PublishedApi internal val value: Any?) {
    public val isSome: Boolean get() = value !is None
    public val isNone: Boolean get() = value is None
    public fun getOrNull(): T? = when {
        isNone -> null
        else -> value as T
    }

    companion object {
        public inline fun <T> some(value: T): Option<T> = Option(value)
        public inline fun <T> none(): Option<T> = Option(None)

        @PublishedApi
        internal object None
    }
}
