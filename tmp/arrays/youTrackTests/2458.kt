// Original bug: KT-22027

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
