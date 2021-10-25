// Original bug: KT-29129

class A {
    fun greeting() = "Hello"
}

fun foo(a: Pair<Int, A.() -> Unit>) {}
fun test() {
    foo(1 to { greeting() }) //fails to infer that the lambda is A.() -> Unit
}
