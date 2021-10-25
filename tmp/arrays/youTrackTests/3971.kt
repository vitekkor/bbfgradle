// Original bug: KT-5449

class A<T>

fun <T> A<T>.plus(a: A<T>): A<T> = a
@JvmName("plus?")
fun <T> A<T>?.plus(a: A<T>?): A<T>? = a

fun <T> foo(a: A<T>) {}
@JvmName("foo?")
fun <T> foo(a: A<T>?) {}


fun test() {
    val a = A<Int>()
    val b: A<Int>? = null

    a.plus(a)
    b.plus(b) // wrong error here

    foo(a)
    foo(b) // wrong error here
}
