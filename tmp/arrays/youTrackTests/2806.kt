// Original bug: KT-18080

fun main(args: Array<String>) {
    Foo<String>().bar(Foo<Int>(), Foo<String>()) 
    Foo<String>().bar(Foo<Int>(), ::Pair) // <-- type inference failed, not enough information
    Foo<String>().bar(Foo<Int>(), { t, r -> t to r })
}

class Foo<T>

fun <T : Any, R : Any> Foo<T>.bar(foo1: Foo<R>, foo2: Foo<T>) = Unit // reproduces only if this function exists
fun <T : Any, R : Any, U : Any> Foo<T>.bar(foo1: Foo<R>, buzz: (T, R) -> U) = Unit
