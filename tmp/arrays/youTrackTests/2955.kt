// Original bug: KT-39468

fun <T> materialize() = null as T

abstract class Foo<T>

class Bar<T> : Foo<T>(), Comparable<Bar<T>> {
    override fun compareTo(other: Bar<T>) = TODO()
}

fun <T: Comparable<T>, S: T> Bar<in S>.greater(t: T) = 1
fun <T: Comparable<T>, S: T> Bar<in S>.greater(other: Foo<T>) = 1 // chosen by OI

fun main() {
    val column = materialize<Bar<Long>>()

    column.greater(column) // Overload resolution ambiguity in NI, OK in OI
}
