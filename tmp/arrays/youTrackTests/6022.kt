// Original bug: KT-30411

interface RecursiveGeneric<T : RecursiveGeneric<T>>

class A : RecursiveGeneric<A>
class B : RecursiveGeneric<B>

fun <K> select(x: K, y: K): K = x

fun foo(a: A, b: B) {
    val c = select(a, b) // try invoking "specify type explicitly" 
}
