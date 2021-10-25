// Original bug: KT-30716

inline class Foo(val value: Int)

interface Parent {
    val member: Any
}

class Sub(override val member: Foo) : Parent

fun main() {
    val b = Sub(Foo(1))
    println(b.member)
}
