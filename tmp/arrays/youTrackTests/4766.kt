// Original bug: KT-29385

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@ExperimentalContracts
fun example(block: () -> Unit) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    block()
}

@ExperimentalContracts
class Example {
    fun foo(x: Int) {
        example {
            x // AnalyzerException: Expected an object reference, but found I
        }
    }

    fun bar(x: A) {
        example {
            x // OK, no error
        }
    }
}

class A
