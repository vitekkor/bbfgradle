// Original bug: KT-36678

//  a1.kt
open class Base<T> {
    open fun foo(p1: T) {
        println("p1 " + p1)
    }
    open fun foo(p2: String) {
        println("p2 " + p2)
    }
}
