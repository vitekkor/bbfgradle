// Original bug: KT-30716

inline class Foo(val value: Int)

interface Parent<T> {
    val member: T
}

class Sub(override val member: Foo) : Parent<Foo>

fun main() {
    val b = Sub(Foo(1))
    println(b.member)
}
