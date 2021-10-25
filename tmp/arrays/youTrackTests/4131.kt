// Original bug: KT-15922

interface Base

open class A : Base
class B : A()

fun foo(a: Base) {}
fun foo(b: B) {}

fun <T> bar(x: T, f: (T) -> Unit) {}

fun test() {
    bar(A(), ::foo) // error
    bar(B(), ::foo) // ok 
    bar(A()) { foo(it) } // ok
}
