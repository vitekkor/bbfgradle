// Original bug: KT-27721

import java.util.*

fun main(args: Array<String>) {
    val _3monthInSeconds: Int = 3 * 30 * 24 * 3600
    val now = Date().time
    val inMs: Long = now + _3monthInSeconds * 1000
    println(inMs > now)
}
