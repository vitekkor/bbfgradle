// Original bug: KT-35912

import kotlin.reflect.KFunction2

class Foo

fun test(fn: KFunction2<Foo, Array<out String>, String>) = null

fun Foo.bar(vararg x: String) = ""
fun Foo.bar(vararg x: Int) = ""

fun main() {
    test(Foo::bar) // Callable reference resolution ambiguity: public fun Foo.bar(vararg x: String): String defined in root package in file main.kt public fun Foo.bar(vararg x: Regex): String defined in root package in file main.kt
}
