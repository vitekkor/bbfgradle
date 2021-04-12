// Original bug: KT-14515

fun increment(x : Int) = x + 1
fun square(x : Int) = x * x

fun main(args: Array<String>) {
    val value = 1
    value
            .let { increment(it) } // Line A
            .let { square(it) }    // Line B
            .let { println(it) }   // Line C
}
