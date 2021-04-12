// Original bug: KT-26343

class Bar<T:Any> {
    fun List<T>.bar(y: List<T>): List<T> = y
    fun List<T>.foo(y: List<T>?, z: List<T>): List<T> = if (y == null) { z } else { y.bar(z) }
}
