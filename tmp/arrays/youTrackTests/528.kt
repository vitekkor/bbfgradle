// Original bug: KT-41200

import kotlin.random.Random

fun main() {
    val a : Int
    if (Random.nextBoolean()) {
        a = 0
        println(a)
    } else {
        a = 0
        println(a)
    }
    println(5 / a)
}
