// Original bug: KT-27512

sealed class Option<out T> {
    class Some<T>(val value: T) : Option<T>()
    object None : Option<Nothing>()
    fun size(): Int = when (this) {
        is Some -> 1
        None -> 0
    }
}
