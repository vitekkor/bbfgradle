// Original bug: KT-36201

import kotlin.reflect.KFunction1

fun <A1> fun2(f: KFunction1<A1, Unit>, a: A1) {
    f.invoke(a)
}
fun containsRegex(vararg otherPatterns: String) {}

fun main() {
    fun2(::containsRegex, arrayOf("foo")) // OK in OI, expected String instead of Array<String> in NI
}
