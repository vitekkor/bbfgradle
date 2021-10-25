// Original bug: KT-30923

// a.kt
fun main() {
    bar<Int>() // breakpoint and evaluate `bar<Int>()`
}

// b.kt
abstract class Foo<T> {
    private val any = Any()
    fun getAny() = any
}

inline fun <reified T> bar(): Any = object : Foo<T>() {}.getAny()
