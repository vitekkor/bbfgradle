// Original bug: KT-29510

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@ExperimentalContracts
class A {
    fun foo() {
        bar {}
    }

    inline fun bar(crossinline f: () -> Unit) {
        baz {
           f()
        }
    }
}

@ExperimentalContracts
inline fun baz(crossinline f: () -> Unit) {
    contract {
        callsInPlace(f, InvocationKind.EXACTLY_ONCE)
    }

    f()
}
