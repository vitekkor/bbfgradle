// Original bug: KT-24799

class A<T>
fun <E, T : Iterable<E>> A<T>.foo(e: E) = println(e)

fun main() {
    A<Iterable<Int>>().foo(1)         // ok
    A<Iterable<Int>>().foo("asdf")    // would like it fails but does not as it transforms T into an Iterable<Any>
}
