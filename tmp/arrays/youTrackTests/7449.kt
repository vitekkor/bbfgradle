// Original bug: KT-27433

open class A
class B : A()

fun A.foo() = 1
fun B.foo() = 2

fun main(args: Array<String>) {
    val a: A = B()
    println(a.foo()) // 1

    if (a is B) {
        println(a.foo()) // 2 (Smart cast to B)
    }
}
