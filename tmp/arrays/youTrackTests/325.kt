// Original bug: KT-45539

interface X<T> {
    operator fun plus(n: Int) : T
    fun next(): T = this + 1
}

inline class A(val value: Int) : X<A> {
    override operator fun plus(n: Int) = A(value + n)
}

fun main() {
    println(A(1).next())
}
