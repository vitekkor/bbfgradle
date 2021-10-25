// Original bug: KT-30030

import kotlin.contracts.*

class C {
    fun bar() {
        42.foo()
        TODO()
    }

}

@UseExperimental(ExperimentalContracts::class)
private fun Int.foo() {
    contract {
        returns() implies (true)
    }
}
