// Original bug: KT-41078

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

sealed class Test<T> {
    class Error(val message: String) : Test<Nothing>()
    class Value<T>(val value: T) : Test<T>()
}

@ExperimentalContracts
fun <T> Test<T>.isValue(): Boolean {
    contract {
        returns(true) implies (this@isValue is Test.Value<T>)
    }
    return this@isValue is Test.Value<T>
}

@ExperimentalContracts
fun test() {
    val x: Test<Int> = Test.Value(1)

    if (x.isValue()) {
        val value = x.value // detects type as T
    }
}
