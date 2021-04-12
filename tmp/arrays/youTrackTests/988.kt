// Original bug: KT-38849

import kotlin.contracts.*

@OptIn(ExperimentalContracts::class)
fun block(lambda: () -> Unit) {
    contract {
        callsInPlace(lambda, InvocationKind.EXACTLY_ONCE)
    }
    lambda()
}
fun main() {

    val list: List<Int>

    block {
        list = listOf(1, 2, 3)
    }
    
    block {
        println(listOf(2, 3, 4).first { list.contains(it) })
    }
}
