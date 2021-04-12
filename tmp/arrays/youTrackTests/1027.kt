// Original bug: KT-43092

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract
@OptIn(ExperimentalContracts::class)
inline fun test(block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    try { block() }
    finally { return }
}
fun main() {
    test {
        println("A")
        return@main
    }
    println("B") // <-- Compiler claims this to be unreachable code
}
