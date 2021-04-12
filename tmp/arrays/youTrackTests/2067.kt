// Original bug: KT-35467

import kotlin.contracts.*

@UseExperimental(ExperimentalContracts::class)
fun main() {
    val e2: String = "".apply2 { "" }
}

@ExperimentalContracts
inline fun String.apply2(block: String.() -> Unit): String {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    block()
    return this
}
