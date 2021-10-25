// Original bug: KT-27770

import kotlin.test.*
import kotlin.contracts.*

@UseExperimental(kotlin.contracts.ExperimentalContracts::class)
fun check(actual: Boolean) {
    contract { returns() implies actual }
    assertTrue(actual)
}

fun main() {
    open class S
    class P(val str: String = "P") : S()

    val s: S = P()

//    require(s is P)
    check(s is P)
    println(s.str)
}
