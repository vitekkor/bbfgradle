// Original bug: KT-28792

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@ExperimentalContracts
fun createOnce(runFunction: () -> Unit) {
    contract {
        callsInPlace(runFunction, InvocationKind.EXACTLY_ONCE)
    }
    runFunction()
}

@ExperimentalContracts
fun getKotlinVersion(): Float {
    val kotlinVersion: Float
    createOnce {
        kotlinVersion = 1.3f
    }

    return kotlinVersion
}

@ExperimentalContracts
fun main() {
    println(getKotlinVersion())
}
