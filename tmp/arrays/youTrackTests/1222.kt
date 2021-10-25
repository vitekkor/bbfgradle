// Original bug: KT-39728

import kotlin.reflect.KAnnotatedElement

fun foo(param: KAnnotatedElement) : Boolean = TODO()

class Foo {
    fun bar(a: Int, b: Int) {}
}

fun main() {
    foo(Foo::bar) // <-- TYPE_MISMATCH  error highlighted but compile without a problem
}
