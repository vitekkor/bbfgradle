// Original bug: KT-9805

class A {
    val foo: B.() -> Unit get() = null!!
}

class B {
    operator fun (B.() -> Unit).unaryPlus() = this()
}

fun test(a: A, b: B) {
    with(b) {
        + a.foo
    }
}
