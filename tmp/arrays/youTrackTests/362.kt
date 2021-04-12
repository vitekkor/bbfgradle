// Original bug: KT-42020

open class Foo1<T> {
    fun foo(a: T) { println("1") }
    fun foo(b: String) { println("2") }
}

class Foo2 : Foo1<String>()

fun main() {
    Foo2().foo(a = "")  // 1
    Foo2().foo(b = "")  // 2
}
