// Original bug: KT-37592

class A {
    fun bar() {
        fun String.foo(): Unit {} // (2)
        val foo: String.() -> Unit = {} // (1)
        "1".foo() // resolves to (2)
        with("2") {
            foo() // resolves to (1) in old FE, but to (2) in FIR
        }
    }
}
class B {
    val foo: String.() -> Unit = {} // (1)
    fun String.foo(): Unit {} // (2)
    fun bar() {
        "1".foo() // resolves to (2)
        with("2") {
            foo() // resolves to (2)
        }
    }
}
