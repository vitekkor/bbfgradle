// Original bug: KT-6591

class SomeClass<T> {
    fun foo(bar: T) { }
    fun foo(bar: (T) -> Int) { }
}

fun main(args: Array<String>) {
    val a = SomeClass<String?>()
    a.foo(null)
}
