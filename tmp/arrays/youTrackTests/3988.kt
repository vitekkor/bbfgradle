// Original bug: KT-16591

fun <T> doSomething(vararg t: Foo<T>) {
    t.forEach { println(it) }
}

class Foo<T>(val f: T)

interface Bar

class A: Bar
class B: Bar

fun callDoSomething() {
    // Have to cast A & B instances to Bar, otherwise there is a compilation error
    doSomething(Foo(A() as Bar), Foo(B() as Bar))

    // Compilation error despite A and B both implementing Bar
     doSomething(Foo(A()), Foo(B()))
}
