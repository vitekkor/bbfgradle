// Original bug: KT-32869

import kotlin.reflect.KFunction1

fun <A1> fun1(f: KFunction1<A1, Unit>) {}

fun foo(arg: Int) {}
fun foo() {}

fun test() {
    fun1<Int>(::foo) // Remove explicit type arguments
    fun1(::foo) // compilation error
}
