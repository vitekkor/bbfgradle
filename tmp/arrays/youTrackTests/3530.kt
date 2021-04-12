// Original bug: KT-38281

package ppp

import ppp.invoke as invoke1

object Bar
object Foo {
    val bar = Bar
}

fun Foo.bar() = 1
operator fun Bar.invoke() = 2

fun main() {
    println(Foo.bar.invoke1()) // The expression cannot be a selector (occur after a dot) 
                                              // Unresolved reference: bar
}

