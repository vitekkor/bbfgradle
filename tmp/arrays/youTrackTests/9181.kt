// Original bug: KT-13312

class A(val foo: Int.() -> Int)

fun main(vararg a: String) {
    val a = A({ this })
    
    println(a.foo(1))
    println(with(a) { foo(1) })
    println(with(a) { 1.foo() })
}
