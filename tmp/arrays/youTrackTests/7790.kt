// Original bug: KT-17732

fun main(args: Array<String>) {
    val x = 92
    for (divisor in divisors(x)) {
        println(x) // BUG, should be `divisor`, which is unused.
    }
}

fun divisors(x: Int) : Collection<Int> = TODO()
