// Original bug: KT-29772

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind

@ExperimentalContracts
inline fun foo(block: () -> Unit) {
    kotlin.contracts.contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    block()
}

@ExperimentalContracts
fun main() {
    val x: Int
    foo {
        x = 42 // [CAPTURED_VAL_INITIALIZATION] Captured values initialization is forbidden due to possible reassignment
    }
}
