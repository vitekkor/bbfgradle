// Original bug: KT-28483

inline class ResultOrClosed(val x: Any?)
interface A<T> {
    fun foo(): T
}

class B : A<ResultOrClosed> {
    override fun foo(): ResultOrClosed = ResultOrClosed("")
}

fun main() {
    val foo1: Any = ResultOrClosed("")
    val foo2: Any = (B() as A<ResultOrClosed>).foo()

    println(foo1 is ResultOrClosed) // prints true
    println(foo2 is ResultOrClosed) // prints false
}
