// Original bug: KT-25099

@file:Suppress("UNSUPPORTED_FEATURE", "INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")
import kotlin.internal.*

inline class Result<T>(private val _value: Any?) {
    companion object {
        @InlineOnly inline fun <T> success(value: T): Result<T> = Result(value)
    }
}
