// Original bug: KT-39777

fun <T> materialize(): T = null as T

class Foo
class Bar<T>

fun <T> test(x: Bar<out T>) {}

fun main() {
    test<Foo>(materialize()) // OK in OI, not enough information... in NI
}
