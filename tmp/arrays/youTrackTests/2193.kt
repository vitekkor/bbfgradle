// Original bug: KT-34199

inline class Optional<out T> @Deprecated(
    message = "Not type-safe, use factory method",
    replaceWith = ReplaceWith("Inlined.of(_value)")
) constructor(private val _value: Any?) {
    val value: T?
        get() =
            @Suppress("UNCHECKED_CAST")
            if (isPresent) _value as T
            else null

    val isPresent: Boolean
        get() = _value != NULL

    companion object {
        @Suppress("DEPRECATION")
        fun <T> of(value: T) = Optional<T>(value)

        fun <T : Any> ofNullable(value: T?): Optional<T> =
            if (value == null) EMPTY
            else of(value)

        @Suppress("DEPRECATION")
        val EMPTY = Optional<Nothing>(NULL)
    }

    private object NULL
}

inline fun <T> Optional<T>.ifPresent(code: (T) -> Unit) {
    @Suppress("UNCHECKED_CAST")
    if (isPresent) return code(value as T)
}

fun <T> Optional<T>.or(code: () -> T): T {
    ifPresent { return it }
    return code()
}

fun main() {
    emptyOr { Optional.EMPTY }.value == null
}

fun <T> emptyOr(other: () -> T): T = Optional.EMPTY.or(other)
