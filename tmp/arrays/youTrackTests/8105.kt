// Original bug: KT-22482

package sssdgs

class A

val A.call get() = 5

fun withA(a: A.() -> Unit) {}

fun test() {
    withA {
        call.toDouble()
    }

    val foo = "" // perform rename refactoring foo -> call

    foo.toString()
}
