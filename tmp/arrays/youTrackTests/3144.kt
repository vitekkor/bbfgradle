// Original bug: KT-39014

import kotlin.reflect.*

class D {
    operator fun getValue(_this: Any?, p: KProperty<*>) = ""
}

val String.p by D()

fun main() {
    String::p.getDelegate("")
}
