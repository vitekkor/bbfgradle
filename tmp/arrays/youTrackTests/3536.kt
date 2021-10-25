// Original bug: KT-29565

inline fun <reified T> isT(x: Any) = x is T

class A<T>(a: T)

fun main(args: Array<String>) {
    println(isT<A<String>>(A(""))) // true
    println(isT<A<String>>(A(42))) // true
}
