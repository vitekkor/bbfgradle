// Original bug: KT-8147

fun String.foo(): String {
    class A {
        val x = this@foo
    }
    return A().x
}

fun String.bar(): String {
    class A {
        val x = this@bar
    }
    fun qux() = A()
    return qux().x
}

fun main(args: Array<String>) {
    println("String.foo() works fine".foo())
    println("String.bar() throws VerifyError".bar())
}
