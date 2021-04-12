// Original bug: KT-39107

import kotlin.jvm.JvmName

inline class Foo(val value: String?) {
    fun foo(other: String?): String? = (value ?: other)?.plus(" (nullable)")

    @Suppress("INAPPLICABLE_JVM_NAME")
    @JvmName("fooNotNull")
    fun foo(other: String): String = (value ?: other).plus(" (non-nullable)")
}

var nullableStr: String? = "foo"

fun x(v: Foo): String = v.foo("hello")
fun y(v: Foo): String? = v.foo(nullableStr)
fun z(v: Foo): String? = v.foo(null)

fun main() {
    listOf(::x, ::y, ::z).forEach {
        println(it(Foo("nonnull")))
        println(it(Foo(null)))
    }
}
