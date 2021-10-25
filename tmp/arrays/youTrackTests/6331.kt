// Original bug: KT-30609

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@ExperimentalContracts
fun example(block: () -> Unit) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    block()
}

// ERROR: Kotlin: [Internal Error] java.lang.IllegalStateException (see stacktrace.txt)
@ExperimentalContracts
class Example(bool: Boolean) {
    init {
        example {
            if (bool) {

            }
        }
    }
}
