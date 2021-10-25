// Original bug: KT-16588

import java.math.*

fun number(doLong: Boolean): Number = when {
    doLong -> 1.toLong()
    else -> BigDecimal.valueOf(0)
}

fun main(args: Array<String>) {
    println(number(true))
}
