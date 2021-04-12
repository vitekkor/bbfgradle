// Original bug: KT-39374

import kotlin.contracts.*

@OptIn(ExperimentalContracts::class)
public fun <T> runBlocking0(block: suspend () -> T): T? {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return null
}

sealed class S {
    class Z : S() {
        fun f(): String = "OK"
    }
}

val z: S = S.Z()

fun b(): String? = when (val w = z) {
    is S.Z -> runBlocking0 { w.f() }
}
