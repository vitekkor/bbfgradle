// Original bug: KT-26485

import kotlin.reflect.jvm.isAccessible

private fun foo(s: String): String = "$s"

fun main(args: Array<String>) {
    val foo = ::foo.apply { isAccessible = true }
    println(foo.call(null))
}
