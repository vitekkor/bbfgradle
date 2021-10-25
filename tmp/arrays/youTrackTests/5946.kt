// Original bug: KT-18131

internal val referredInternalVal = 1
internal var referredInternalVar = 2
internal fun referredInternalFun() = 3
internal class ReferredInternalClass {}

fun referInternalFun() {
    println(referredInternalVal)
    println(referredInternalVar)
    println(referredInternalFun())
    println(ReferredInternalClass())
}

val referredPublicVal = 1

fun referPublicFun() {
    println(referredPublicVal)
} 