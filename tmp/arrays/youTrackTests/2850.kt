// Original bug: KT-40520

abstract class Base<T> {
    abstract inner class Inner1 {
        abstract fun foo(x: T)
    }
}
class Derived<T>() : Base<T>() {
    inner class Inner2 : Inner1() {
        override fun foo(x: T) {}
    }
}
fun main() {
}
