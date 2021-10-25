// Original bug: KT-32997

package foo.bar

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

fun foo() {
//    for ((a, _) in listOf(1 to 2, 3 to 4)) {
    val (a, _) = 1 to 2
    bar {
        println(a)
    }
//    }
}

@UseExperimental(ExperimentalContracts::class)
fun bar(f: () -> Unit = {}) {
    contract {
        callsInPlace(f, InvocationKind.EXACTLY_ONCE)
    }

    f()
}
