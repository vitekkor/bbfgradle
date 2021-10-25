// Original bug: KT-36506

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract


suspend fun main()  {
    val test: Int
    runBlock { test = 42 }
    println(test)  // prints 0  <<<<<<<<<<<<<<
}


@UseExperimental(ExperimentalContracts::class)
suspend fun runBlock(block: suspend () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }

    block()
}
