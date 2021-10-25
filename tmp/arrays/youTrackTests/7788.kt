// Original bug: KT-25629

package test

const val D_0 = 0.0
const val D_MINUS_0 = -0.0
const val IS_0_EQUAL_TO_MINUS_0 = D_0 == D_MINUS_0

fun main(args: Array<String>) {
    println(IS_0_EQUAL_TO_MINUS_0)     // false
    println(D_0 == D_MINUS_0)          // true
}
