// Original bug: KT-29031

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
inline fun block(block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    block()
}

@ExperimentalContracts
fun main() {
    foo()
}
