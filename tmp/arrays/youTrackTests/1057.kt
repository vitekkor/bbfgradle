// Original bug: KT-42044

import kotlin.contracts.*

class Smth {
    val whatever: Int // MUST_BE_INITIALIZED_OR_BE_ABSTRACT

    init {
        calculate({ whatever = it }) // CAPTURED_MEMBER_VAL_INITIALIZATION
    }

    @OptIn(ExperimentalContracts::class)
    private inline fun calculate(block: (Int) -> Unit) {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }
        block(0)
    }
}
