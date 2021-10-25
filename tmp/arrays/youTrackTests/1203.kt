// Original bug: KT-26367

@file:UseExperimental(ExperimentalContracts::class)

package pack

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

inline fun <R> mockRun(block: () -> R): R {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return block()
}

fun useRun() {
    val vrp: String
    mockRun {
        vrp = "vrp"
    }
    println(vrp)
}
