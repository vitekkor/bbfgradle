// Original bug: KT-26461

import kotlin.contracts.*

@ExperimentalContracts
fun myRun(block: () -> Unit) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    block()
}

@ExperimentalContracts
fun main(args: Array<String>) {
    val x: String
    myRun { x = "abc" }
    println(x)
}
