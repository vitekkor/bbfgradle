// Original bug: KT-36812

import kotlin.random.Random

fun test(): Char {
    val c: Char
    if (Random.nextBoolean()) {
        c = '1'
    } else {
        c = '2'
    }
    return c
}
