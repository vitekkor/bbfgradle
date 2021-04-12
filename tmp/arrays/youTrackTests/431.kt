// Original bug: KT-41201

import kotlin.random.Random

fun main() {
    val a: Int
    if (Random.nextBoolean()) {
        a = 0
    } else {
        a = 0
    }
    println(5 / a)
}
