// Original bug: KT-28486

inline class ResultOrClosed(val x: Any?)
interface A<T> {
    fun foo(): T
}

interface A1 {
    fun foo(): ResultOrClosed
}

class B : A<ResultOrClosed>, A1 {
    // Report override conflict because for A we need the value to be boxed, but for A1 we'd want it not to be boxed
    override fun foo(): ResultOrClosed = ResultOrClosed("")
}

fun main() {
    val foo1: Any = ResultOrClosed("")
    val foo2: Any = (B() as A<ResultOrClosed>).foo()
    val foo3: Any = (B() as A1).foo()

    println(foo1 is ResultOrClosed) // prints true
    println(foo2 is ResultOrClosed) // prints false
    println(foo3 is ResultOrClosed) // prints true
}
