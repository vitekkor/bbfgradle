// Original bug: KT-39532

fun foo(f: () -> Unit) {}
fun suspendFoo(f: suspend () -> Unit) {}

fun bar() {}
fun adaptParameter(a: Int = 42) {}
fun adaptReturnType(): String = ""

fun test() {
    foo(::adaptParameter) // OK
    foo(::adaptReturnType) // OK
    suspendFoo(::bar) // OK
}
