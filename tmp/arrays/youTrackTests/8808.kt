// Original bug: KT-18730

class BrokenOnce<T>(val v: T) {
    fun seq(): Sequence<T> = generateSequence(v, { null })
}

class WorkingOnce<T: Any>(val v: T) {
    fun seq(): Sequence<T> = generateSequence(v, { null })
}
