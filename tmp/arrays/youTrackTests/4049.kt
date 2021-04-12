// Original bug: KT-6591

class SomeClass<T>

fun <T> SomeClass<T>.foo(bar: T) { }
fun <T> SomeClass<T>.foo(bar: (T) -> Int) { }

fun main(args: Array<String>) {
    val a = SomeClass<String?>()
    a.foo(null) // Error:(14, 11) Kotlin: Null can not be a value of a non-null type (kotlin.String?) -> kotlin.Int
}
