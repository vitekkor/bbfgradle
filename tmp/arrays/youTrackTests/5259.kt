// Original bug: KT-31692

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@ExperimentalContracts
inline fun tryTo(block: () -> Unit) {
    contract {
        callsInPlace(block, kotlin.contracts.InvocationKind.EXACTLY_ONCE)
    }
    try {
        block()
    } catch (e: Exception) {
    }
}

@ExperimentalContracts
fun foo(): String? {
    tryTo {
        throw Exception("hi")
    }
    return null // Marked as unreachable code
}

@ExperimentalContracts
fun main() {
    println(foo()) // null
}
