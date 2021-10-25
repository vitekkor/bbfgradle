// Original bug: KT-22323

open class A {
    fun CharSequence.bar() = println("A.CharSequence.bar($this)")
}
class B : A() {
    fun String.bar() = println("B.String.bar($this)")
}

fun main(args: Array<String>) {
    val s: String = "Hello World!"
    val c: CharSequence = s
    with(A()) {
        c.bar()
        s.bar()
    }
    with(B()) {
        c.bar()
        s.bar() // This one is resolved to the function in B, others are resolved to A, no ambiguity
    }
    c.foo()
    s.foo() // String is picked because receiver is more specific than CharSequence, no ambiguity
}

fun CharSequence.foo() = println("CharSequence.foo($this)")
fun String.foo() = println("String.foo($this)")

