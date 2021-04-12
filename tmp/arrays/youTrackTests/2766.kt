// Original bug: KT-40600

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
inline fun <reified R> test(block: () -> R): R {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return block()
}

class Test {
    val something: String

    init {
        test {
            something = "Hello, World!"
        }
    }
}

fun main() {
    println(Test().something)
}
