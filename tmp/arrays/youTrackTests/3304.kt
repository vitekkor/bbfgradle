// Original bug: KT-39220

import kotlin.reflect.KFunction2

interface Foo {
    fun resolve(var1: Int): String
    fun resolve(var1: String): String
}

fun <T> bar(f: KFunction2<T, String, String>) {}

fun <T : Foo> main() {
    bar<T>(Foo::resolve) // OK in OI, Ambiguity in NI
    bar<Foo>(Foo::resolve) // OK
    bar(Foo::resolve) // OK
}
