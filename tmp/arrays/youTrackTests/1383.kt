// Original bug: KT-44312

open class Base<T> {
    open fun foo(p1: T) {
        println("p1 " + p1)
    }
    open fun foo(p2: String) {
        println("p2 " + p2)
    }
}
class Derived : Base<String>()
fun main() {
    Derived().foo(p1 = "abc")
    Derived().foo(p2 = "dce")
}
