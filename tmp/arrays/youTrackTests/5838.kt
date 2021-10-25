// Original bug: KT-31922

class A<T>

val foo: A<Int>  // [UNINITIALIZED_VARIABLE] Variable 'y' must be initialized
    get() = y

val x: A<Int> = foo
val y = A<Int>()

fun main() {
    println(x) // null
}
