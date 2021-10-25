// Original bug: KT-666

open class A<T>()

class B() : A<B>()

fun a(i: A<B>): Int {
    return 1
}

fun <T: A<T>> a(t: T): Boolean {
    return false
}

fun main(args : Array<String>) {
    val x = a(B())
    System.out?.println(x)
}
