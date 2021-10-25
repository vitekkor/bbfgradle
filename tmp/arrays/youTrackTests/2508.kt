// Original bug: KT-40835

import KotlinClass.Companion.b

class KotlinClass {

    companion object {
        fun b() = 42
    }
}

fun b() = Unit
fun test() {
    b() // now resolved to `fun b() = 42`
    KotlinClass()
    b()
}
