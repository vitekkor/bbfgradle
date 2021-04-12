// Original bug: KT-28641

import kotlin.random.Random


infix fun (() -> Unit).unless(condition: Boolean) {
    if (!condition) {
        this()
    }
}

fun main(args: Array<String>) {
    val age = Random.nextInt()
    ({ println("Pay taxes") } as () -> Unit) unless (age < 18)
}
