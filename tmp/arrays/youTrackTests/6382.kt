// Original bug: KT-30228

class A {
    infix fun foo(x : Any) = A()
}

fun main() {
    val a = A()
    a.run {
        foo(0)
        foo(0)
    }
    a.foo(0) foo 0
    a.foo(0)
    a.foo(0)
}
