// Original bug: KT-34202

import kotlin.math.*

fun main() {
    println(listOf(1L).map(Long::absoluteValue))  // broken (IllegalAccessError)
    println(listOf(1L).map(Long::sign))  // ok
}
