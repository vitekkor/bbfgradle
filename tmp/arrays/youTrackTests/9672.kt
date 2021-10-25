// Original bug: KT-3194

class Other<T>

class Some<T> {
    fun getOtherWithIn(): Other<in T> = Other()
    fun getOtherInvariant(): Other<T> = Other()
}

fun <T> inspectOut(cls : Some<out T>) {
    println(cls.getOtherWithIn()) // Unresolved reference error.
    println(cls.getOtherInvariant()) // Ok
}
