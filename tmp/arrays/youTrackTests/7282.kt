// Original bug: KT-27560

import kotlin.reflect.full.memberProperties

class X {
    var y: suspend (Unit) -> Unit = {}
}

fun main(args: Array<String>) {
    val x = X()
    x::class.memberProperties.forEach { it.getter.call(x) }
}
