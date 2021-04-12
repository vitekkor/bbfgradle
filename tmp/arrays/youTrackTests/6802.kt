// Original bug: KT-9394

sealed class C {
    class A : C()
    class B : C()
}

fun main(args: Array<String>) {
    foo(C.A())
}

fun foo(c: C) {
    val x: Int
    when (c) {
        is C.A -> x = 1
        is C.B -> x = 2
    }
    print(x)
}
