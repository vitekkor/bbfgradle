// Original bug: KT-40467

import kotlin.reflect.jvm.reflect

fun foo(x: Function<*> = {}) {
    // Will print "null" if the IR for the lambda is deep-copied
    println(x.reflect())
}

fun main() {
    foo()
}
