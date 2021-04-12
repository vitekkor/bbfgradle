// Original bug: KT-29032

package foo

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@ExperimentalContracts
fun foo() {
    val a: String
    println("aa")
    block {
        a = "a"
    }
    println(a)
}

@ExperimentalContracts
fun block(block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    block()
}
