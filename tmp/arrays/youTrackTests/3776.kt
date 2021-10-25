// Original bug: KT-38091

import kotlin.reflect.jvm.reflect

class A {
    fun foo(s: String = "", vararg xs: Long): CharSequence = "foo"
}

fun main() {
    println(A::foo.reflect())
}
