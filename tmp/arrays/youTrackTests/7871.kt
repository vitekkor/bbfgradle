// Original bug: KT-24930

sealed class Lce<T, U> {
    data class Loading<T, U>(val content: T? = null) : Lce<T, U>()
    data class Content<T, U>(val content: T) : Lce<T, U>()
    data class Error<T, U>(val error: U) : Lce<T, U>()
}
