// Original bug: KT-38820

package test

import kotlin.reflect.jvm.isAccessible

inline class S(val s: String)

object Host {
    private val ok = S("OK")
    val ref = ::ok.apply { isAccessible = true }
}

fun main() {
    println(Host.ref)
}
