// Original bug: KT-40691

@file:OptIn(ExperimentalContracts::class)
import kotlin.contracts.*

class A {
    val value = "Some value"

    init {
        foo {
            println(value) // [CAPTURED_VAL_INITIALIZATION] Captured values initialization is forbidden due to possible reassignment
        }
    }
}

fun foo(block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    block()
}
