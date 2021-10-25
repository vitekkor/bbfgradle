// Original bug: KT-44244

open class Base<T> {
    open fun foo(p1: T): String { return "p1:$p1" }
    open fun foo(p2: String): String { return "p2:$p2" }
}
class Derived : Base<String>()
