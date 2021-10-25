// Original bug: KT-16620

class Proxy(val a: A) : A()

open class A {
    val map = mutableMapOf<A, A>()
    fun mapped(a: A) = map.getOrPut(a) { return Proxy(a) }
}

fun main() {
    val a = A()
    a.mapped(A())
    println(a.map)
}
